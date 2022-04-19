package fr.polytech.ihm.td4menu.ratatouille.datas;

import org.json.JSONException;

import java.io.IOException;

public abstract class RecipeFactory {

    public abstract Recipe instantiate() throws JSONException, IOException;
}
