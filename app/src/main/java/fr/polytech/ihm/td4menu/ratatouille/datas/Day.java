package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.Arrays;
import java.util.List;

public class Day {

    private int day;
    private Recipe firstRecipe;
    private Recipe secondRecipe;

    public Day(int day, Recipe firstRecipe, Recipe secondRecipe) {
        this.day = day;
        this.firstRecipe = firstRecipe;
        this.secondRecipe = secondRecipe;
    }

    public int getDay() {
        return day;
    }

    public String getDayString() {
        switch (day)
        {
            case 0:
                return "lundi";
            case 1:
                return "mardi";
            case 2:
                return "mercredi";
            case 3:
                return "jeudi";
            case 4:
                return "vendredi";
            case 5:
                return "samedi";
            case 6:
                return "dimanche";
            default:
                return "Inconnu";
        }
    }

    public Recipe getFirstRecipe() {
        return firstRecipe;
    }

    public Recipe get(int id) {
        return id == 0 ? firstRecipe : secondRecipe;
    }

    public Recipe getSecondRecipe() {
        return secondRecipe;
    }

    public List<Recipe> getAll() {
        return Arrays.asList(firstRecipe, secondRecipe);
    }

    public void setFirstRecipe(Recipe firstRecipe) {
        this.firstRecipe = firstRecipe;
    }

    public void setSecondRecipe(Recipe secondRecipe) {
        this.secondRecipe = secondRecipe;
    }
}
