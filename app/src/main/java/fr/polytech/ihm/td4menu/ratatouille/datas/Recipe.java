package fr.polytech.ihm.td4menu.ratatouille.datas;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class Recipe implements Serializable {

    /**
     * Unique id.
     */
    protected final int id;
    protected int relId;

    /**
     * Cooking time in minutes.
     */
    protected int cookingTime;

    /**
     * Recipe name
     */
    protected String name;

    /**
     * Is a custom recipe made by the user.
     */
    protected boolean isCustom;

    /**
     * The recipe origin
     */
    protected Optional<String> origin;

    /**
     * The image url.
     */
    protected String imageUrl;

    /**
     * Step list.
     */
    protected List<String> steps;

    /**
     * Recipe category.
     */
    protected List<RecipeCategory> categoryList;

    public Recipe(int id, String name, String origin, int cookingTime) {
        this.id = relId = id;
        this.name = name;
        this.cookingTime = cookingTime;
        this.origin = Optional.of(origin);
    }

    public Recipe(int id, String name, int cookingTime) {
        this.id = relId = id;
        this.name = name;
        this.cookingTime = cookingTime;
        this.origin = Optional.empty();
    }

    public static Recipe instantiate(DataSource source, int relId) throws JSONException, IOException {
        switch (source){
            case RATATOUILLE:
                return Recipes.get(relId);

            case SPOONACULAR:
                Spoonacular recipeApi = new Spoonacular();

                return recipeApi.getRecipe(relId);

            case EDAMAM:
                throw new UnsupportedOperationException("Edamam API is not implemented yet.");
        }

        throw new UnsupportedOperationException("Could not find a supported API (" + source.name() + ").");
    }

    public int getId() {
        return id;
    }

    public int getRelId() {
        return relId;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = Optional.of(origin);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public void setCategoryList(List<RecipeCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public List<String> getSteps() {
        return steps;
    }

    public String getName() {
        return name;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public Optional<String> getOrigin() {
        return origin;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<RecipeCategory> getCategoryList() {
        return categoryList;
    }

    public static SpRecipe newSpRecipe(int relId, String title, int cookingTime){
        return new SpRecipe((relId + DataSource.SPOONACULAR.name()).hashCode(), title, cookingTime);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", cookingTime=" + cookingTime +
                ", name='" + name + '\'' +
                ", isCustom=" + isCustom +
                ", origin='" + origin + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", steps=" + steps +
                ", categoryList=" + categoryList +
                '}';
    }
}
