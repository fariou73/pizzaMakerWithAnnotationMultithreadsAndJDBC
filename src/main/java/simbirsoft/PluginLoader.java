package simbirsoft;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class PluginLoader {

    public List<Class<?>> load(String[] pluginsList) {
        List<Class<?>> classList = new ArrayList<>();

        for (String pluginJarFile : pluginsList) {
            try {
                // Open the JAR file
                JarFile jarfile = new JarFile(pluginJarFile);

                // Get the manifest
                Manifest manifest = jarfile.getManifest();

                // Get the main attributes in the manifest
                Attributes attributes = manifest.getMainAttributes();

                String pluginClassName = attributes.getValue("Main-Class");
                Path path = Paths.get(pluginJarFile);
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{path.toUri().toURL()});
                Class<?> pluginClass = classLoader.loadClass(pluginClassName);
                classList.add(pluginClass);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classList;
    }
}
