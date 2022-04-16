package fr.polytech.ihm.td4menu.ratatouille.recipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    Recipes recipes = new Recipes();
    Context context;

    public RecipeAdapter(Recipes recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        View v;
        TextView recipeName;

        ViewHolder(View v){
            super(v);

            this.v = v;
            recipeName = v.findViewById(R.id.recipeName);

            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void display(Recipe recipe){
            recipeName.setText(recipe.getName());
        }

        @Override
        public void onClick(View view){
            int position = getLayoutPosition(); //gets item position

            Log.d("info", position + "");
            Toast.makeText(context,"Vous voulez voir la Recette : " + recipes.get(position).getName() , Toast.LENGTH_SHORT).show();

            // TODO : never null, why?
            FrameLayout frameLayout = view.findViewById(R.id.frame_layout_detail);

            if (frameLayout == null){
                Log.d("info","send value to the DetailActivity =>"+recipes.get(position).getName());
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                intent.putExtra(String.valueOf(Recipe.class), recipes.get(position));
                context.startActivity(intent);
            }
            else {
                Log.d("info","send value to the fragment =>"+recipes.get(position).getName());
                RecipeDetailsFragment detailFragment = new RecipeDetailsFragment();
                Bundle args = new Bundle();
                args.putSerializable(String.valueOf(Recipe.class), recipes.get(position));
                detailFragment.setArguments(args);
                FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_detail, detailFragment);
                fragmentTransaction.commit();
            }

        }

        @Override
        public boolean onLongClick(View v){
            return true;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recipe_view, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder viewHolder, int position){
        viewHolder.display(this.recipes.get(position));
    }

    @Override
    public int getItemCount(){
        return this.recipes.size();
    }
}