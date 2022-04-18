package fr.polytech.ihm.td4menu.ratatouille.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;

public class ListRecipeActivity extends AppCompatActivity implements OnButtonClickedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);

        //display detailFragment if exists
        if (findViewById(R.id.frame_layout_detail) != null) {
            RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_detail, recipeDetailsFragment);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onButtonClicked(Model_Ratatouille model_ratatouille, int position){
        List<Recipe> recipeList = model_ratatouille.getRecipeList(model_ratatouille.getWeekNumber()).getRecipeList();
        Toast.makeText(this,"Vous voulez voir la Recette : " + recipeList.get(position).getName() , Toast.LENGTH_SHORT).show();

        FrameLayout frameLayout = findViewById(R.id.frame_layout_detail);

        if (frameLayout == null){
            Log.d("info","send value to the DetailActivity =>"+recipeList.get(position).getName());
            Intent intent = new Intent(this, RecipeDetailsActivity.class);
            Recipe recipe = recipeList.get(position);
            intent.putExtra(String.valueOf(Recipe.class), recipe);
            startActivity(intent);
        }
        else {
            Log.d("info","send value to the fragment =>"+recipeList.get(position).getName());
            RecipeDetailsFragment detailFragment = new RecipeDetailsFragment();
            FragmentTransaction fragmentTransaction = ((FragmentActivity)this).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_detail, detailFragment);
            fragmentTransaction.commit();
            model_ratatouille.recipeClick(recipeList.get(position).getId(), findViewById(R.id.frame_layout_detail));
        }
    }
}