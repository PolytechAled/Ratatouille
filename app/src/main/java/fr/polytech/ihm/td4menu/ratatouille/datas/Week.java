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

    public void removeRecipe(int id){
        for(int i = 0; i < this.recipeList.size(); i++) {
            if(this.recipeList.get(i).getId() == id) {
                recipeList.remove(i);
                break;
            }
        }
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public Recipe getRecipe(int id){
        for(int i = 0; i < this.recipeList.size(); i++){
            if(this.recipeList.get(i).getId() == id){
                return this.recipeList.get(i);
            }
        }
        return null;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
