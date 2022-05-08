package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomRecipeFactory extends RecipeFactory {
    private final String title;
    private final String origin;
    private final int cookingTime;
    private final List<RecipeCategory> categoryList;
    private final List<String> ingredients;
    private final List<String> instructions;

    public CustomRecipeFactory(String title, String origin, int cookingTime, List<RecipeCategory> categoryList, List<String> ingredients, List<String> instructions){
        this.title = title;
        this.origin = origin;
        this.cookingTime = cookingTime;
        this.categoryList = categoryList;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public CustomRecipeFactory(String nameText, String fait_maison, int timeVal, String ingredientsText, String stepText) {
        super();
        this.ingredients = new ArrayList<>();
        this.instructions = new ArrayList<>();
        this.categoryList = new ArrayList<>();

        this.title = nameText;
        this.origin = fait_maison;
        this.cookingTime = timeVal;
        this.ingredients.add(ingredientsText);
        this.instructions.add(stepText);
    }

    @Override
    public Recipe instantiate() {
        Recipe recipe = new Recipe(new Random().nextInt(), title);
        recipe.setOrigin(origin);
        recipe.setCookingTime(cookingTime);
        recipe.setCategoryList(categoryList);
        recipe.setIngredients(ingredients);
        recipe.setSteps(instructions);
        Recipes.add(recipe);
        return recipe;
    }
}
