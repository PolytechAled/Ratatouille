package fr.polytech.ihm.td4menu.ratatouille.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.stream.Collectors;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;

public class RecipeListAdapter extends BaseAdapter {

    private List<Recipe> recipes;
    private List<Recipe> filteredList;
    private LayoutInflater layoutInflater;
    private Context context;

    public RecipeListAdapter(Context context,  List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
        this.filteredList = recipes;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int i) {
        return filteredList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Recipe r = filteredList.get(i);

        if (view == null)
        {
            view = layoutInflater.inflate(R.layout.recipe_view, null);
        }

        TextView recipeName = view.findViewById(R.id.recipeName);
        recipeName.setText(r.getName());

        return view;
    }

    public void filter(String filter) {
        filteredList = recipes.stream().filter(s -> s.getName().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
        notifyDataSetChanged();
    }
}
