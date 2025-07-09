package dev.yooproject.yoolib.configuration;

import java.io.File;

public interface ConfigProvider {
    Object load(File file) throws Exception;
    void save(File file, Object data) throws Exception;
    Object get(Object root, String path);
    void set(Object root, String path, Object value);
}
