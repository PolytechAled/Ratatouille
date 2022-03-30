package fr.polytech.ihm.td4menu.ratatouille.datas;

public class Recipe {

    /**
     * Cooking time in hour.
     */
    private int cookingTime;

    /**
     * Recipe name
     */
    private String name;

    public Recipe() {

    }

    public Recipe(String name, int cookingTime) {
        this.name = name;
        this.cookingTime = cookingTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public String getName() {
        return name;
    }
}
