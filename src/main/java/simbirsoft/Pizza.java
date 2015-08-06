package simbirsoft;

public class Pizza {
    private final PizzaSize currentPizzaSize;
    private final Ingredients currentIngredients;

    public Pizza(PizzaSize currentPizzaSize, Ingredients currentIngredients) {
        this.currentPizzaSize = currentPizzaSize;
        this.currentIngredients = currentIngredients;
    }

    @Override
    public String toString() {
        String result = ("Pizza size: " + currentPizzaSize.getCurrentSize() + "\r\n");
        for (Ingredient ingredient : currentIngredients.getIngredients()) {
            result += ingredient + "\r\n";
        }
        return result;
    }

}
