package com.example.ecommerce.bakingapp;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class master_fragment extends Fragment {


    List<steps> list ;

    public List<ingredients> getListofingrediants() {
        return listofingrediants;
    }

    public void setListofingrediants(List<ingredients> listofingrediants) {
        this.listofingrediants = listofingrediants;
    }

    List<ingredients> listofingrediants ;
    public void setList(List<steps> list) {
        this.list = list;
    }
    public List<steps> getList() {
        return list;
    }
    // default constructor
    public master_fragment()
    {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View rootview = inflater.inflate(R.layout.master_fragment,container,false);
      //  TextView recipedescribtiontext = (TextView)rootview.findViewById(R.id.recipestepdescription) ;
        //recipedescribtiontext.setText("firststep");
        // 1. get a reference to recyclerView
     list = getArguments().getParcelableArrayList("object") ;
     listofingrediants = getArguments().getParcelableArrayList("listofing") ;

     // take objects from recycler veiw
        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.fragmenrv);
        RecyclerView ingredientsrecyclerView = (RecyclerView) rootview.findViewById(R.id.ingredientsrv);

        TextView ingrediants = (TextView) rootview.findViewById(R.id.ingredients) ;

        ingrediants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ingredientsrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        master_fragment_adapter adapter = new master_fragment_adapter(list,getContext()) ;
        ingredientsAdapter adapter1 = new ingredientsAdapter(listofingrediants) ;

        recyclerView.setAdapter(adapter);
        ingredientsrecyclerView.setAdapter(adapter1);

        return rootview ;

    }
}
