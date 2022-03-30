package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.ArrayList;
import java.util.List;

public class Recipes {

    private static final Recipes instance = new Recipes();

    private List<Recipe> recipeList;

    private Recipes() {
        this.recipeList = new ArrayList<>();
    }

    public static Recipe get(int id) {
        return instance.recipeList.get(id);
    }



}
