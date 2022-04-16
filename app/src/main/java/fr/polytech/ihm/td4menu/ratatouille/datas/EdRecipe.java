package fr.polytech.ihm.td4menu.ratatouille.datas;

public class EdRecipe extends Recipe {
    public EdRecipe(int id, String name, int cookingTime) {
        super((id + "edamam").hashCode(), name, cookingTime);
    }
}
