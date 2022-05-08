package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.List;

public abstract class RecipeApi {
    public abstract Recipe populateRecipe(int relId) throws Exception;
    public abstract List<Recipe> searchRecipes(String query, String cuisine, String diet, String intolerances) throws Exception;
}
