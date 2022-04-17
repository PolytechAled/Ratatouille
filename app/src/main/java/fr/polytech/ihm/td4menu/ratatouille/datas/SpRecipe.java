package fr.polytech.ihm.td4menu.ratatouille.datas;

public class SpRecipe extends Recipe {

    public SpRecipe(int relId, String name) {
        super((relId + "spoonacular").hashCode(), name);

        this.relId = relId;
    }
}
