package fr.polytech.ihm.td4menu.ratatouille.MVC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Optional;

import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Week;

public class Model_Ratatouille extends Observable {
    private Map<Integer, Week> recipeList;
    private Controller_Ratatouille controller_Ratatouille;

    public void setController(Controller_Ratatouille controller_Ratatouille) {
        this.controller_Ratatouille = controller_Ratatouille;
    }

    public Model_Ratatouille(Controller_Ratatouille controller_Ratatouille) {
        this.recipeList = new HashMap<>();
        this.controller_Ratatouille = controller_Ratatouille;
    }

    public void build(){
        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe(0, "Recette1", "France", 75));
        recipes.add(new Recipe(1, "Recette2", "France", 15));
        recipes.add(new Recipe(2, "Recette3", "Belgique", 56));
        recipes.add(new Recipe(3, "Recette4", "Chine", 45));
        recipes.add(new Recipe(4, "Recette5", "Ecosse", 45));
        recipes.add(new Recipe(5, "Recette6", "Espagne", 15));
        recipes.add(new Recipe(6, "Recette7", "France", 10));

        this.recipeList.put(0,new Week(recipes));
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

    public void add(int weekNumber, Recipe recipe) {
        Week week = recipeList.get(weekNumber);
        week.addRecipe(recipe);
        recipeList.put(weekNumber,week);
        setChanged();
        notifyObservers();
    }

    private int getMaxKeyList(){
        return this.recipeList.keySet().stream().max(Integer::compare).get();
    }

    public void removeWeek(int weekNumber) {
        if (weekNumber < this.getMaxKeyList()) {
            recipeList.remove(weekNumber);
            setChanged();
            notifyObservers();
        }
    }

    public void removeARecipe(int weekNumber, Recipe recipe) {
        if (weekNumber < this.getMaxKeyList()) {
            Week week = recipeList.get(weekNumber);
            week.removeRecipe(recipe);
            recipeList.put(weekNumber,week);
            setChanged();
            notifyObservers();
        }
    }
}
