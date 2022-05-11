package fr.polytech.ihm.td4menu.ratatouille.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.WithoutMenuFragment;
import fr.polytech.ihm.td4menu.ratatouille.datas.Day;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.recipe.create.MenuOfRecipeCreation;

public class ListRecipeActivity extends AppCompatActivity implements OnButtonClickedListener {
    private RecipeDetailsFragment recipeDetailsFragment;
    private FragmentTransaction fragmentTransaction;
    private ListRecipeFragment listRecipeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);
/*
        listRecipeFragment = new ListRecipeFragment();
        this.fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_recipe_list, listRecipeFragment);
        this.fragmentTransaction.commit();
*/
        //display detailFragment if exists
        if (findViewById(R.id.frame_layout_detail) != null) {
            this.recipeDetailsFragment = new RecipeDetailsFragment();
            this.fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_detail, recipeDetailsFragment);
            this.fragmentTransaction.commit();
        }
    }

    @Override
    public void onButtonClicked(Model_Ratatouille model_ratatouille, Day day, int position){
        List<Recipe> recipeList = model_ratatouille.getWeek(model_ratatouille.getWeekNumber()).getDay(position).getAll();
        FrameLayout frameLayout = findViewById(R.id.frame_layout_detail);

        if (frameLayout == null){
            Intent intent = new Intent(this, RecipeDetailsActivity.class);
            intent.putExtra(String.valueOf(Recipe.class), day.get(position));
            //model_ratatouille.recipeClick(recipeList.get(position).getId(), findViewById(R.id.frame_layout_detail));
            startActivity(intent);
        } else {
            //this.recipeDetailsFragment = new RecipeDetailsFragment();
            this.fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_detail, recipeDetailsFragment);
            fragmentTransaction.commit();
            model_ratatouille.recipeClick(day.get(position).getId(), findViewById(R.id.frame_layout_detail));
       }
    }

    @Override
    public void onButtonClicked2(Model_Ratatouille model_ratatouille, int position) {
        Intent intent = new Intent(this, MenuOfRecipeCreation.class);
        //intent.putExtra("model", (Parcelable) model_ratatouille);
        model_ratatouille.setDayNumber(position);
        Recipes.setDay(model_ratatouille.getWeek(model_ratatouille.getWeekNumber()).getDay(position));
        Recipes.setWeek(model_ratatouille.getWeek(model_ratatouille.getWeekNumber()));
        startActivity(intent);
    }
}