package simbirsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaSize {
    public static final List<String> SIZES_OF_PIZZA = new ArrayList<String>(Arrays.asList("SMALL", "NORMAL", "BIG"));
    private String currentSize="";

    public void setCurrentSize(String size) {
        currentSize = size;
    }

    public String getCurrentSize() {
        return currentSize;
    }

    public void printALL() {
        for (String sizeStr : SIZES_OF_PIZZA) {
            System.out.println(sizeStr + System.lineSeparator());
        }
    }
}

