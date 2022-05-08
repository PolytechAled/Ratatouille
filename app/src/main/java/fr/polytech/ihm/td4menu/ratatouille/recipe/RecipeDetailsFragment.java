package fr.polytech.ihm.td4menu.ratatouille.recipe;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.adapters.RecipeDetailsAdapter;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.repository.CalendarRepository;

public class RecipeDetailsFragment extends Fragment {

    public RecipeDetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        TextView recipeName= result.findViewById(R.id.recipeDetailName);
        TextView textViewTime = result.findViewById(R.id.recipeDetailTime);
        GridView gridViewPictos = result.findViewById(R.id.recipeDetailPictos);
        ImageView imageView = result.findViewById(R.id.recipeDetailImage);
        ListView listViewIngredient = result.findViewById(R.id.recipeDetailIngredients);
        ListView listViewInstruction = result.findViewById(R.id.recipeDetailInstructions);

        Bundle arguments = getArguments();
        //int valeur = getArguments().getInt("id");

        if(arguments != null){
            Serializable recipe = getArguments().getSerializable("Recipe");
            Recipe r = (Recipe) recipe;
            result.findViewById(R.id.calendar_btn).setOnClickListener((e) -> {
                CalendarRepository repository = new CalendarRepository();
                repository.registerEvent(this.getActivity(), Recipes.get(r.getId()), Calendar.getInstance());
            });

            recipeName.setText(r.getName());
            textViewTime.setText(" "+ r.getCookingTime() + " minutes");
            ArrayAdapter arraysAdapterIngredient = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, r.getIngredients());
            listViewIngredient.setAdapter(arraysAdapterIngredient);

            ArrayAdapter arraysAdapterInstruction = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, r.getSteps());
            listViewInstruction.setAdapter(arraysAdapterInstruction);

            RecipeDetailsAdapter recipeDetailsAdapter = new RecipeDetailsAdapter(getContext(),r.getCategoryList());
            gridViewPictos.setAdapter(recipeDetailsAdapter);
            //imageView.setImageURI(Uri.parse(r.getImageUrl()));
        }

        return result;
    }
}