package fr.polytech.ihm.td4menu.ratatouille.datas;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.JsonFetcher;

public class Spoonacular extends RecipeApi {
    private static final String API_KEY = "5971d456278241fa9895be1c1318448e";
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_COOKING_TIME = "readyInMinutes";
    private static final String JSON_RESULTS = "results";
    private static final String JSON_RECIPES = "recipes";
    private static final String JSON_INGREDIENTS = "extendedIngredients";
    private static final String JSON_INGREDIENT_NAME = "name";
    private static final String JSON_INGREDIENT_MEASURES = "measures";
    private static final String JSON_INGREDIENT_MEASURE_METRIC = "metric";
    private static final String JSON_INGREDIENT_MEASURE_AMOUNT = "amount";
    private static final String JSON_INGREDIENT_MEASURE_UNIT_SHORT = "unitShort";
    private static final String JSON_RECIPE_STEPS = "steps";
    private static final String JSON_RECIPE_STEP = "step";

    private static final String RECIPE_API_URL = "https://api.spoonacular.com/recipes/%d/information?apiKey=%s&includeNutrition=false";
    private static final String SEARCH_RECIPE_API_URL = "https://api.spoonacular.com/recipes/complexSearch?apiKey=%s&query=%s&" +
            "cuisine=%s&" +
            "diet=%s&" +
            "intolerances=%s&" +
            "instructionsRequired=true&" +
            // TODO remove limit
            "number=2";
    private static final String RANDOM_RECIPES_API_URL = "https://api.spoonacular.com/recipes/random?apiKey=%s&" +
            // TODO remove limit
            "number=2";
    private static final String RECIPE_INSTRUCTIONS_API_URL = "https://api.spoonacular.com/recipes/%d/analyzedInstructions?apiKey=%s";

    @Override
    public Recipe populateRecipe(int relId) throws JSONException, IOException {
        Recipe recipe = Recipes.get(relId);

        try {
            String path = String.format(RECIPE_API_URL, relId, API_KEY);
            Log.d("Ratatouille", "Connecting to \"" + path + "\"");
            URL url = new URL(path);

            JSONObject json = JsonFetcher.fetchObject(url);
            System.out.println(json);

            if (recipe == null){
                recipe = new SpRecipe(relId, json.getString(JSON_TITLE));
                Recipes.add(recipe);
            }

            recipe.setOrigin("France");

            List<String> ingredientList = new ArrayList<>();

            if (json.has(JSON_INGREDIENTS)){
                JSONArray ingredients = json.getJSONArray(JSON_INGREDIENTS);
                Log.d("Ratatouille", "Recipe has " + ingredients.length() + " ingredients.");

                for (int i = 0; i < ingredients.length(); ++i){
                    JSONObject ingredient = ingredients.getJSONObject(i);
                    JSONObject measureMetric = ingredient.getJSONObject(JSON_INGREDIENT_MEASURES).getJSONObject(JSON_INGREDIENT_MEASURE_METRIC);
                    ingredientList.add(ingredient.getString(JSON_INGREDIENT_NAME) + " " + measureMetric.getDouble(JSON_INGREDIENT_MEASURE_AMOUNT) + measureMetric.getString(JSON_INGREDIENT_MEASURE_UNIT_SHORT));
                }
            }

            Log.d("Ratatouille", ingredientList.toString());

            recipe.setIngredients(ingredientList);
            recipe.setCookingTime(json.getInt(JSON_COOKING_TIME));

            URL instructionsUrl = new URL(String.format(RECIPE_INSTRUCTIONS_API_URL, relId, API_KEY));

            JSONArray instructionsJson = JsonFetcher.fetchArray(instructionsUrl);

            if (instructionsJson.length() > 0) {
                JSONObject instructions = instructionsJson.getJSONObject(0);
                JSONArray steps = instructions.getJSONArray(JSON_RECIPE_STEPS);
                ArrayList<String> stepList = new ArrayList<>();

                for (int i = 0; i < steps.length(); ++i){
                    JSONObject step = steps.getJSONObject(i);
                    stepList.add(step.getString(JSON_RECIPE_STEP));
                }

                recipe.setSteps(stepList);
            }

            recipe.setPopulated(true);

            return recipe;
        } catch (IOException | JSONException e) {
            Log.d("Ratatouille", "Could not connect to the Internet.");
            throw e;
        }
    }

    @Override
    public List<Recipe> searchRecipes(String query, String cuisine, String diet, String intolerances) throws JSONException, IOException {
        List<Recipe> recipeList = new ArrayList<>();

        try {
            String path = String.format(SEARCH_RECIPE_API_URL, API_KEY,
                    URLEncoder.encode(query, "utf-8"),
                    URLEncoder.encode(cuisine, "utf-8"),
                    URLEncoder.encode(diet, "utf-8"),
                    URLEncoder.encode(intolerances, "utf-8"));
            Log.d("Ratatouille", "Connecting to \"" + path + "\"");
            URL url = new URL(path);

            JSONObject json = JsonFetcher.fetchObject(url);

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

    @Override
    public List<Recipe> randomRecipes() throws JSONException, IOException {
        List<Recipe> recipeList = new ArrayList<>();

        try {
            String path = String.format(RANDOM_RECIPES_API_URL, API_KEY);
            Log.d("Ratatouille", "Connecting to \"" + path + "\"");
            URL url = new URL(path);

            JSONObject json = JsonFetcher.fetchObject(url);

            if (json.has(JSON_RECIPES)){
                JSONArray array = json.getJSONArray(JSON_RECIPES);

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
