package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.Random;

public class CustomRecipeFactory extends RecipeFactory {
    private final String title;
    private final String origin;
    private final int cookingTime;

    public CustomRecipeFactory(String title, String origin, int cookingTime){
        this.title = title;
        this.origin = origin;
        this.cookingTime = cookingTime;
    }

    @Override
    public Recipe instantiate() {
        Recipe recipe = new Recipe(new Random().nextInt(), title);
        recipe.setOrigin(origin);
        recipe.setCookingTime(cookingTime);
        Recipes.add(recipe);
        return recipe;
    }
}
