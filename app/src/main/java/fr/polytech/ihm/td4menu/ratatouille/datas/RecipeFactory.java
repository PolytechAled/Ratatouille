package fr.polytech.ihm.td4menu.ratatouille.datas;

import org.json.JSONException;

import java.io.IOException;

public class RecipeFactory {

    private RecipeFactory(){}

    public static Recipe instantiate(DataSource source, int relId) throws JSONException, IOException {
        switch (source){
            case RATATOUILLE:
                return Recipes.get(relId);

            case SPOONACULAR:
                Spoonacular recipeApi = new Spoonacular();

                return recipeApi.populateRecipe(relId);

            case EDAMAM:
                throw new UnsupportedOperationException("The Edamam API is not implemented yet.");
        }

        throw new UnsupportedOperationException("Could not find a supported API (" + source.name() + ").");
    }
}
