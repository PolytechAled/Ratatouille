package fr.polytech.ihm.td4menu.ratatouille.recipe;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.MVC.View_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> recipeList;
    private View_Ratatouille view_ratatouille;
    private Model_Ratatouille model_ratatouille;
    private OnButtonClickedListener callBackActivity;

    public RecipeAdapter(Context context, View_Ratatouille view_ratatouille, Model_Ratatouille model_ratatouille, OnButtonClickedListener callBackActivity) {
        this.model_ratatouille = model_ratatouille;
        this.context = context;
        this.recipeList = new ArrayList<>();
        List<Recipe> list = model_ratatouille.getRecipeList(model_ratatouille.getWeekNumber()).getRecipeList();
        this.recipeList.addAll(list);
        this.view_ratatouille = view_ratatouille;
        this.callBackActivity = callBackActivity;
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
            //v.setOnClickListener( clic ->  view_ratatouille.onClickItem(model_ratatouille.getRecipePosition(getAdapterPosition()).getId()));
            //v.setOnClickListener(this);
            v.setOnClickListener(clic ->{
                callBackActivity.onButtonClicked(model_ratatouille,getAdapterPosition());
            });
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