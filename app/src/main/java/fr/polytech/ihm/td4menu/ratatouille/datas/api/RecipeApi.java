package fr.polytech.ihm.td4menu.ratatouille.datas.api;

import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;

public abstract class RecipeApi {
    public abstract Recipe populateRecipe(int relId) throws Exception;
    public abstract List<Recipe> searchRecipes(String query, String cuisine, String diet, String intolerances) throws Exception;
    public abstract List<Recipe> randomRecipes() throws Exception;
}
