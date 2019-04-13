package com.example.ecommerce.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ingredientsAdapter extends RecyclerView.Adapter<ingredientsAdapter.fragmentviewholder> {

    private List<ingredients> list ;


    public ingredientsAdapter(List<ingredients> list)
    {
        this.list = list ;

    }

    @NonNull
    @Override
    public fragmentviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_ingredients_item, viewGroup, false);
        return new fragmentviewholder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull fragmentviewholder fragmentviewholder, final int i) {

        fragmentviewholder.ingredient.setText(list.get(i).getIngredient());
        fragmentviewholder.quantity.setText( String.valueOf(list.get(i).getQuantity()));
        fragmentviewholder.measure.setText(list.get(i).getMeasure());


    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class fragmentviewholder extends RecyclerView.ViewHolder {

        TextView ingredient;
        TextView measure;
        TextView quantity;

        fragmentviewholder(View itemView) {
            super(itemView);

            ingredient = itemView.findViewById(R.id.textView);
            measure = itemView.findViewById(R.id.textView3);
            quantity = itemView.findViewById(R.id.textView2);

        }

    }


}
