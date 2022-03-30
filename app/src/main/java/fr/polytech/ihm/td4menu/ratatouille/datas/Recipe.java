package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.List;

public class Recipe {

    /**
     * Cooking time in hour.
     */
    private int cookingTime;

    /**
     * Recipe name
     */
    private String name;

    /**
     * Is a custom recipe made by the user.
     */
    private boolean isCustom;

    /**
     * The recipe origin
     */
    private String origin;

    /**
     * The image url.
     */
    private String imageUrl;

    /**
     * Step list.
     */
    private List<String> steps;

    /**
     * Recipe category.
     */
    private List<RecipeCategory> categoryList;

    public Recipe() {
        this.isCustom = false;
    }

    public Recipe(String name, String origin, int cookingTime) {
        this.name = name;
        this.origin = origin;
        this.cookingTime = cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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

    public String getOrigin() {
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
}
