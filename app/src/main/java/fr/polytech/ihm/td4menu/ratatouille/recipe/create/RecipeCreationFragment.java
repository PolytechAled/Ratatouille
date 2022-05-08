package fr.polytech.ihm.td4menu.ratatouille.recipe.create;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.polytech.ihm.td4menu.ratatouille.Intro;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.recipe.create.custom.CreateCustomRecipe;

public class RecipeCreationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_of_recipe_creation, container, false);

        int moment = 2;
        if(getArguments() != null){
            moment = getArguments().getInt("moment");
        }
        Log.d("info","moment : " + moment);

        int finalMoment = moment;
        view.findViewById(R.id.buttonMenuRecipeCreer).setOnClickListener(clic -> {
            Intent intent = new Intent(getContext(), CreateCustomRecipe.class);
            intent.putExtra("moment", finalMoment);
            startActivity(intent);
        });

        Log.d("info", "Fragtest");
        return view;
    }
}