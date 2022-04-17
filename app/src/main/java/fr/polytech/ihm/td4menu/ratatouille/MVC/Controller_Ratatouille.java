package fr.polytech.ihm.td4menu.ratatouille.MVC;

import android.view.ViewGroup;

import fr.polytech.ihm.td4menu.ratatouille.datas.Week;

public class Controller_Ratatouille {

    private Model_Ratatouille model_ratatouille;
    private View_RecipeDetails view_recipeDetails;

    public Controller_Ratatouille(Model_Ratatouille model_ratatouille, View_RecipeDetails view_recipeDetails) {
        this.model_ratatouille = model_ratatouille;
        this.view_recipeDetails = view_recipeDetails;
/*
        LinearLayout manage = ((ConstraintLayout)view.getLayout()).findViewById(R.id.manage);
        manage.findViewById(R.id.addTeam1).setOnClickListener(click ->  addPersonInTeam( manage, Model_Kindergarten.Team.TEAM1));*/
    }

    private void addRecipe(ViewGroup manage) {

    }

    private void deleteRecipe(ViewGroup manage) {

    }

    public void addWeek(int weekNumber){

    }

    private void deleteWeek(ViewGroup manage) {

    }

    public Week getWeek(){ // TODO : voir la pertinence; Dans view?
        return null;
    }
}
