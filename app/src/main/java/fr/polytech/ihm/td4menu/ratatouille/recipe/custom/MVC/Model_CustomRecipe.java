package fr.polytech.ihm.td4menu.ratatouille.recipe.custom.MVC;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Observable;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Controller_Ratatouille;

public class Model_CustomRecipe extends Observable {
    private Controller_CustomRecipe controller_customRecipe;
    private Bitmap picture;
    private String name;

    public Model_CustomRecipe(Controller_CustomRecipe controller_customRecipe) {
        super();
        this.controller_customRecipe = controller_customRecipe;
        this.picture = null;
        this.name = null;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public void setController(Controller_CustomRecipe controller_customRecipe) {
        this.controller_customRecipe = controller_customRecipe;
    }
}