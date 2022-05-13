package fr.polytech.ihm.td4menu.ratatouille.datas.recipe.create;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.contracts.SearchRecipeContract;
import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import fr.polytech.ihm.td4menu.ratatouille.datas.api.Spoonacular;
import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.create.custom.CreateCustomRecipe;

public class RecipeCreationFragment extends Fragment {

    private int moment;
    private boolean refreshOk = false;

    private ActivityResultLauncher<Integer> mSearchRecipe = registerForActivityResult(new SearchRecipeContract(),
            recipe -> {
                Recipes.getDay().setRecipe(moment, recipe);
                Recipes.setMoment(moment);
                Recipes.setNewRecipe(recipe);
            });

    @Override
    public void onResume() {
        super.onResume();
        if(refreshOk){
            Log.d("info","refresh()");
            getActivity().recreate();
            refreshOk = false;
        }
    }

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
            refreshOk = true;
            Recipes.setMoment(moment);
            Intent intent = new Intent(getContext(), CreateCustomRecipe.class);
            intent.putExtra("moment", this.moment);
            startActivity(intent);
            //getActivity().recreate();
        });

        view.findViewById(R.id.buttonMenuRecipeRecherche).setOnClickListener(clic -> {
            mSearchRecipe.launch(this.moment);
        });

        view.findViewById(R.id.buttonMenuRecipeGenerer).setOnClickListener(clic -> {
            Recipe newRecipe = randomRecipe();
            Recipes.getDay().setRecipe(moment, newRecipe);
            Recipes.setMoment(moment);
            Recipes.setNewRecipe(newRecipe);
            getActivity().recreate();
        });


        Log.d("info", "Fragtest");
        return view;
    }

    public Recipe randomRecipe() {
        Spoonacular spoonacular = new Spoonacular();
        AtomicReference<List<Recipe>> recipeList = new AtomicReference<>();
        Thread s = new Thread(() -> {
            recipeList.set(Recipes.getRecipeList());
        });

        try {
            s.start();
            s.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collections.shuffle(recipeList.get());
        if(recipeList.get() != null && recipeList.get().size() > 0)
        {
            return recipeList.get().get(0);
        }
        return null;
    }
}