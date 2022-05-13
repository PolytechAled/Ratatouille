package fr.polytech.ihm.td4menu.ratatouille.recipe.create.search;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.adapters.RecipeListAdapter;
import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;

public class SearchRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(this, Recipes.getRecipeList());
        ListView recipeList = findViewById(R.id.searchRecipeList);
        recipeList.setAdapter(recipeListAdapter);

        recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setResult(Activity.RESULT_OK, new Intent().putExtra("recipe", (Recipe) recipeListAdapter.getItem(i)));
                finish();
            }
        });


        EditText searchField = findViewById(R.id.searchRecipeInput);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                recipeListAdapter.filter(searchField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }



}