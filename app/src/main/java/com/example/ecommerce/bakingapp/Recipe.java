package com.example.ecommerce.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

 public class Recipe implements Parcelable {

  private   int recipeID ;
    private String name ;
    private   List<ingredients> ingredientslist ;
    private List<steps> stepsList ;
    private int serving ;




     public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<com.example.ecommerce.bakingapp.ingredients> getIngredients() {
        return ingredientslist;
    }

    public void setIngredients(List<com.example.ecommerce.bakingapp.ingredients> ingredients) {
        this.ingredientslist = ingredients;
    }

    public List<steps> getSteps() {
        return stepsList;
    }

    public void setSteps(List<steps> steps) {
        this.stepsList = steps;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

   public Recipe (int recipeID , String name , List<ingredients> ingredients, List<steps> steps, int serving )
   {
       this.recipeID= recipeID ;
       this.name = name ;
       this.ingredientslist = ingredients ;
       this.stepsList = steps ;
       this.serving = serving ;

          }


     @Override
     public int describeContents() {
         return 0;
     }

     @Override
     public void writeToParcel(Parcel dest, int flags) {
         dest.writeInt(this.recipeID);
         dest.writeString(this.name);
         dest.writeTypedList(this.ingredientslist);
         dest.writeTypedList(this.stepsList);
         dest.writeInt(this.serving);
     }

     protected Recipe(Parcel in) {
         this.recipeID = in.readInt();
         this.name = in.readString();
         this.ingredientslist = in.createTypedArrayList(ingredients.CREATOR);
         this.stepsList = in.createTypedArrayList(steps.CREATOR);
         this.serving = in.readInt();
     }

     public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
         @Override
         public Recipe createFromParcel(Parcel source) {
             return new Recipe(source);
         }

         @Override
         public Recipe[] newArray(int size) {
             return new Recipe[size];
         }
     };
 }
