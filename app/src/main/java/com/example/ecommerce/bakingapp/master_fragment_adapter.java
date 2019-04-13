package com.example.ecommerce.bakingapp;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class master_fragment_adapter extends   RecyclerView.Adapter<master_fragment_adapter.fragmentviewholder> {


   static private List<steps> list ;
    static private Context context;
   public master_fragment_adapter(List<steps> list,Context context)
   {
       this.list = list ;
       this.context = context;
   }

    @NonNull
    @Override
    public master_fragment_adapter.fragmentviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_step_item , viewGroup, false);
        return new fragmentviewholder(view);    }

    @Override
    public void onBindViewHolder(@NonNull master_fragment_adapter.fragmentviewholder fragmentviewholder, final int i) {
       fragmentviewholder.stepname.setText("Step:"+ i +list.get(i).getShortDescription());

       /* fragmentviewholder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<steps> li = (ArrayList<steps>) list;

                Intent intent = new Intent(context,Detail.class) ;
               intent.putParcelableArrayListExtra("steplist",li) ;
                intent.putExtra("step_item",list.get(i)) ;
                intent.putExtra("position",i) ;
                 //intent.putExtras(bundle) ;
                context.startActivity(intent);

            }
        });

*/
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class fragmentviewholder extends RecyclerView.ViewHolder {

        TextView stepname;
        ImageView imgview  ;

        fragmentviewholder(View itemView) {
            super(itemView);

            stepname = itemView.findViewById(R.id.recipeitem);
            imgview = itemView.findViewById(R.id.imageView2) ;

            imgview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ArrayList<steps> li = (ArrayList<steps>) list;

                    if(context.getResources().getBoolean(R.bool.tablet_mode))
                    {
                        ((Master)context).addDetailFragmentTabletActivity(li,
                                list.get(getAdapterPosition()), getAdapterPosition());
                    }
                    else {
                        Intent intent = new Intent(context, Detail.class);
                /*Bundle bundle = new Bundle() ;
                bundle.putParcelable("list",(Parcelable) list);
               bundle.putParcelableArrayList("",li);*/
                        intent.putParcelableArrayListExtra("steplist", li);
                        intent.putExtra("step_item", list.get(getAdapterPosition()));
                        intent.putExtra("position", getAdapterPosition());
                        //intent.putExtras(bundle) ;
                        context.startActivity(intent);
                    }



                }
            });


        }

    }

}
