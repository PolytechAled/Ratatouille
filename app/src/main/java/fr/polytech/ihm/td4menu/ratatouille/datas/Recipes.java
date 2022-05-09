package fr.polytech.ihm.td4menu.ratatouille.datas;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
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
    private List<Recipe> recipeListGenerate;
    private Week weekGenerate;
    private Week week;
    private String origin;
    private String diet;

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

    public static Week getWeek(){
        return instance.week;
    }

    public static void setWeek(Week week){
        instance.week = week;
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

    public static List<Recipe> getRecipeListGenerate() {
        return instance.recipeListGenerate;
    }

    public static void setRecipeListGenerate(List<Recipe> recipeListGenerate) {
        instance.recipeListGenerate = recipeListGenerate;
        for(Recipe recipe : recipeListGenerate)
            add(recipe);
        instance.buildWeek();
    }

    public static Week getWeekGenerate() {
        return instance.weekGenerate;
    }

    public static void setWeekGenerate(Week weekGenerate) {
        instance.weekGenerate = weekGenerate;
    }

    public void buildWeek(){
        Spoonacular spoonacular = new Spoonacular();
        new Thread(()-> {
            List<Day> dayList = new ArrayList<>();
            int j = 0;
            for (int i = 0; i < instance.recipeListGenerate.size(); i++) {
                Recipe recipe1 = null;
                Recipe recipe2 = null;
                Day day;

                try {
                    recipe1 = spoonacular.populateRecipe(instance.recipeListGenerate.get(i).getRelId());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (i < instance.recipeListGenerate.size() - 1) {
                    try {
                        recipe2 = spoonacular.populateRecipe(instance.recipeListGenerate.get(++i).getRelId());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                day = new Day(j++, recipe1, recipe2);
                dayList.add(day);
            }
            instance.setWeekGenerate(new Week(dayList,0));
        }).start();
    }

    public static String getOrigin() {
        return instance.origin;
    }

    public static void setOrigin(String origin) {
        instance.origin = origin;
    }

    public static String getDiet() {
        return instance.diet;
    }

    public static void setDiet(String diet) {
        instance.diet = diet;
    }
}
