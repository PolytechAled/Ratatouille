package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.List;

public class Week {
    List<Recipe> recipeList;
    int weekNumber;

    public Week(List<Recipe> recipeList,int weekNumber) {
        this.recipeList = recipeList;
        this.weekNumber = weekNumber;
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

    public Recipe getRecipeId(int id){
        for(int i = 0; i < this.recipeList.size(); i++){
            if(this.recipeList.get(i).getId() == id){
                return this.recipeList.get(i);
            }
        }
        return null;
    }

    public Recipe getRecipePosition(int position){
        return this.recipeList.get(position);
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
