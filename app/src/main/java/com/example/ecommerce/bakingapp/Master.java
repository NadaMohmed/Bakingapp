package com.example.ecommerce.bakingapp;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Master extends AppCompatActivity {

    //@BindView(R.id.instructions_toolbar)Toolbar toolbar;
    //@BindView(R.id.instructions_recipe_name)TextView recipeName;
    //@BindView(R.id.fav_toggle)ToggleButton toggleButton;
    sharedPreference sh ;
    ArrayList<ingredients> listofingerdients ;
    StringBuilder inglist ;
  //  Toolbar toolbar ;
    TextView recipename  ;
    ToggleButton toggleButton ;
    Recipe recipe ;
   public static final String TAG=Master.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        // toolbar = (Toolbar)findViewById( R.id.instructions_toolbar );
         recipename = (TextView) findViewById(R.id.instructions_recipe_name) ;
         toggleButton = (ToggleButton) findViewById(R.id.fav_toggle) ;


        Intent intent = getIntent();

        recipe = intent.getParcelableExtra("Recipe");
        if (recipe.getSteps() !=null)
        {
            Log.i("h","sa7") ;

        }
        else
        { Log.i("o","8alat") ;}

        recipename.setText(recipe.getName());
      // String string= recipe.getSteps().get(0).getShortDescription() ;
       //Log.i("hi",string) ;
     // get list of steps
        ArrayList<steps> listofsteps = new ArrayList<steps>() ;
        listofsteps = (ArrayList<steps>) recipe.getSteps() ;

        // get list of ingrediants
         listofingerdients = new ArrayList<ingredients>() ;
      listofingerdients = (ArrayList<ingredients>) recipe.getIngredients() ;
          inglist = new StringBuilder();

/*        for (int i=0; i<listofsteps.size() ;i++)
        {
            String str =listofsteps.get(i).getShortDescription() ;
            Log.i("s" ,str) ;

        }*/
        master_fragment obj = new master_fragment();

        Bundle bundle = new Bundle();
        Log.i(TAG, "onCreate: ");
        Log.i(TAG, "onCreate: "+listofsteps.size());
        // send list to fragment class
        bundle.putParcelableArrayList("object", listofsteps);
        bundle.putParcelableArrayList("listofing", listofingerdients);
        obj.setArguments(bundle);
       // obj.setList(recipe.getSteps());

        FragmentManager fragmentManager = getSupportFragmentManager() ;
        fragmentManager.beginTransaction()
                .add(R.id.container,obj)
                .commit() ;

       /* ArrayList<StringBuilder> str = sh.getArrayList(recipe.getName()) ;

        if (str!=null)
        {
            toggleButton.setChecked(true);

        }
        else
        {
            toggleButton.setChecked(false);
        }*/

toggleButton.setOnClickListener(new View.OnClickListener() {


    @Override
    public void onClick(View view) {
        sharedPreference.recipename = recipe.getName() ;
        toggleButton.setChecked(true);

        if (toggleButton.isChecked())
        { for (int i=0 ; i < listofingerdients.size();i++)
         {
             inglist.append(listofingerdients.get(i).getIngredient()+ "\n");
         }
          sh = new sharedPreference(getApplicationContext());
         sh.saveArrayList(inglist,sharedPreference.recipename);

        }

        ArrayList<StringBuilder> str = sh.getArrayList(recipe.getName()) ;


       /* else
        {

        }*/

    }
});

        //if(getResources().getBoolean(R.bool.tablet_mode))

        }

        public void addDetailFragmentTabletActivity(ArrayList<steps> stepsList, steps s, int position)
        {
            detail_fragment ob = new detail_fragment();

            Bundle bundle = new Bundle();

            bundle.putParcelable("step", s);
            bundle.putInt("position", position);
            bundle.putParcelableArrayList("steplist", stepsList);
            ob.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager() ;
            fragmentManager.beginTransaction()
                    .add(R.id.step_detail_container,ob)
                    .commit() ;

        }
}
