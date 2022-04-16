package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.List;

public class Week {
    List<Recipe> recipeList;

    public Week(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void addRecipe(Recipe recipe){
        recipeList.add(recipe);
    }

    public void removeRecipe(Recipe recipe){
        recipeList.remove(recipe);
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
