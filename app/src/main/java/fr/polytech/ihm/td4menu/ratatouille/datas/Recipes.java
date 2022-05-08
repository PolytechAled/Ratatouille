package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Recipes {
    private static final Recipes instance = new Recipes();

    private Map<Integer, Recipe> recipeList;
    private Day day;
    private Recipe newRecipe;
    private int moment;

    private Recipes() {
        this.recipeList = new HashMap<>();
    }

    public static List<Recipe> getRecipeList() {
        return instance.recipeList.values().stream().collect(Collectors.toList());
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

    public static Day getDay() {
        return instance.day;
    }

    public static void setDay(Day da) {
        instance.day = da;
    }

    public static Recipe getNewRecipe() {
        return instance.newRecipe;
    }

    public static void setNewRecipe(Recipe newRecipe) {
        instance.newRecipe = newRecipe;
    }

    /**
     * MIDI ou SOIR ? 0 ou 1 ?
     * @param moment
     */
    public static void setMoment(int moment) {
        instance.moment = moment;
    }

    public static int getMoment() {
        return instance.moment;
    }
}
