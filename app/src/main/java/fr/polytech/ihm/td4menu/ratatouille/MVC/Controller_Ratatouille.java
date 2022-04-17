package fr.polytech.ihm.td4menu.ratatouille.MVC;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import fr.polytech.ihm.td4menu.ratatouille.R;

public class Controller_Ratatouille {
    /*
    private Model_Recipes model_recipes;
    private View_Recipes view_recipes;

    public Controller_Ratatouille(Model_Recipes model_recipes, View_Recipes view_recipes) {
        this.model_recipes = model_recipes;
        this.view_recipes = view_recipes;

        LinearLayout manage = ((ConstraintLayout)view.getLayout()).findViewById(R.id.manage);
        manage.findViewById(R.id.addTeam1).setOnClickListener(click ->  addPersonInTeam( manage, Model_Kindergarten.Team.TEAM1));
    }

    private void addRecipeInTeam(ViewGroup manage, Model_Kindergarten.Team team) {
        String name = ((EditText)manage.findViewById(R.id.input_name)).getText().toString();
        Log.d("info", "add team="+team );
        if ( !name.equals("") ) {
            model_recipes.add(team, name);
            if (model.size() > 0) {
                TextView label = ((ConstraintLayout) view.getLayout()).findViewById(R.id.labelTeam1);
                label.setTextColor(Color.RED);
            }
        }
    }

     */
}
