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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.MVC.View_ListRecipe;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    Context context;
    List<Recipe> recipeList;
    View_ListRecipe view_listRecipe;
    Model_Ratatouille model_ratatouille;

    public RecipeAdapter(Context context, View_ListRecipe view_listRecipe, Model_Ratatouille model_ratatouille) {
        this.model_ratatouille = model_ratatouille;
        this.context = context;
        this.recipeList = new ArrayList<>();
        List<Recipe> list = model_ratatouille.getRecipeList(model_ratatouille.getWeekNumber()).getRecipeList();
        this.recipeList.addAll(list);
        this.view_listRecipe = view_listRecipe;
    }

    public void updateModel(Model_Ratatouille model) {
        this.model_ratatouille = model;
    }

    public void refresh(Model_Ratatouille model) {
        this.model_ratatouille = model;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        View v;
        TextView recipeName;

        ViewHolder(View v){
            super(v);
            ConstraintLayout layoutItem;
            int positionAdapter = getLayoutPosition();
/*
            this.v = v;
            recipeName = v.findViewById(R.id.recipeName);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
      */

            //(1) : Réutilisation des layouts
            //layoutItem = (ConstraintLayout) (v == null ? inflater.inflate(R.layout.view_adapter, parent, false) : view);
            //layoutItem = ((ConstraintLayout) v==null?v.findViewById(R.layout.fragment_list_recipe) : v);

            //(2) : Récupération des TextView de notre layout
            recipeName = v.findViewById(R.id.recipeName);

            //(3) : Renseignement des valeurs
            //Recipe recipe = model_ratatouille.getRecipePosition(getAdapterPosition());

            //recipeName.setText( recipe.getName()+"" );

            //écouter si clic sur la vue
            v.setOnClickListener( clic ->  view_listRecipe.onClickItem(model_ratatouille.getRecipePosition(getAdapterPosition()).getId()));
        }

        public void display(Recipe recipe){
            if(recipe != null)
                recipeName.setText(recipe.getName());
        }

        @Override
        public void onClick(View view){
            int position = getLayoutPosition(); //gets item position

            Log.d("info", position + "");
            Toast.makeText(context,"Vous voulez voir la Recette : " + recipeList.get(position).getName() , Toast.LENGTH_SHORT).show();

            // Informe le model de la recette selectionné
            //model_ratatouille.recipeClick(recipeList.get(position).getId());


            /*
            // TODO : never null, why?
            FrameLayout frameLayout = view.findViewById(R.id.frame_layout_detail);

            if (frameLayout == null){
                Log.d("info","send value to the DetailActivity =>"+recipeList.get(position).getName());
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                intent.putExtra(String.valueOf(Recipe.class), recipeList.get(position));
                context.startActivity(intent);
            }
            else {
                Log.d("info","send value to the fragment =>"+recipeList.get(position).getName());
                RecipeDetailsFragment detailFragment = new RecipeDetailsFragment();
                Bundle args = new Bundle();
                args.putSerializable(String.valueOf(Recipe.class), recipeList.get(position));
                detailFragment.setArguments(args);
                FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_detail, detailFragment);
                fragmentTransaction.commit();
            }


 */
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
        viewHolder.display(recipeList.get(position));
    }

    @Override
    public int getItemCount(){
        return recipeList.size();
    }
}