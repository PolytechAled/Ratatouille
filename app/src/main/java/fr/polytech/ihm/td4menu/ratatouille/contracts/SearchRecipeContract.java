package fr.polytech.ihm.td4menu.ratatouille.contracts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import fr.polytech.ihm.td4menu.ratatouille.SearchRecipeActivity;
import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;

public class SearchRecipeContract extends ActivityResultContract<Integer, Recipe> {
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, @NonNull Integer code) {
        Intent intent = new Intent(context, SearchRecipeActivity.class);
        return intent;
    }

    @Override
    public Recipe parseResult(int resultCode, @Nullable Intent result) {
        if (resultCode != Activity.RESULT_OK || result == null) {
            return null;
        }
        return (Recipe) result.getSerializableExtra("recipe");
    }
}
