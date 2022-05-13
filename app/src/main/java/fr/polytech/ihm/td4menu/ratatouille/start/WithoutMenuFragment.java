package fr.polytech.ihm.td4menu.ratatouille.start;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.datas.api.Spoonacular;
import fr.polytech.ihm.td4menu.ratatouille.setOfRecipe.ListRecipeActivity;

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
            new Thread(() -> {
                try {
                    String n = "";
                    Recipes.setDiet(diet);
                    Recipes.setOrigin(origin);
                    List<Recipe> recipeList = spoonacular.searchRecipes(n, origin, diet, n);
                    Recipes.setRecipeListGenerate(recipeList);
                    delay(2);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        });

        return view;
    }


    public void delay(int seconds){
        int milliseconds = seconds * 1000;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("XXX");                 //add your code here
                        Intent intent = new Intent(getContext(), ListRecipeActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }, milliseconds);
            }
        });
    }
}