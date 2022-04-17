package fr.polytech.ihm.td4menu.ratatouille.MVC;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

import fr.polytech.ihm.td4menu.ratatouille.recipe.RecipeAdapter;

public class View_ListRecipe implements Observer {
    private Controller_Ratatouille controller_ratatouille;
    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;

    @Override
    public void update(Observable observable, Object o) {

    }
}
