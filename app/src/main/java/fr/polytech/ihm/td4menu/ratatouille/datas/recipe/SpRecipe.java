package fr.polytech.ihm.td4menu.ratatouille.datas.recipe;

import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;

public class SpRecipe extends Recipe {

    public SpRecipe(int relId, String name) {
        super((relId + "spoonacular").hashCode(), name);

        this.relId = relId;
    }
}
