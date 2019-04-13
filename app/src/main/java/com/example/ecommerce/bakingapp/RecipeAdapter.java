package com.example.ecommerce.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> results;
    private Context context;


    public RecipeAdapter (Context context, List<Recipe> results) {
        this.context = context;
        this.results = results;

    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_cardtem, viewGroup, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, final int i) {

        recipeViewHolder.recipename.setText(results.get(i).getName());
        recipeViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,"The position is:"+i,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, Master.class);
                if (results.get(i)!= null) {
                   // Log.i("h","sa7") ;
                  /*  List<ingredients> li = new ArrayList<>() ;
                    for (int i=0; i<results.get(i).getIngredients().size();i++)
                    {
                        li.add(results.get(i).getIngredients().get(i)) ;
                    }
                    List<steps> ls = new ArrayList<>() ;
                    for (int i=0; i<results.get(i).getSteps().size();i++)
                    {
                        ls.add(results.get(i).getSteps().get(i)) ;
                    }
*/

                    Recipe recipe = new Recipe(results.get(i).getRecipeID(),results.get(i).getName(),results.get(i).getIngredients(),results.get(i).getSteps(),results.get(i).getServing());
                   // intent.putExtra("Recipe", recipe);
                    intent.putExtra("Recipe", recipe) ;
                }
                else {
                  //  Log.i("o","8alat") ;
                    Toast.makeText(context, "feh 7aga 8alat", Toast.LENGTH_SHORT).show();
                }
                //intent.putExtra("Recipe", results.get(i));
              /*  intent.putExtra ("name",results.get(position));
                intent.putExtra("hi",results.get(position));*/

                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView recipename;
        CardView cv;

        RecipeViewHolder(View itemView) {
            super(itemView);

            recipename = itemView.findViewById(R.id.recipename);
            cv = (CardView)itemView.findViewById(R.id.cv);


        }

    }

}
