package fr.polytech.ihm.td4menu.ratatouille.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;

public class ListRecipeFragment extends Fragment {
    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_list_recipe, null);

        recyclerView = result.findViewById(R.id.recipeListFragment);


        Recipes.add(new Recipe(0, "Recette1", "France", 75));
        Recipes.add(new Recipe(1, "Recette2", "France", 15));
        Recipes.add(new Recipe(2, "Recette3", "Belgique", 56));
        Recipes.add(new Recipe(3, "Recette4", "Chine", 45));
        Recipes.add(new Recipe(4, "Recette5", "Ecosse", 45));
        Recipes.add(new Recipe(5, "Recette6", "Espagne", 15));
        Recipes.add(new Recipe(6, "Recette7", "France", 10));

        recipeAdapter = new RecipeAdapter(result.getContext());

        recyclerView.setAdapter(recipeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(result.getContext(),LinearLayoutManager.VERTICAL, false));

        return result;
    }
}