package fr.polytech.ihm.td4menu.ratatouille;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
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

        @Override
        public void onClick(View view){
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
        // TODO Set right recipe name
        viewHolder.recipeName.setText("Recipe Name");
    }

    @Override
    public int getItemCount(){
        return 10;
    }
}
