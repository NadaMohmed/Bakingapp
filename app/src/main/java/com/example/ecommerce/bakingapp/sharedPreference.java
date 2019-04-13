package com.example.ecommerce.bakingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.SerializablePermission;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.io.Serializable;

public class sharedPreference {

    static String recipename ;
    public  Context context ;
    sharedPreference(Context context)
    {
        this.context= context ;
    }


    /**
     *     Save and get ArrayList in SharedPreference
     */

    public  void saveArrayList(StringBuilder ingredients, String key){
        ArrayList<StringBuilder> list = new ArrayList<StringBuilder>() ;
        list.add(ingredients) ;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public   ArrayList<StringBuilder> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<StringBuilder>>() {}.getType();
        return gson.fromJson(json, type);
    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_widget: {
                SharedPreferences.Editor sharedPrefsEditor;
                final String MY_PREFS_NAME = "MyPrefsFile";
                StringBuilder ingredients;

                sharedPrefsEditor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

                sharedPrefsEditor.putString("name", BakingWrapper.getInstance().getBakings().getName());
                ingredients = new StringBuilder("");
                for (int i = 0; i < BakingWrapper.getInstance().getBakings().getIngredients().size(); i++) {
                    ingredients.append(BakingWrapper.getInstance().getBakings().getIngredients().get(i).getQuantity());
                    ingredients.append("  ");
                    ingredients.append(BakingWrapper.getInstance().getBakings().getIngredients().get(i).getMeasure());
                    ingredients.append("  ");
                    ingredients.append(BakingWrapper.getInstance().getBakings().getIngredients().get(i).getIngredient());
                    ingredients.append("\n");
                }

                sharedPrefsEditor.putString("Ingredients", ingredients.toString());
                sharedPrefsEditor.apply();

                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    sharedPrefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String namePref = sharedPrefs.getString("name", null);
        String ingredPref = sharedPrefs.getString("Ingredients", null);


*/

  /*  public void addingredients(StringBuilder t,String SHARED_PREFS_FILE ) {

        Serializable object = new SerializablePermission();
          ArrayList<StringBuilder>  currentTasks = new ArrayList<StringBuilder>();

        currentTasks.add(t);

        // save the task list to preference
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);

       SharedPreferences.Editor editor = prefs.edit();


        editor.putString("", String.valueOf(currentTasks));
        editor.putString("",  currentTasks));

        editor.commit();
        //editor.apply();
    }

    sharedPrefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    String namePref = sharedPrefs.getString("name", null);
    String ingredPref = sharedPrefs.getString("Ingredients", null);
*/

   }
