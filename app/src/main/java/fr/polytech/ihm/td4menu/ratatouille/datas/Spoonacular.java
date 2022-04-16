package fr.polytech.ihm.td4menu.ratatouille.datas;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import fr.polytech.ihm.td4menu.ratatouille.JsonFetcher;

public class Spoonacular extends RecipeApi {
    private static final String API_KEY = "5971d456278241fa9895be1c1318448e";
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_COOKING_TIME = "readyInMinutes";

    private static final String RECIPE_API_URL = "https://api.spoonacular.com/recipes/%d/information?apiKey=%s&includeNutrition=false";

    public Recipe getRecipe(int relId) throws JSONException, IOException {
        try {
            String path = String.format(RECIPE_API_URL, relId, API_KEY);
            Log.d("Ratatouille", "Connecting to \"" + path + "\"");
            URL url = new URL(path);

            JSONObject json = JsonFetcher.fetch(url);
            System.out.println(json);
            return new SpRecipe(json.getInt(JSON_ID),
                    json.getString(JSON_TITLE),
                    json.getInt(JSON_COOKING_TIME));
        } catch (IOException | JSONException e) {
            Log.d("Ratatouille", "Could not connect to the Internet.");
            throw e;
        }
    }
}
