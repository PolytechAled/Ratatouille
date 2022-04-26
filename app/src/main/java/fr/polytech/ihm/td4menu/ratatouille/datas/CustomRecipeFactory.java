package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.List;
import java.util.Random;

public class CustomRecipeFactory extends RecipeFactory {
    private final String title;
    private final String origin;
    private final int cookingTime;
    private final List<RecipeCategory> categoryList;
    private final List<String> ingredients;

    public CustomRecipeFactory(String title, String origin, int cookingTime, List<RecipeCategory> categoryList, List<String> ingredients){
        this.title = title;
        this.origin = origin;
        this.cookingTime = cookingTime;
        this.categoryList = categoryList;
        this.ingredients = ingredients;
    }

    @Override
    public Recipe instantiate() {
        Recipe recipe = new Recipe(new Random().nextInt(), title);
        recipe.setOrigin(origin);
        recipe.setCookingTime(cookingTime);
        recipe.setCategoryList(categoryList);
        recipe.setIngredients(ingredients);
        Recipes.add(recipe);
        return recipe;
    }
}