package fr.polytech.ihm.td4menu.ratatouille.datas;

import org.json.JSONException;

import java.io.IOException;

public abstract class RecipeApi {
    public abstract Recipe getRecipe(int relId) throws JSONException, IOException;
}
