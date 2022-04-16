package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Recipes {
    private static final Recipes instance = new Recipes();

    private Map<Integer, Recipe> recipeList;

    private Recipes() {
        this.recipeList = new HashMap<>();
    }

    public Collection<Recipe> getRecipeList() {
        return recipeList.values();
    }

    public static void add(Recipe recipe) {
        instance.recipeList.put(recipe.getId(), recipe);
    }

    public static Recipe get(int position){
        return instance.recipeList.get(position);
    }

    public static int size() {
        return instance.recipeList.size();
    }
}
