package fr.polytech.ihm.td4menu.ratatouille.datas.recipe.create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Day;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;

public class MenuOfRecipeCreation extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    RecipeCreationFragment menuOfRecipeCreationFragment1;
    RecipeCreationFragment menuOfRecipeCreationFragment2;
    RecipePresFragment recipePresFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_of_recipe_creation);
        render(true);
    }

    private void render(boolean firstRender) {
        Day day = Recipes.getDay();
        Log.d("info","DAY : " + day.getDayString());
        TextView titre = findViewById(R.id.menuOfecipeCreationTitle);
        if (firstRender) {
            String jour = (String) titre.getText();
            jour += day.getDayString();
            Log.d("info", "test jour : " + jour);
            titre.setText(jour);
        }

        int moment;

        if (menuOfRecipeCreationFragment1 == null || day.getFirstRecipe() == null) {
            menuOfRecipeCreationFragment1 = new RecipeCreationFragment();
            Bundle args = new Bundle();
            moment = 0;
            args.putInt("moment", moment);
            menuOfRecipeCreationFragment1.setArguments(args);
            fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.menuOfRecipeCreation1, menuOfRecipeCreationFragment1);
            fragmentTransaction.commit();
        }else{
            recipePresFragment = new RecipePresFragment();
            Bundle args = new Bundle();
            args.putString("recipe_pres_name", day.getFirstRecipe().getName());
            recipePresFragment.setArguments(args);
            fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.menuOfRecipeCreation1, recipePresFragment);
            fragmentTransaction.commit();
        }

        if (menuOfRecipeCreationFragment2 == null || day.getSecondRecipe() == null) {
            menuOfRecipeCreationFragment2 = new RecipeCreationFragment();
            Bundle args = new Bundle();
            moment = 1;
            args.putInt("moment", moment);
            menuOfRecipeCreationFragment2.setArguments(args);
            fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.menuOfRecipeCreation2, menuOfRecipeCreationFragment2);
            fragmentTransaction.commit();
        }else{
            recipePresFragment = new RecipePresFragment();
            Bundle args = new Bundle();
            args.putString("recipe_pres_name", day.getSecondRecipe().getName());
            recipePresFragment.setArguments(args);
            fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.menuOfRecipeCreation2, recipePresFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        render(false);
    }
}