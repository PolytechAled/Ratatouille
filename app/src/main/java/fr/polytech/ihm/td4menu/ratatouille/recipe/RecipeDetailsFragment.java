package fr.polytech.ihm.td4menu.ratatouille.recipe;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.repository.CalendarRepository;

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
        //int valeur = getArguments().getInt("id");

        if(arguments != null){
            Serializable recipe = getArguments().getSerializable("Recipe");
            Recipe r = (Recipe) recipe;
            recipeName.setText(r.getName());


            result.findViewById(R.id.calendar_btn).setOnClickListener((e) -> {
                CalendarRepository repository = new CalendarRepository();
                repository.registerEvent(this.getActivity(), Recipes.get(r.getId()), Calendar.getInstance());
            });
        }

        return result;
    }
}