package fr.polytech.ihm.td4menu.ratatouille.datas.recipe;

import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;

public class EdRecipe extends Recipe {
    public EdRecipe(int relId, String name) {
        super((relId + "edamam").hashCode(), name);

        this.relId = relId;
    }
}
