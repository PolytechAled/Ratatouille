package fr.polytech.ihm.td4menu.ratatouille.MVC;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.adapters.RecipeDetailsAdapter;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.datas.RecipeCategory;

import fr.polytech.ihm.td4menu.ratatouille.recipe.OnButtonClickedListener;
import fr.polytech.ihm.td4menu.ratatouille.adapters.WeekAdapter;

public class View_Ratatouille implements Observer{
    private Controller_Ratatouille controller_ratatouille;
    private WeekAdapter weekAdapter;
    private RecyclerView recyclerView;
    private ArrayAdapter<RecipeCategory> arrayAdapter;
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
/*
    public void onClickValidCustomRecipe(Recipe recipe){
        if(recipe != null){
            this.controller_ratatouille.
        }
    }
*/
    @Override
    public void update(Observable observable, Object o) {
        Model_Ratatouille model = (Model_Ratatouille) observable;
        Log.d("info","Update View Ratatouille");
        switch (model.getUpdateType()){
            case VIEW_LISTRECIPE:
                if (true) {
                    //recipeAdapter.updateModel(model);
                    this.weekAdapter = new WeekAdapter(this.layout.getContext(), this,model, callBackActivity);

                    this.recyclerView = layout.findViewById(R.id.recipeListFragment);
                    this.recyclerView.setLayoutManager(new LinearLayoutManager(getLayout().getContext()));
                    this.recyclerView.setAdapter(this.weekAdapter);


                    modelCreated = true;
                }else {
                    Log.d("info","Refresh");
                    this.weekAdapter.refresh(model);
                }
                Log.d("info","Update View listRecipe");
                break;

            case VIEW_DETAILSRECIPE:
                Recipe recipe = Recipes.get(model.getRecipeShow());
                if (recipe == null) break;
                TextView recipeName= model.getLayout().findViewById(R.id.recipeDetailName);
                TextView textViewTime = model.getLayout().findViewById(R.id.recipeDetailTime);
                //GridView gridViewPictos = model.getLayout().findViewById(R.id.recipeDetailPictos);
                ListView listViewIngredient = model.getLayout().findViewById(R.id.recipeDetailIngredients);
                ListView listViewInstruction = model.getLayout().findViewById(R.id.recipeDetailInstructions);

                recipeName.setText(recipe.getName());
                textViewTime.setText(" "+ recipe.getCookingTime() + " minutes");
                ArrayAdapter arraysAdapterIngredient = new ArrayAdapter<String>(this.layout.getContext(), android.R.layout.simple_list_item_1, recipe.getIngredients());
                listViewIngredient.setAdapter(arraysAdapterIngredient);

                ArrayAdapter arraysAdapterInstruction = new ArrayAdapter<String>(this.layout.getContext(), android.R.layout.simple_list_item_1, recipe.getSteps());
                listViewInstruction.setAdapter(arraysAdapterInstruction);

                //RecipeDetailsAdapter recipeDetailsAdapter = new RecipeDetailsAdapter(this.layout.getContext(),recipe.getCategoryList());
                //gridViewPictos.setAdapter(recipeDetailsAdapter);

                break;
        }
    }

    public void addRecipe() {
        this.controller_ratatouille.addRecipe(Recipes.getNewRecipe(), Recipes.getMoment());
    }
}
