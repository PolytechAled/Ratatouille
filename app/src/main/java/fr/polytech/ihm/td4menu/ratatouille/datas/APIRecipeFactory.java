package fr.polytech.ihm.td4menu.ratatouille.datas;

import org.json.JSONException;

import java.io.IOException;

public class APIRecipeFactory extends RecipeFactory {
    private final DataSource dataSource;
    private final int relId;

    public APIRecipeFactory(DataSource dataSource, int relId){
        this.dataSource = dataSource;
        this.relId = relId;
    }

    @Override
    public Recipe instantiate() throws JSONException, IOException {
        switch (dataSource){
            case RATATOUILLE:
                return Recipes.get(relId);

            case SPOONACULAR:
                Spoonacular recipeApi = new Spoonacular();

                return recipeApi.populateRecipe(relId);

            case EDAMAM:
                throw new UnsupportedOperationException("The Edamam API is not implemented yet.");
        }

        throw new UnsupportedOperationException("Could not find a supported API (" + dataSource.name() + ").");
    }
}
