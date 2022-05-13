package fr.polytech.ihm.td4menu.ratatouille.recipe.create.custom.MVC;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.recipe.create.custom.CreateCustomRecipe;

public class View_CustomRecipe implements Observer {
    private Controller_CustomRecipe controller_customRecipe;
    private CreateCustomRecipe layout;

    public View_CustomRecipe(Controller_CustomRecipe controller_customRecipe, CreateCustomRecipe layout) {
        this.controller_customRecipe = controller_customRecipe;
        this.layout = layout;
    }

    public void onClickTakeAPhoto(Bitmap bitmap){
        this.controller_customRecipe.addPicture(bitmap);
    }

    public void onClickName(String name){
        this.controller_customRecipe.addName(name);
    }

    public void setListener(Controller_CustomRecipe controller_customRecipe) {
        this.controller_customRecipe = controller_customRecipe;
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.d("info","Create Custom Recipe Update");
        Model_CustomRecipe model_customRecipe = (Model_CustomRecipe) observable;

        // Recuperation des elements
        TextView name = layout.findViewById(R.id.customRecipeName);
        ImageView image = layout.findViewById(R.id.customRecipeImage);

        // update des elements
        image.setImageBitmap(model_customRecipe.getPicture());
    }
}
