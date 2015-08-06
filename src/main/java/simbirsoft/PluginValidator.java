package simbirsoft;

import com.simbirsoft.annotations.GetIngredientMethodAnnotation;
import com.simbirsoft.annotations.GetIngredientNameMethodAnnotation;
import com.simbirsoft.annotations.PluginAnnotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PluginValidator {

    public Ingredient validate(Class<?> pluginClass) {
        Ingredient pluginIngredient = null;
        String ingredientName = "";
        int ingredientCount = 0;
        try {
            if (pluginClass.isAnnotationPresent(PluginAnnotation.class)) {
                Object plugin = pluginClass.newInstance();

                PluginAnnotation pluginAnnotation = pluginClass.getAnnotation(PluginAnnotation.class);

                Method[] methods = pluginClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(GetIngredientNameMethodAnnotation.class)) {
                        Class[] methodParameters = method.getParameterTypes();
                        if (methodParameters.length == 0) {
                            ingredientName = (String) method.invoke(plugin);
                        }
                    }
                    if (method.isAnnotationPresent(GetIngredientMethodAnnotation.class)) {
                        Class[] methodParameters = method.getParameterTypes();
                        if (methodParameters.length == 0) {
                            ingredientCount = (int) method.invoke(plugin);
                        }
                    }
                }
                pluginIngredient = new Ingredient(ingredientName, ingredientCount);
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return pluginIngredient;
    }
}