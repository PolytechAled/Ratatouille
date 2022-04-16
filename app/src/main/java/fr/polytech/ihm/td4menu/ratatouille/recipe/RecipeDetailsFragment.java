package fr.polytech.ihm.td4menu.ratatouille.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.polytech.ihm.td4menu.ratatouille.R;

public class RecipeDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.recipe_view, null);
        /*
        Bundle arguments = getArguments();
        TextView displayValue = result.findViewById(R.id.editView);
        if(arguments!=null) {
            int valeur = getArguments().getInt(getString(R.string.value));
            displayValue.setText(Integer.toString(valeur));
        }
        else {
            displayValue.setText("still none");
        }
        */

        return result;
    }
}