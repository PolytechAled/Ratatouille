package fr.polytech.ihm.td4menu.ratatouille.recipe.create;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.polytech.ihm.td4menu.ratatouille.R;


public class RecipePresFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_pres, container, false);

        String name = " ";

        if(getArguments() != null){
            name = getArguments().getString("recipe_pres_name");
        }


        //recipe_pres_name
        TextView textView = view.findViewById(R.id.recipe_pres_name);
        textView.setText(name);

        return view;
    }
}