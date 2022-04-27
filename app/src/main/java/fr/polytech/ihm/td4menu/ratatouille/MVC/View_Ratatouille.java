package fr.polytech.ihm.td4menu.ratatouille.MVC;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.recipe.OnButtonClickedListener;
import fr.polytech.ihm.td4menu.ratatouille.recipe.WeekAdapter;

public class View_Ratatouille implements Observer{
    private Controller_Ratatouille controller_ratatouille;
    private WeekAdapter weekAdapter;
    private RecyclerView recyclerView;
    private boolean modelCreated = false;
    private ViewGroup layout;
    private OnButtonClickedListener callBackActivity;

    public <T extends ViewGroup> View_Ratatouille(Context context, ViewGroup layout, OnButtonClickedListener callBackActivity) {
        //this.recipeAdapter = new RecipeAdapter(context, this); //carrefull, model is null !
        this.layout = layout;
        this.callBackActivity = callBackActivity;
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
        Log.d("info","Update View Ratatouille");
        switch (model.getUpdateType()){
            case VIEW_LISTRECIPE:
                if (!modelCreated) {
                    //recipeAdapter.updateModel(model);
                    this.weekAdapter = new WeekAdapter(this.layout.getContext(), this,model, callBackActivity);

                    this.recyclerView = layout.findViewById(R.id.recipeListFragment);
                    this.recyclerView.setLayoutManager(new LinearLayoutManager(getLayout().getContext()));
                    this.recyclerView.setAdapter(this.weekAdapter);


                    modelCreated = true;
                }else {
                    this.weekAdapter.refresh(model);
                }
                Log.d("info","Update View listRecipe");
                break;

            case VIEW_DETAILSRECIPE:
                Recipe recipe = Recipes.get(model.getRecipeShow());
                TextView textView = model.getLayout().findViewById(R.id.recipeDetailName);
                textView.setText(recipe.getName());
                Log.d("info","Update View detailsRecipe");
                break;
        }
    }
}
