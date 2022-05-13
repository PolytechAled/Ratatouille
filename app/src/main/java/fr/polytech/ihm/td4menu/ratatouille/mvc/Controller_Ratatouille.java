package fr.polytech.ihm.td4menu.ratatouille.mvc;

import android.util.Log;
import android.view.ViewGroup;

import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;

public class Controller_Ratatouille implements IViewClick {

    private Model_Ratatouille model_ratatouille;
    private View_Ratatouille view_ratatouille;

    public Controller_Ratatouille(Model_Ratatouille model_ratatouille, View_Ratatouille view_ratatouille) {
        this.model_ratatouille = model_ratatouille;
        this.view_ratatouille = view_ratatouille;
/*
        LinearLayout manage = ((ConstraintLayout)view.getLayout()).findViewById(R.id.manage);
        manage.findViewById(R.id.addTeam1).setOnClickListener(click ->  addPersonInTeam( manage, Model_Kindergarten.Team.TEAM1));*/
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
            model_ratatouille.recipeClick(recipeID,null);
        }
    }

    public void addRecipe(Recipe recipe, int moment) {
        if(recipe != null) {
            Log.d("info", "Nom de la recette : " + recipe.getName());
            model_ratatouille.addRecipe(recipe, moment);
        }
    }
}
