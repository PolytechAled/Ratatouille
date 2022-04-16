package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.ArrayList;
import java.util.List;

public class Recipes {
    private List<Recipe> recipeList;

    public Recipes() {
        this.recipeList = new ArrayList<>();
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void setRecipe(Recipe recipe) {
        this.recipeList.add(recipe);
    }

    public Recipe get(int position){
        return this.recipeList.get(position);
    }

    public int size(){
        return this.recipeList.size();
    }
}
