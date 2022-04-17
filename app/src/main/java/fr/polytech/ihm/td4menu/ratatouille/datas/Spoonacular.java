package fr.polytech.ihm.td4menu.ratatouille.datas;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.JsonFetcher;

public class Spoonacular extends RecipeApi {
    private static final String API_KEY = "5971d456278241fa9895be1c1318448e";
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_COOKING_TIME = "readyInMinutes";
    private static final String JSON_RESULTS = "results";

    private static final String RECIPE_API_URL = "https://api.spoonacular.com/recipes/%d/information?apiKey=%s&includeNutrition=false";
    private static final String SEARCH_RECIPE_API_URL = "https://api.spoonacular.com/recipes/complexSearch?query=%s&number=2&apiKey=%s";

    @Override
    public Recipe populateRecipe(int relId) throws JSONException, IOException {
        Recipe recipe = Recipes.get(relId);

        try {
            String path = String.format(RECIPE_API_URL, relId, API_KEY);
            Log.d("Ratatouille", "Connecting to \"" + path + "\"");
            URL url = new URL(path);

            JSONObject json = JsonFetcher.fetch(url);
            System.out.println(json);

            if (recipe == null){
                recipe = new SpRecipe(relId, json.getString(JSON_TITLE));
                Recipes.add(recipe);
            }

            recipe.setOrigin("France");
            recipe.setCookingTime(json.getInt(JSON_COOKING_TIME));

            recipe.setPopulated(true);

            return recipe;
        } catch (IOException | JSONException e) {
            Log.d("Ratatouille", "Could not connect to the Internet.");
            throw e;
        }
    }

    @Override
    public List<Recipe> searchRecipes(String query) throws JSONException, IOException {
        List<Recipe> recipeList = new ArrayList<>();

        try {
            String path = String.format(SEARCH_RECIPE_API_URL, query, API_KEY);
            Log.d("Ratatouille", "Connecting to \"" + path + "\"");
            URL url = new URL(path);

            JSONObject json = JsonFetcher.fetch(url);

            if (json.has(JSON_RESULTS)){
                JSONArray array = json.getJSONArray(JSON_RESULTS);

                for (int i = 0; i < array.length(); ++i){
                    JSONObject recipe = array.getJSONObject(i);

                    recipeList.add(new SpRecipe(recipe.getInt(JSON_ID), recipe.getString(JSON_TITLE)));
                }
            }

            return recipeList;
        } catch (IOException | JSONException e) {
            Log.d("Ratatouille", "Could not connect to the Internet.");
            throw e;
        }
    }
}
