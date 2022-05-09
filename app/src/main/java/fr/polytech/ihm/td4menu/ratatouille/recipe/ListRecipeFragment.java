package fr.polytech.ihm.td4menu.ratatouille.recipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Controller_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.MVC.View_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.MenuGenerationActivity;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.APIRecipeFactory;
import fr.polytech.ihm.td4menu.ratatouille.datas.CustomRecipeFactory;
import fr.polytech.ihm.td4menu.ratatouille.datas.DataSource;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;

public class ListRecipeFragment extends Fragment {
    private OnButtonClickedListener callBackActivity;

    View_Ratatouille view;
    Model_Ratatouille model;
    Controller_Ratatouille controller;

    public ListRecipeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_list_recipe, null);

        //create VIEW with XML layout
        view = new View_Ratatouille( getActivity().getApplicationContext(), (ViewGroup) result, callBackActivity);
        model = new Model_Ratatouille(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW

        // TODO : Just for exemple
        model.build();
        //this.delay(3);
        controller = new Controller_Ratatouille( model,view );
        model.setController( controller );
        view.setListener( controller );

        return result;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        createCallbackToParentActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        view.addRecipe();
    }

    // Create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            callBackActivity = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }
}