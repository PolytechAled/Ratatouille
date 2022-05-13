package fr.polytech.ihm.td4menu.ratatouille.datas.factory;

import org.json.JSONException;

import java.io.IOException;

import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;

public abstract class RecipeFactory {

    public abstract Recipe instantiate() throws JSONException, IOException;
}
