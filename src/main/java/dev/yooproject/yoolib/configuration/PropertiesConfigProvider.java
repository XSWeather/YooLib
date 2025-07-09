package dev.yooproject.yoolib.configuration;

import java.io.*;
import java.util.Properties;

public class PropertiesConfigProvider implements ConfigProvider {
    @Override
    public Object load(File file) throws Exception {
        Properties props = new Properties();
        if (file.exists()) try (InputStream in = new FileInputStream(file)) {
            props.load(in);
        }
        return props;
    }

    @Override
    public void save(File file, Object data) throws Exception {
        if (data instanceof Properties) {
            try (OutputStream out = new FileOutputStream(file)) {
                ((Properties) data).store(out, null);
            }
        }
    }

    @Override
    public Object get(Object root, String path) {
        return ((Properties) root).getProperty(path);
    }

    @Override
    public void set(Object root, String path, Object value) {
        ((Properties) root).setProperty(path, String.valueOf(value));
    }
}
