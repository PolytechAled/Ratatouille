package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class Recipe implements Serializable{

    /**
     * Unique id.
     */
    protected final int id;
    protected int relId;

    protected boolean populated;

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
    protected String origin;
    protected boolean hasOrigin;

    /**
     * The image url.
     */
    protected String imageUrl;

    /**
     * Step list.
     */
    protected List<String> steps;
    /**
     * Ingredient list.
     */
    protected List<String> ingredients;

    /**
     * Recipe category.
     */
    protected List<RecipeCategory> categoryList;

    public Recipe(int id, String name) {
        this.id = relId = id;
        this.name = name;
        this.hasOrigin = false;
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
        this.origin = origin;
        this.hasOrigin = true;
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients){
        this.ingredients = ingredients;
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

    public boolean isPopulated() {
        return populated;
    }

    public void setPopulated(boolean populated) {
        this.populated = populated;
    }

    public Optional<String> getOrigin() {
        return hasOrigin ? Optional.of(origin) : Optional.empty();
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
                ", ingredient=" + ingredients +
                '}';
    }
}
