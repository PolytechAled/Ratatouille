package fr.polytech.ihm.td4menu.ratatouille.recipe;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Controller_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.MVC.View_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;

public class ListRecipeFragment extends Fragment {
    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;
    private OnButtonClickedListener callBackActivity;
    //private Model_Ratatouille model;

    public ListRecipeFragment(){
        //this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_list_recipe, null);

/*
        new Thread(() -> {
            Recipes.add(new CustomRecipeFactory("Recette1", "France", 75).instantiate());
            Recipes.add(new CustomRecipeFactory("Recette2", "France", 15).instantiate());
            Recipes.add(new CustomRecipeFactory("Recette3", "Belgique", 56).instantiate());
            Recipes.add(new CustomRecipeFactory("Recette4", "Chine", 45).instantiate());
            Recipes.add(new CustomRecipeFactory("Recette5", "Ecosse", 45).instantiate());
            Recipes.add(new CustomRecipeFactory("Recette6", "Espagne", 15).instantiate());
            Recipes.add(new CustomRecipeFactory("Recette7", "France", 10).instantiate());

            try {
                Recipes.add(new APIRecipeFactory(DataSource.SPOONACULAR, 716429).instantiate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
*/

        //create VIEW with XML layout
        View_Ratatouille view = new View_Ratatouille( getActivity().getApplicationContext(), (ViewGroup) result, callBackActivity);
        Model_Ratatouille model = new Model_Ratatouille(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW

        // TODO : Just for exemple
        model.build();

        Controller_Ratatouille controller = new Controller_Ratatouille( model,view );
        model.setController(controller);
        view.setListener( controller );


        return result;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        createCallbackToParentActivity();
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