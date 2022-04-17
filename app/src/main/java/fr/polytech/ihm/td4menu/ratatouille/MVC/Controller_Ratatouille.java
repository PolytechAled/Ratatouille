package fr.polytech.ihm.td4menu.ratatouille.MVC;

import android.util.Log;
import android.view.ViewGroup;

import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;

public class Controller_Ratatouille implements IViewClick {

    private Model_Ratatouille model_ratatouille;
    private View_RecipeDetails view_recipeDetails;
    private View_ListRecipe view_listRecipe;

    public Controller_Ratatouille(Model_Ratatouille model_ratatouille, View_RecipeDetails view_recipeDetails) {
        this.model_ratatouille = model_ratatouille;
        this.view_recipeDetails = view_recipeDetails;
/*
        LinearLayout manage = ((ConstraintLayout)view.getLayout()).findViewById(R.id.manage);
        manage.findViewById(R.id.addTeam1).setOnClickListener(click ->  addPersonInTeam( manage, Model_Kindergarten.Team.TEAM1));*/
    }

    public Controller_Ratatouille(Model_Ratatouille model_ratatouille, View_ListRecipe view_listRecipe) {
        this.model_ratatouille = model_ratatouille;
        this.view_listRecipe = view_listRecipe;
/*
        LinearLayout manage = ((ConstraintLayout)view.getLayout()).findViewById(R.id.manage);
        manage.findViewById(R.id.addTeam1).setOnClickListener(click ->  addPersonInTeam( manage, Model_Kindergarten.Team.TEAM1));*/
    }

    /**
     * Add a recipe
     * @param manage
     */
    private void addRecipe(ViewGroup manage) {
        // TODO : Recupe à partir de manage ou Factory?
        // TextView origin = manage.findId...
        // TextView recipeName =
        // ...
        // Week week =
        //Recipe recipe =
        //model_ratatouille.addRecipe(weekNumber,recipe);
    }

    /**
     * Add a week of recipes
     * @param manage
     */
    public void addWeek(ViewGroup manage){
        // TODO : Recupe à partir de manage ou Factory?
        // int id = anmage.findId...
        // List<Recipe> =
        // ...
        // Week week =
        //model_ratatouille.addWeek(weekNumber,week);
    }

    /**
     * Delete a recipe
     * @param weekNumber
     * @param id
     */
    private void deleteRecipe(int weekNumber,int id) {
        model_ratatouille.deleteRecipe(weekNumber,id);
    }

    /**
     * Delete a week of recipes
     * @param weekNumber
     */
    private void deleteWeek(int weekNumber) {
        model_ratatouille.deleteWeek(weekNumber);
    }

    // Je veux afficher la recette cliquée
    @Override
    public void onClickItem(int recipeID){
        Log.d("info", "id item clicked = " + recipeID +". Week = " +model_ratatouille.getWeekNumber());
        if (model_ratatouille.size()>0) {
            model_ratatouille.recipeClick(recipeID);
        }
    }
}
