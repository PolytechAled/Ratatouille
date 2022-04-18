package fr.polytech.ihm.td4menu.ratatouille.recipe;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;

public class RecipeDetailsFragment extends Fragment {
    private Model_Ratatouille model_ratatouille;
    public RecipeDetailsFragment() {
        //this.model_ratatouille = model_ratatouille;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        TextView recipeName= result.findViewById(R.id.recipeDetailName);
        Bundle arguments = getArguments();
        if(arguments != null){
            Recipe recipe = (Recipe) arguments.getParcelable(String.valueOf(Recipe.class));
            recipeName.setText(recipe.getName());
        }

        return result;
    }
}