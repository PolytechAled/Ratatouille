package fr.polytech.ihm.td4menu.ratatouille.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Day;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.recipe.OnButtonClickedListener;

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

        ViewHolder(View v){
            super(v);
            recipeName = v.findViewById(R.id.recipeName);

            v.setOnClickListener(clic ->{
                callBackActivity.onButtonClicked(model_ratatouille,getAbsoluteAdapterPosition());
            });
        }

        public void display(Recipe day){
            if(day != null)
                recipeName.setText(day.getName());
        }

        @Override
        public void onClick(View view){
            int position = getLayoutPosition();
            Log.d("info", position + "");
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
