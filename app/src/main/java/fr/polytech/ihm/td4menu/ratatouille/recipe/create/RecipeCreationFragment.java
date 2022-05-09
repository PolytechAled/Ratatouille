package fr.polytech.ihm.td4menu.ratatouille.recipe.create;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.SearchRecipeActivity;
import fr.polytech.ihm.td4menu.ratatouille.contracts.SearchRecipeContract;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.recipe.create.custom.CreateCustomRecipe;

public class RecipeCreationFragment extends Fragment {

    private int moment;

    private ActivityResultLauncher<Integer> mSearchRecipe = registerForActivityResult(new SearchRecipeContract(),
            recipe -> {
                Recipes.getDay().setRecipe(moment, recipe);
                Recipes.setMoment(moment);
                Recipes.setNewRecipe(recipe);
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_of_recipe_creation, container, false);

        this.moment = 2;
        if(getArguments() != null){
            this.moment = getArguments().getInt("moment");
        }
        Log.d("info","moment : " + moment);

        view.findViewById(R.id.buttonMenuRecipeCreer).setOnClickListener(clic -> {
            Intent intent = new Intent(getContext(), CreateCustomRecipe.class);
            intent.putExtra("moment", this.moment);
            startActivity(intent);
        });

        view.findViewById(R.id.buttonMenuRecipeRecherche).setOnClickListener(clic -> {
            mSearchRecipe.launch(this.moment);
        });

        Log.d("info", "Fragtest");
        return view;
    }

}