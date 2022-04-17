package fr.polytech.ihm.td4menu.ratatouille.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;

public class RecipeDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        Bundle args = getArguments();
        TextView textView = result.findViewById(R.id.recipeNameDetail);

        Recipe recipe = (Recipe) getArguments().getSerializable(String.valueOf(Recipe.class));
        if(args != null && recipe != null){
            textView.setText(recipe.getName());
        }else{
            textView.setText("");
        }

        return result;
    }
}