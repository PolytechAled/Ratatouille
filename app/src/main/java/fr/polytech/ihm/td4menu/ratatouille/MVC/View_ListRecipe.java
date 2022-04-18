package fr.polytech.ihm.td4menu.ratatouille.MVC;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.datas.Week;
import fr.polytech.ihm.td4menu.ratatouille.recipe.RecipeAdapter;

public class View_ListRecipe implements Observer {
    private Controller_Ratatouille controller_ratatouille;
    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;
    private boolean modelCreated = false;
    private ViewGroup layout;

    public <T extends ViewGroup> View_ListRecipe(Context context, ViewGroup layout) {
        this.recipeAdapter = new RecipeAdapter(context, this); //carrefull, model is null !
        this.layout = layout;
        Log.d("info", "View is created" );
    }

    public ViewGroup getLayout() {
        return layout;
    }

    public void setListener(Controller_Ratatouille controller) {
        this.controller_ratatouille = controller;
    }

    public void onClickItem(int recipeID){
        this.controller_ratatouille.onClickItem(recipeID);
    }

    @Override
    public void update(Observable observable, Object o) {
        Model_Ratatouille model = (Model_Ratatouille) observable;

        if (!modelCreated) {
            recipeAdapter.updateModel(model);

            RecyclerView recyclerView = layout.findViewById(R.id.recipeListFragment);
            recyclerView.setAdapter(recipeAdapter);
            recipeAdapter = new RecipeAdapter(layout.getContext(), this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getLayout().getContext()));
            recyclerView.setAdapter(recipeAdapter);

            modelCreated = true;
        }else {
            recipeAdapter.refresh(model);
        }
    }
}
