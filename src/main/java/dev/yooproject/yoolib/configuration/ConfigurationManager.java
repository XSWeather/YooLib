package dev.yooproject.yoolib.configuration;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.Map;

public class ConfigurationManager {
    private static final Map<ConfigFormat, ConfigProvider> providers = new EnumMap<>(ConfigFormat.class);

    static {
        providers.put(ConfigFormat.YAML, new YamlConfigProvider());
        providers.put(ConfigFormat.JSON, new JsonConfigProvider());
        providers.put(ConfigFormat.PROPERTIES, new PropertiesConfigProvider());
    }

    public static void registerProvider(ConfigFormat format, ConfigProvider provider) {
        providers.put(format, provider);
    }

    public static <T> T load(JavaPlugin plugin, Class<T> configClass) {
        try {
            ConfigurationFile configAnno = configClass.getAnnotation(ConfigurationFile.class);
            if (configAnno == null) throw new IllegalArgumentException("No @ConfigurationFile annotation!");

            File file = new File(plugin.getDataFolder(), configAnno.file());
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            ConfigProvider provider = providers.get(configAnno.format());
            Object root = provider.load(file);

            T config = configClass.getDeclaredConstructor().newInstance();

            for (Field field : configClass.getFields()) {
                ConfigField valueAnno = field.getAnnotation(ConfigField.class);
                if (valueAnno == null) continue;
                String path = valueAnno.path().isEmpty() ? field.getName() : valueAnno.path();
                Object val = provider.get(root, path);
                if (val != null) {
                    field.set(config, val);
                } else {
                    provider.set(root, path, field.get(config));
                }
            }
            provider.save(file, root);
            return config;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + e.getMessage(), e);
        }
    }

    public static <T> void save(JavaPlugin plugin, T configInstance) {
        try {
            Class<?> configClass = configInstance.getClass();
            ConfigurationFile configAnno = configClass.getAnnotation(ConfigurationFile.class);
            if (configAnno == null) throw new IllegalArgumentException("No @ConfigurationFile annotation!");

            File file = new File(plugin.getDataFolder(), configAnno.file());
            ConfigProvider provider = providers.get(configAnno.format());
            Object root = provider.load(file);

            for (Field field : configClass.getFields()) {
                ConfigField valueAnno = field.getAnnotation(ConfigField.class);
                if (valueAnno == null) continue;
                String path = valueAnno.path().isEmpty() ? field.getName() : valueAnno.path();
                provider.set(root, path, field.get(configInstance));
            }
            provider.save(file, root);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save config: " + e.getMessage(), e);
        }
    }

    public static void reload(JavaPlugin plugin, Object configInstance) {
        load(plugin, configInstance.getClass());
    }
}
