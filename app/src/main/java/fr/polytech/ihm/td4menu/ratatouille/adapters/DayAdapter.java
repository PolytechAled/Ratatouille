package fr.polytech.ihm.td4menu.ratatouille.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.polytech.ihm.td4menu.ratatouille.mvc.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Day;
import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.setOfRecipe.OnButtonClickedListener;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder>{


    private Day day;
    private Model_Ratatouille model_ratatouille;
    private OnButtonClickedListener callBackActivity;

    public DayAdapter(Context context, Model_Ratatouille model_ratatouille, int dayId, OnButtonClickedListener callBackActivity) {
        this.model_ratatouille = model_ratatouille;
        this.day = model_ratatouille.getWeek(model_ratatouille.getWeekNumber()).getDay(dayId);
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
        TextView recipeName;
        Recipe recipe;

        ViewHolder(View v){
            super(v);
            recipeName = v.findViewById(R.id.recipeName);

            v.setOnClickListener(clic ->{
                if (recipe != null)
                    callBackActivity.onButtonClicked(model_ratatouille,day,getAbsoluteAdapterPosition());
            });
        }

        public void display(Recipe recipe){
            this.recipe = recipe;

            if(recipe != null)
                recipeName.setText(recipe.getName());
            else {
                recipeName.setText("No recipe here");
                itemView.findViewById(R.id.recipeCLayout).setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
            }
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
    public DayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recipe_view, parent, false);
        return new DayAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapter.ViewHolder viewHolder, int position){
        viewHolder.display(day.get(position));
    }

    @Override
    public int getItemCount(){
        return day.getAll().size();
    }

}
