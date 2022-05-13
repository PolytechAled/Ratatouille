package fr.polytech.ihm.td4menu.ratatouille.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.mvc.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.RecipeCategory;


public class RecipeDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private List<RecipeCategory> recipeCategories;
    private Model_Ratatouille model_ratatouille;

    // Constructor
    public RecipeDetailsAdapter(Context c, List<RecipeCategory> recipeCategoryList) {
        this.recipeCategories = recipeCategoryList;
        mContext = c;
    }

    public int getCount() {
        if(recipeCategories != null)
            return recipeCategories.size();
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(recipeCategories.get(position).getCategoryUri());
        return imageView;
    }

}
