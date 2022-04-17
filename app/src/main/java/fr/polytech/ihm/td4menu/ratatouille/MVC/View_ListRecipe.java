package fr.polytech.ihm.td4menu.ratatouille.MVC;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.datas.Week;
import fr.polytech.ihm.td4menu.ratatouille.recipe.RecipeAdapter;

public class View_ListRecipe implements Observer {
    private Controller_Ratatouille controller_ratatouille;
    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;
    private ViewGroup layout;

    public ViewGroup getLayout() {
        return layout;
    }

    public void setListener(Controller_Ratatouille controller) {
        this.controller_ratatouille = controller;
    }

    public void onClickItem(int weekNumber, int recipeID){
        this.controller_ratatouille.onClickItem(weekNumber, recipeID);
    }

    @Override
    public void update(Observable observable, Object o) {
        View result = layout.findViewById(R.id.fragment_recipe_list);
        recipeAdapter = new RecipeAdapter(result.getContext());
        recyclerView = result.findViewById(R.id.recipeListFragment);

        Model_Ratatouille model = (Model_Ratatouille) observable;


        // TODO : Comment recup le numero de semaine?
        Week week = model.getRecipeList(0);
        //Recipes.



        //recyclerView.setAdapter(recipeAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(result.getContext(),LinearLayoutManager.VERTICAL, false));

    }
}
