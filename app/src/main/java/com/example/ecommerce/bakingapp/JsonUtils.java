package com.example.ecommerce.bakingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    static StringBuilder recipename = new StringBuilder();
    static StringBuilder recipe_ingredients = new StringBuilder();
    static  List<Recipe>  listofrecipe;
    ArrayList<String> currentTasks ;

    public static List<Recipe> getobjects(String json)
            throws JSONException
    {
       // JSONObject jsonObject = new JSONObject(json) ;
        List<Recipe> listofobjects = new ArrayList<Recipe>() ;
        /*List<ingredients> listofingredients = new ArrayList<ingredients>() ;
        List<steps> listofsteps = new ArrayList<steps>() ;*/

        JSONArray jsonarray = new JSONArray(json);
        JSONObject jsonobject ;
        JSONObject ingredientsobject ;
        JSONObject stepsobject ;


        for (int i =0 ; i< jsonarray.length();i++ )

        {
            final List<ingredients> listofingredients = new ArrayList<ingredients>() ;
              final  List<steps> listofsteps = new ArrayList<steps>() ;
            //listofingredients.clear();
            //listofsteps.clear();

            jsonobject = jsonarray.getJSONObject(i);
            int id = jsonobject.getInt("id") ;
            String name = jsonobject.getString("name") ;
            // get list of ingrediants
            JSONArray ingrediantsarray = jsonobject.getJSONArray("ingredients") ;

            for (int j =0; j< ingrediantsarray.length();j++)
            {
                ingredientsobject = ingrediantsarray.getJSONObject(j) ;
                int quantity = ingredientsobject.getInt("quantity") ;
                String measure = ingredientsobject.getString("measure") ;
                String ingre = ingredientsobject.getString("ingredient") ;
                ingredients  ingredientobject = new ingredients(quantity,measure,ingre) ;
                listofingredients.add(ingredientobject) ;


            }
            // get list of steps
            JSONArray stepsarray = jsonobject.getJSONArray("steps") ;
            for (int j =0; j< stepsarray.length();j++)
            {
                stepsobject = stepsarray.getJSONObject(j) ;
                int step_id = stepsobject.getInt("id") ;
                String shortDescription = stepsobject.getString("shortDescription") ;
                String description = stepsobject.getString("description") ;
                String videoURL =stepsobject.getString("videoURL") ;
                String thumbnailURL =stepsobject.getString("thumbnailURL") ;
                steps  stepobject = new steps(step_id,shortDescription,description,videoURL,thumbnailURL) ;

               listofsteps.add(stepobject) ;

            }
            //List<steps> so = new ArrayList<>(listofsteps)  ;


            int serving = jsonobject.getInt("servings") ;
            Recipe re = new Recipe(id,name,listofingredients,listofsteps,serving) ;
            listofobjects.add(re) ;


        }

            return listofobjects ;

    }



}
