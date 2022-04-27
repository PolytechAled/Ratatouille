package fr.polytech.ihm.td4menu.ratatouille.recipe.create;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Day;
import fr.polytech.ihm.td4menu.ratatouille.recipe.create.custom.CreateCustomRecipe;

public class MenuOfRecipeCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_of_recipe_creation);

        Day day = null;

        findViewById(R.id.buttonMenuRecipeCreer).setOnClickListener(clic -> {
            Intent intent = new Intent(this, CreateCustomRecipe.class);
            //intent.putExtra("val", val);
            startActivity(intent);
            finish();
        });
    }

}