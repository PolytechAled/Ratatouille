package fr.polytech.ihm.td4menu.ratatouille.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Controller_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.MVC.View_ListRecipe;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.DataSource;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.RecipeFactory;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;

public class ListRecipeFragment extends Fragment {
    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_list_recipe, null);

/*
        new Thread(() -> {
            Recipe recipe = new Recipe(0, "Recette1");
            recipe.setOrigin("France");
            recipe.setCookingTime(75);
            Recipes.add(recipe);

            recipe = new Recipe(1, "Recette2");
            recipe.setOrigin("France");
            recipe.setCookingTime(15);
            Recipes.add(recipe);

            recipe = new Recipe(2, "Recette3");
            recipe.setOrigin("Belgique");
            recipe.setCookingTime(56);
            Recipes.add(recipe);

            recipe = new Recipe(3, "Recette4");
            recipe.setOrigin("Chine");
            recipe.setCookingTime(45);
            Recipes.add(recipe);

            recipe = new Recipe(4, "Recette5");
            recipe.setOrigin("Ecosse");
            recipe.setCookingTime(45);
            Recipes.add(recipe);

            recipe = new Recipe(5, "Recette6");
            recipe.setOrigin("Espagne");
            recipe.setCookingTime(15);
            Recipes.add(recipe);

            recipe = new Recipe(6, "Recette7");
            recipe.setOrigin("France");
            recipe.setCookingTime(10);
            Recipes.add(recipe);

            try {
                recipe = RecipeFactory.instantiate(DataSource.SPOONACULAR, 716429);

                Recipes.add(recipe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
*/

        //create VIEW with XML layout
        View_ListRecipe view = new View_ListRecipe( getActivity().getApplicationContext(), (ViewGroup) result);
        Model_Ratatouille model = new Model_Ratatouille(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW

        // TODO : Just for exemple
        model.build();

        Controller_Ratatouille controller = new Controller_Ratatouille( model,view );
        model.setController(controller);
        view.setListener( controller );

        return result;
    }
}