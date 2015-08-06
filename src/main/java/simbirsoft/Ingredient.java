package simbirsoft;

public class Ingredient {
    private final String ingredientsName;
    private Integer ingredientCount;


    public Ingredient(String ingredientsName, Integer ingredientCount) {
        this.ingredientsName = ingredientsName;
        this.ingredientCount = ingredientCount;
    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public Integer getIngredientCount() {
        return ingredientCount;
    }

    public void setIngredientCount(Integer count) {
        ingredientCount = count;
    }

    @Override
    public String toString() {
        return (ingredientsName + ' ' + ingredientCount);
    }
}
