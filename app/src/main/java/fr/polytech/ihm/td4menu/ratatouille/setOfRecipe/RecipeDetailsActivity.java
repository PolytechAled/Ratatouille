package fr.polytech.ihm.td4menu.ratatouille.setOfRecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity{

    private final String TAG = "INFO "+getClass().getSimpleName();
    private RecipeDetailsFragment recipeDetailsFragment;
    private FragmentTransaction fragmentTransaction;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra(String.valueOf(Recipe.class));

/*
        //display detailFragment if exists
        if (findViewById(R.id.frame_layout_detail) != null) {
            RecipeDetailsFragment detailFragment = new RecipeDetailsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_detail, detailFragment);
            fragmentTransaction.commit();
        }
*/
        if (recipeDetailsFragment == null) {
            recipeDetailsFragment = new RecipeDetailsFragment();
            Bundle args = new Bundle();
            args.putSerializable("Recipe", recipe);
            args.putInt("id", recipe.getId());
            recipeDetailsFragment.setArguments(args);
            fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_detail, recipeDetailsFragment);
            fragmentTransaction.commit();
        }
    }
}