package fr.polytech.ihm.td4menu.ratatouille.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.recipe.create.MenuOfRecipeCreation;

public class ListRecipeActivity extends AppCompatActivity implements OnButtonClickedListener {
    private RecipeDetailsFragment recipeDetailsFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);

        //display detailFragment if exists
        if (findViewById(R.id.frame_layout_detail) != null) {
            this.recipeDetailsFragment = new RecipeDetailsFragment();
            this.fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_detail, recipeDetailsFragment);
            this.fragmentTransaction.commit();
        }
    }

    @Override
    public void onButtonClicked(Model_Ratatouille model_ratatouille, int position){
        List<Recipe> recipeList = model_ratatouille.getWeek(model_ratatouille.getWeekNumber()).getDay(0).getAll();
        Toast.makeText(this,"Vous voulez voir la Recette : " + recipeList.get(position).getName() , Toast.LENGTH_SHORT).show();

        FrameLayout frameLayout = findViewById(R.id.frame_layout_detail);

        if (frameLayout == null){
            Log.d("info","send value to the DetailActivity =>"+recipeList.get(position).getName());
            Intent intent = new Intent(this, RecipeDetailsActivity.class);
            Recipe recipe = recipeList.get(position);
            intent.putExtra(String.valueOf(Recipe.class), recipe);
            //model_ratatouille.recipeClick(recipeList.get(position).getId(), findViewById(R.id.frame_layout_detail));
            startActivity(intent);
        } else {
            Log.d("info","send value to the fragment =>"+recipeList.get(position).getName());
            //this.recipeDetailsFragment = new RecipeDetailsFragment();
            this.fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_detail, recipeDetailsFragment);
            fragmentTransaction.commit();
            model_ratatouille.recipeClick(recipeList.get(position).getId(), findViewById(R.id.frame_layout_detail));
       }
    }

    @Override
    public void onButtonClicked2(Model_Ratatouille model_ratatouille, int position) {
        Intent intent = new Intent(this, MenuOfRecipeCreation.class);
        //intent.putExtra("model", (Parcelable) model_ratatouille);
        model_ratatouille.setDayNumber(position);
        Recipes.setDay(model_ratatouille.getWeek(model_ratatouille.getWeekNumber()).getDay(position));
        startActivity(intent);
    }
}