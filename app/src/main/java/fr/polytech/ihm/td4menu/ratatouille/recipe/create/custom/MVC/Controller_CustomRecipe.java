package fr.polytech.ihm.td4menu.ratatouille.recipe.create.custom.MVC;

import android.graphics.Bitmap;

public class Controller_CustomRecipe {
    Model_CustomRecipe model_customRecipe;
    View_CustomRecipe view_customRecipe;

    public Controller_CustomRecipe(Model_CustomRecipe model_customRecipe, View_CustomRecipe view_customRecipe) {
        this.model_customRecipe = model_customRecipe;
        this.view_customRecipe = view_customRecipe;
    }

    public void addName(String name){
        model_customRecipe.setName(name);
    }

    public void addPicture(Bitmap bitmap){
        model_customRecipe.setPicture(bitmap);
    }
}
