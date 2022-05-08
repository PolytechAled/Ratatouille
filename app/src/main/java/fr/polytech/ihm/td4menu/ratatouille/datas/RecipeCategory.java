package fr.polytech.ihm.td4menu.ratatouille.datas;

import android.net.Uri;

import fr.polytech.ihm.td4menu.ratatouille.R;

public enum RecipeCategory {
    VEGAN,
    FAST_FOOD,
    BIO,
    FIVE_VEGETABLE,
    VEGETARIAN;

    public int getCategoryUri(){
        switch (this){
            case FAST_FOOD:
                return R.drawable.ic_fast_food;
            case VEGAN:
                return R.drawable.ic_vegan;
            case BIO:
                return R.drawable.ic_ab;
        }
        return 0;
    }
}
