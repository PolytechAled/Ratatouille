package fr.polytech.ihm.td4menu.ratatouille;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListRecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListRecipeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        RecipeAdapter adapter = new RecipeAdapter();
        RecyclerView recyclerView = findViewById(R.id.recipeList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        return inflater.inflate(R.layout.fragment_list_recipe, container, false);
    }
}