package fr.polytech.ihm.td4menu.ratatouille.datas;

public class EdRecipe extends Recipe {
    public EdRecipe(int relId, String name) {
        super((relId + "edamam").hashCode(), name);

        this.relId = relId;
    }
}
