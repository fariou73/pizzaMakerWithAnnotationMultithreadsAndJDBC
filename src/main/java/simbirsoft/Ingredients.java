package simbirsoft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ingredients {

    private final List<Ingredient> ingredientsList;

    public Ingredients() {
        ingredientsList = new ArrayList<>();
    }

    public List<Ingredient> getIngredients() {
        return ingredientsList;
    }

    public void saveFromFile(String pathFromIngredientFile) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:"+pathFromIngredientFile)) {
            Statement statement = conn.createStatement();
            conn.setAutoCommit(false);
            try {
                for (Ingredient ingredient : ingredientsList) {
                    statement.executeQuery("INSERT INTO Ingredients (name, count) VALUES ('"
                            + ingredient.getIngredientsName() + "',"
                            + ingredient.getIngredientCount() + ")");
                }
            } catch (SQLException e) {
                conn.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String pathFromIngredientFile) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:"+pathFromIngredientFile)) {
            Statement statement = conn.createStatement();
            conn.setAutoCommit(false);
            try {
                ResultSet resultSet = statement.executeQuery("select * from Ingredients");
                while (resultSet.next()) {
                    ingredientsList.add(new Ingredient(resultSet.getString("name"), resultSet.getInt("count")));
                }
            } catch (SQLException e) {
                conn.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isDifferenceReal(Ingredients ingredients) {
        boolean result = true;
        for (Ingredient ingredient : ingredients.getIngredients()) {
            Ingredient ingredient1FromDifference = get(ingredient.getIngredientsName());
            if (ingredient1FromDifference != null) {
                if ((ingredient1FromDifference.getIngredientCount() - ingredient.getIngredientCount()) < 0) {
                    result = false;
                }
            } else {
                result = false;
            }
        }
        return result;
    }

    public void difference(Ingredients ingredients) {
        for (Ingredient ingredient : ingredients.getIngredients()) {
            Ingredient ingredient1FromDifference = get(ingredient.getIngredientsName());
            ingredient1FromDifference.setIngredientCount(ingredient1FromDifference.getIngredientCount() - ingredient.getIngredientCount());
        }
    }

    public Ingredient get(String name) {
        Ingredient result = null;
        for (Ingredient ingredient : ingredientsList) {
            if (ingredient.getIngredientsName() == name) {
                result = ingredient;
            }
        }
        return result;
    }

    public void add(Ingredient ingredient) {
        ingredientsList.add(ingredient);
    }

    public void clear() {
        ingredientsList.clear();
    }

    public void printAll() {
        int j = 0;
        for (Ingredient myIngredient : ingredientsList) {
            j++;
            System.out.println(j + ") " + myIngredient);
        }
    }
}
