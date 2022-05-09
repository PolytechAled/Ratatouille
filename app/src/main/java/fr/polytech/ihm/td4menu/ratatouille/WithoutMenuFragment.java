package fr.polytech.ihm.td4menu.ratatouille;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Spoonacular;

public class WithoutMenuFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_without_menu, container, false);

        Spinner spinnerOrigin = view.findViewById(R.id.spinnerOrigin);
        Spinner spinnerRegime = view.findViewById(R.id.spinnerRegime);

        ArrayAdapter<CharSequence> adapterOrigin = ArrayAdapter.createFromResource(getContext(), R.array.origine, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterRegime = ArrayAdapter.createFromResource(getContext(), R.array.regime, android.R.layout.simple_spinner_item);

        adapterOrigin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(adapterOrigin);

        adapterRegime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegime.setAdapter(adapterRegime);

        view.findViewById(R.id.createMenu).setOnClickListener(clic -> {
            String origin = spinnerOrigin.getSelectedItem().toString();
            String diet = spinnerRegime.getSelectedItem().toString();
            Spoonacular spoonacular = new Spoonacular();
            try {
                String n = "";
                List<Recipe> recipeList = spoonacular.searchRecipes(n,origin,diet,n);
                //Toast.makeText(getContext(),recipeList)
                int test = 0;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return view;
    }
}