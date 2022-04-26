package fr.polytech.ihm.td4menu.ratatouille.MVC;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Optional;

import fr.polytech.ihm.td4menu.ratatouille.datas.CustomRecipeFactory;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.RecipeCategory;
import fr.polytech.ihm.td4menu.ratatouille.datas.Week;

public class Model_Ratatouille extends Observable{
    private Map<Integer, Week> recipeList;
    private Recipe recipeShow;
    private int weekNumber;
    private Controller_Ratatouille controller_Ratatouille;
    private VIEW_TYPE updateType;
    private ViewGroup layout;

    public enum VIEW_TYPE{
        VIEW_LISTRECIPE,
        VIEW_DETAILSRECIPE;
    }

    public void setController(Controller_Ratatouille controller_Ratatouille) {
        this.controller_Ratatouille = controller_Ratatouille;
    }

    public Model_Ratatouille(Controller_Ratatouille controller_Ratatouille) {
        super();
        this.recipeList = new HashMap<>();
        this.controller_Ratatouille = controller_Ratatouille;
        this.recipeShow = null;
    }

    public void build(){
        List<Recipe> recipes = new ArrayList<>();

        List<RecipeCategory> categoryList = new ArrayList<>();
        categoryList.add(RecipeCategory.FIVE_VEGETABLE);
        categoryList.add(RecipeCategory.VEGAN);
        categoryList.add(RecipeCategory.BIO);

        List<String> ingredients = new ArrayList<>();
        ingredients.add("Tomate");
        ingredients.add("Fleur");
        ingredients.add("Patate");

        recipes.add(new CustomRecipeFactory("Recette1", "France", 75, categoryList,ingredients).instantiate());
        recipes.add(new CustomRecipeFactory("Recette2", "France", 15, categoryList,ingredients).instantiate());
        recipes.add(new CustomRecipeFactory("Recette3", "Belgique", 56, categoryList,ingredients).instantiate());
        recipes.add(new CustomRecipeFactory("Recette4", "Chine", 45, categoryList,ingredients).instantiate());
        recipes.add(new CustomRecipeFactory("Recette5", "Ecosse", 45, categoryList,ingredients).instantiate());
        recipes.add(new CustomRecipeFactory("Recette6", "Espagne", 15, categoryList,ingredients).instantiate());
        recipes.add(new CustomRecipeFactory("Recette7", "France", 10, categoryList,ingredients).instantiate());

        this.recipeList.put(0,new Week(recipes,0));

        this.updateType = VIEW_TYPE.VIEW_LISTRECIPE;

        setChanged();
        notifyObservers();
    }

    public Week getRecipeList(int weekNumber) {
        return recipeList.get(weekNumber);
    }

    public Optional<Recipe> getRecipe(int id, int weekNumber){
        Optional<Recipe> recipe = null;
        Week week = getRecipeList(weekNumber);
        return week.getRecipeList().stream().filter(recipe1 -> recipe1.getId() == id).findFirst();
    }

    public int size() {
        return recipeList.size();
    }

    public void addRecipe(int weekNumber, Recipe recipe) {
        Week week = recipeList.get(weekNumber);
        week.addRecipe(recipe);
        recipeList.put(weekNumber,week);
        setChanged();
        notifyObservers();
    }

    public void addWeek(int weekNumber,Week week){
        recipeList.put(weekNumber,week);
        setChanged();
        notifyObservers();
    }

    private int getMaxKeyList(){
        return this.recipeList.keySet().stream().max(Integer::compare).get();
    }

    public void deleteWeek(int weekNumber) {
        if (weekNumber < this.getMaxKeyList()) {
            recipeList.remove(weekNumber);
            setChanged();
            notifyObservers();
        }
    }

    public void deleteRecipe(int weekNumber, int id) {
        if (weekNumber < this.getMaxKeyList()) {
            Week week = recipeList.get(weekNumber);
            week.removeRecipe(id);
            recipeList.put(weekNumber,week);
            setChanged();
            notifyObservers();
        }
    }

    public void recipeClick(int recipeID, ViewGroup layout) {
        Week week = recipeList.get(weekNumber);
        this.recipeShow = week.getRecipeId(recipeID);
        this.updateType = VIEW_TYPE.VIEW_DETAILSRECIPE;
        this.layout = layout;
        setChanged();
        notifyObservers();
    }

    public ViewGroup getLayout() {
        return layout;
    }

    public Recipe getRecipeShow() {
        return recipeShow;
    }

    public void recipesWeek(int weekNumber){
        this.weekNumber = weekNumber;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public Recipe getRecipePosition(int adapterPosition) {
        Week week = this.recipeList.get(this.weekNumber);
        Recipe recipe = week.getRecipePosition(adapterPosition);
        return recipe;
    }

    public VIEW_TYPE getUpdateType() {
        return updateType;
    }
}
