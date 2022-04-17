package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipes {
    private static final Recipes instance = new Recipes();

    private Map<Integer, Recipe> recipeList;

    private Recipes() {
        this.recipeList = new HashMap<>();
    }

    public static Collection<Recipe> getRecipeList() {
        return instance.recipeList.values();
    }

    public static void add(Recipe recipe) {
        instance.recipeList.put(recipe.getId(), recipe);
    }

    public static Recipe get(int id){
        return instance.recipeList.get(id);
    }

    public static int size() {
        return instance.recipeList.size();
    }

    public static void addRecipeList(List<Recipe> recipes){
        instance.recipeList = null;
        for(int i = 0; i < recipes.size(); i++){
            instance.recipeList.put(recipes.get(i).getId(), recipes.get(i));
        }
    }
}
