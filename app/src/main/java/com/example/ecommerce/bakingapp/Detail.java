package com.example.ecommerce.bakingapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Detail extends AppCompatActivity {


    private static final String TAG_RETAINED_FRAGMENT = "RetainedFragment";
    private static final String STEP_NUMBER = "step_num";
steps steps ;
ArrayList<steps> list ;

int position ;


    TextView mStepNum ;
    ImageButton mPrevButton ;
    ImageButton mNextButton ;
    FragmentManager fragmentManager ;
    detail_fragment obj ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

         mStepNum = (TextView) findViewById(R.id.step_number) ;
         mPrevButton =(ImageButton)findViewById(R.id.prev_step) ;
         mNextButton =(ImageButton)findViewById(R.id.next_step) ;


        fragmentManager = getSupportFragmentManager() ;
        obj = (detail_fragment)fragmentManager.findFragmentByTag(TAG_RETAINED_FRAGMENT);



        Intent intent = getIntent();
       list =   intent.getParcelableArrayListExtra("steplist") ;
        steps = intent.getParcelableExtra("step_item");
        position = intent.getIntExtra ("position",0) ;



        if (steps.getVideoURL() !=null)
        {
            Log.i("h","sa7") ;

        }
        else
        { Log.i("o","8alat") ;}

//        obj = new detail_fragment();

        Bundle bundl = new Bundle();
        //Log.i(TAG, "onCreate: ");
        //Log.i(TAG, "onCreate: "+listofsteps.size());
        // send list to fragment class
        bundl.putParcelable("step",steps);
        bundl.putParcelableArrayList("steplist",list);
        bundl.putInt("position",position);

        if(savedInstanceState == null) {
            position = intent.getIntExtra ("position",0);
        } else {
            position = savedInstanceState.getInt(STEP_NUMBER);
        }


/*        fragmentManager.beginTransaction()
                .add(R.id.containerr,obj)
                .commit() ;
*/
        updateStepNumberText(position) ;
        //obj.setArguments(bundl);
        if (mPrevButton != null) {
            mPrevButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position--;
                    obj = detail_fragment.newInstance(list,position);
                    updateStepNumberText(position);
                    fragmentManager.beginTransaction().replace(R.id.containerr,obj,TAG_RETAINED_FRAGMENT).commit();
                }
            });
        }

        if (mNextButton != null) {
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position++;
                    obj = detail_fragment.newInstance(list,position);
                    updateStepNumberText(position);
                    fragmentManager.beginTransaction().replace(R.id.containerr,obj,TAG_RETAINED_FRAGMENT).commit();
                }
            });
        }
        if(obj == null){
            obj = detail_fragment.newInstance(list,position);
            fragmentManager.beginTransaction().replace(R.id.containerr,obj,TAG_RETAINED_FRAGMENT).commit();
        }




    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STEP_NUMBER,position);
    }

    private void updateStepNumberText(int stepPosition){

        if(mStepNum != null) {
            mStepNum.setText(Integer.toString(stepPosition));

            // Check position and hide arrow if at 0 or last position,
            // preventing going negative and higher than list size
            if (stepPosition == 0) {
                mPrevButton.setVisibility(View.GONE);
            } else if (stepPosition == list.size() - 1) {
                mNextButton.setVisibility(View.GONE);
            } else {
                mPrevButton.setVisibility(View.VISIBLE);
                mNextButton.setVisibility(View.VISIBLE);
            }
        }
    }

}
