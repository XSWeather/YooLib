package dev.yooproject.yoolib.configuration;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class YamlConfigProvider implements ConfigProvider {
    @Override
    public Object load(File file) throws Exception {
        return YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void save(File file, Object data) throws Exception {
        if (data instanceof YamlConfiguration) {
            ((YamlConfiguration) data).save(file);
        }
    }

    @Override
    public Object get(Object root, String path) {
        return ((YamlConfiguration) root).get(path);
    }

    @Override
    public void set(Object root, String path, Object value) {
        ((YamlConfiguration) root).set(path, value);
    }
}
