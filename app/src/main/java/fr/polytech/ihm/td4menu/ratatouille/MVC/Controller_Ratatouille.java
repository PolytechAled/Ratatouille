package fr.polytech.ihm.td4menu.ratatouille.MVC;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Week;

public class Controller_Ratatouille {

    private Model_Recipes model_recipes;
    private View_Recipes view_recipes;

    public Controller_Ratatouille(Model_Recipes model_recipes, View_Recipes view_recipes) {
        this.model_recipes = model_recipes;
        this.view_recipes = view_recipes;
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
