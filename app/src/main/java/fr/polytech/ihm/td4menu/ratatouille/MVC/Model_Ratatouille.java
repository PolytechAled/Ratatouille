package fr.polytech.ihm.td4menu.ratatouille.MVC;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Optional;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.CustomRecipeFactory;
import fr.polytech.ihm.td4menu.ratatouille.datas.Day;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.RecipeCategory;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;
import fr.polytech.ihm.td4menu.ratatouille.datas.Week;
import fr.polytech.ihm.td4menu.ratatouille.recipe.ListRecipeActivity;

public class Model_Ratatouille extends Observable{
    private Map<Integer, Week> recipeList;
    private int recipeShow;
    private int weekNumber;
    private int dayNumber;
    private Controller_Ratatouille controller_Ratatouille;
    private VIEW_TYPE updateType;
    private ViewGroup layout;
    private List<Recipe> recipes;
    private List<RecipeCategory> categoryList;

    public void addRecipe(Recipe recipe, int moment) {
        if(moment == 0)
            this.recipeList.get(this.weekNumber).getDay(this.dayNumber).setFirstRecipe(recipe);
        else if(moment == 1)
            this.recipeList.get(this.weekNumber).getDay(this.dayNumber).setSecondRecipe(recipe);
        else
            Log.d("info","ERREUR : moment inconnu");

        Recipes.setNewRecipe(null);

        this.updateType = VIEW_TYPE.VIEW_LISTRECIPE;
        setChanged();
        notifyObservers();

        this.updateType = VIEW_TYPE.VIEW_DETAILSRECIPE;
        setChanged();
        notifyObservers();
    }

    public void setDayNumber(int position) {
        this.dayNumber = position;
    }

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
        this.recipeShow = 0;
    }

    public void build(){
        Log.d("info","BUILD DATASET");
        recipes = new ArrayList<>();

        categoryList = new ArrayList<>();
        categoryList.add(RecipeCategory.FAST_FOOD);
        categoryList.add(RecipeCategory.VEGAN);
        categoryList.add(RecipeCategory.BIO);

        List<String> ingredients = new ArrayList<>();
        ingredients.add("Tomate");
        ingredients.add("Fleur");
        ingredients.add("Patate");

        List<String> instructions = new ArrayList<>();
        instructions.add("Faire ça");
        instructions.add("Faire un autre ça");
        instructions.add("Faire un autre de l'autre ça");

        recipes.add(new CustomRecipeFactory("Recette1", "France", 75, categoryList,ingredients,instructions).instantiate());
        recipes.add(new CustomRecipeFactory("Recette2", "France", 15, categoryList,ingredients,instructions).instantiate());
        recipes.add(new CustomRecipeFactory("Recette3", "Belgique", 56, categoryList,ingredients,instructions).instantiate());
        recipes.add(new CustomRecipeFactory("Recette4", "Chine", 45, categoryList,ingredients,instructions).instantiate());
        recipes.add(new CustomRecipeFactory("Recette5", "Ecosse", 45, categoryList,ingredients,instructions).instantiate());
        recipes.add(new CustomRecipeFactory("Recette6", "Espagne", 15, categoryList,ingredients,instructions).instantiate());
        recipes.add(new CustomRecipeFactory("Recette7", "France", 10, categoryList,ingredients,instructions).instantiate());

        this.recipeList.put(0,new Week(Arrays.asList(new Day(0, recipes.get(0), null), new Day(1, recipes.get(0), recipes.get(2))),0));

        this.updateType = VIEW_TYPE.VIEW_LISTRECIPE;

        setChanged();
        notifyObservers();
    }

    public Week getWeek(int weekNumber) {
        return recipeList.get(weekNumber);
    }

    public int size() {
        return recipeList.size();
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

    public void addARecipe(Recipe recipe, int day, int moment){
        if(moment == 0)
            this.recipeList.get(this.weekNumber).getDay(day).setFirstRecipe(recipe);
        else if(moment == 1)
            this.recipeList.get(this.weekNumber).getDay(day).setSecondRecipe(recipe);
        this.updateType = VIEW_TYPE.VIEW_LISTRECIPE;

        setChanged();
        notifyObservers();
    }

    public void deleteRecipe(int weekNumber, int id) {
        if (weekNumber < this.getMaxKeyList()) {
            Week week = recipeList.get(weekNumber);
            //week.removeRecipe(id);
            //recipeList.put(weekNumber,week);
            setChanged();
            notifyObservers();
        }
    }

    public void recipeClick(int recipeID, ViewGroup layout) {
        this.recipeShow = recipeID;
        this.updateType = VIEW_TYPE.VIEW_DETAILSRECIPE;
        this.layout = layout;
        setChanged();
        notifyObservers();
    }

    public void dayClick(int dayId) {
        Recipes.setDay(recipeList.get(weekNumber).getDay(dayId));
    }

    public ViewGroup getLayout() {
        return layout;
    }

    public int getRecipeShow() {
        return recipeShow;
    }

    public void recipesWeek(int weekNumber){
        this.weekNumber = weekNumber;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public VIEW_TYPE getUpdateType() {
        return updateType;
    }

    public List<RecipeCategory> getRecipeCategories(){
        return categoryList;
    }
}
