package fr.polytech.ihm.td4menu.ratatouille.datas;

public class SpRecipe extends Recipe {

    public SpRecipe(int relId, String name, int cookingTime) {
        super((relId + "spoonacular").hashCode(), name, cookingTime);

        this.relId = relId;
    }
}
