package fr.polytech.ihm.td4menu.ratatouille.datas;

public class SpRecipe extends Recipe {

    public SpRecipe(int id, String name, int cookingTime) {
        super((id + "spoonacular").hashCode(), name, cookingTime);
    }
}
