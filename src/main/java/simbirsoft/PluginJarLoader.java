package simbirsoft;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class PluginJarLoader {
    final String pathFromLoadedPlugins = "plugin/pluginsList";
    List<String> jarFileList = loadFromFile(pathFromLoadedPlugins);

    public PluginJarLoader(Ingredients ingredients) {
        final Timer timer = new Timer();
        final int timerDelayTime = 0;
        final long timerRetryTime = TimeUnit.SECONDS.toMillis(30);
        TimerTask task = new TimerTask() {
            public void run() {
                File file = new File("plugin/");
                String plugins[] = file.list(new FileExtensionsFilter(".jar"));
                List<String> jarToRun = new ArrayList<>();
                for (String plugin : plugins) {
                    if (!jarFileList.contains(plugin)) {
                        jarToRun.add("plugin/" + plugin);
                        jarFileList.add(plugin);
                    }
                }
                PluginLoader pluginLoader = new PluginLoader();
                PluginValidator pluginValidator = new PluginValidator();
                for (Class<?> plugin : pluginLoader.load(jarToRun.toArray(new String[jarToRun.size()]))) {
                    ingredients.add(pluginValidator.validate(plugin));
                }
                saveFromFile(pathFromLoadedPlugins, jarFileList);
            }
        };
        timer.schedule(task, timerDelayTime, timerRetryTime);
    }

    private List<String> loadFromFile(String path) {
        List<String> lines = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader
                (new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
        }
        ;
        return lines;
    }

    private void saveFromFile(String path, List<String> plugins) {
        try (BufferedWriter file = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));) {
            for (String stringFromWrite : plugins) {
                file.write(stringFromWrite + System.lineSeparator());
            }
        } catch (Exception e) {
            System.err.print(e.toString());
        }
    }
}

