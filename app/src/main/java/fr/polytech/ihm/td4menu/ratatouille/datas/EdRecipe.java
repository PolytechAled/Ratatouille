package fr.polytech.ihm.td4menu.ratatouille.datas;

public class EdRecipe extends Recipe {
    public EdRecipe(int relId, String name, int cookingTime) {
        super((relId + "edamam").hashCode(), name, cookingTime);

        this.relId = relId;
    }
}
