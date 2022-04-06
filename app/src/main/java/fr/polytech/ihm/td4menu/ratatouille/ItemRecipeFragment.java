package fr.polytech.ihm.td4menu.ratatouille;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.polytech.ihm.td4menu.ratatouille.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class ItemRecipeFragment extends Fragment {
    public ItemRecipeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecipeAdapter adapter = new RecipeAdapter();
        RecyclerView recyclerView = findViewById(R.id.recipeList);
    }
}