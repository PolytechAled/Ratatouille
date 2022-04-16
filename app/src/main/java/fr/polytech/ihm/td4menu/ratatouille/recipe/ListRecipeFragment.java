package fr.polytech.ihm.td4menu.ratatouille.recipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;

public class ListRecipeFragment extends Fragment {
    private Recipes recipes = new Recipes();
    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_list_recipe, null);

        recyclerView = result.findViewById(R.id.recipeListFragment);

        recipes.setRecipe(new Recipe("Recette1", "France", 75));
        recipes.setRecipe(new Recipe("Recette2", "France", 15));
        recipes.setRecipe(new Recipe("Recette3", "Belgique", 56));
        recipes.setRecipe(new Recipe("Recette4", "Chine", 45));
        recipes.setRecipe(new Recipe("Recette5", "Ecosse", 45));
        recipes.setRecipe(new Recipe("Recette6", "Espagne", 15));
        recipes.setRecipe(new Recipe("Recette7", "France", 10));

        recipeAdapter = new RecipeAdapter(recipes, result.getContext());

        recyclerView.setAdapter(recipeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(result.getContext(),LinearLayoutManager.VERTICAL, false));

        return result;
    }
}