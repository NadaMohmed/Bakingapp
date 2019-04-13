package com.example.ecommerce.bakingapp;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    List<Recipe> lisofrecipe ;
    URL  url ;
    RecyclerView rv ;
    LinearLayoutManager sglm ;
    GridLayoutManager sglm_grid ;
    RecipeAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    lisofrecipe = new ArrayList<Recipe>() ;
        rv = findViewById(R.id.rv);
    /*    sglm = new LinearLayoutManager(MainActivity.this);
        sglm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(sglm);*/

       url = NetworkUtils.buildUrl() ;
        new GithubQueryTask().execute(url);

    }


    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl) ;
                Log.i("msg",githubSearchResults) ;
            } catch (IOException e) {
                e.printStackTrace();
                //Log.i("error",e.getMessage());
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                try {
                    lisofrecipe = JsonUtils.getobjects(githubSearchResults) ;
                    JsonUtils.listofrecipe = lisofrecipe ;
                   for (int i=0 ;i< lisofrecipe.size();i++)
                    {
                        Recipe re = lisofrecipe.get(i) ;
                        JsonUtils.recipename.append( re.getName() + "\n") ;
                        /*for(int j=0; j<re.getIngredients().size();j++)
                        {
                            JsonUtils.recipe_ingredients.append(re.getIngredients().get(j)+ "\n") ;

                        }*/


                    }
                    //////// to get ingredients
                    Recipe re = lisofrecipe.get(0) ;
                   for(int j=0; j<re.getIngredients().size();j++)
                        {
                            JsonUtils.recipe_ingredients.append("Ingredients"+"\n"+ re.getIngredients().get(j).getIngredient()
                           /* + String.valueOf( re.getIngredients().get(j).getQuantity()) +" " + re.getIngredients().get(j).getMeasure()+ "\n"*/

                            ) ;

                        }
                        //////////////


                    if (getResources().getBoolean(R.bool.tablet_mode))
                    {

                        adapter= new RecipeAdapter(MainActivity.this,lisofrecipe) ;
                        rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                        //rv.setHasFixedSize(true);
                        rv.setAdapter(adapter);
                        // MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                    }


                    else
                    {
                        adapter= new RecipeAdapter(MainActivity.this,lisofrecipe) ;
                        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                        rv.setAdapter(adapter);

                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            else
            {
                Toast.makeText(MainActivity.this, "Failed to get results,please try again", Toast.LENGTH_SHORT).show();

            }

        }


}
}
