package dev.yooproject.yoolib.configuration;

import com.google.gson.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonConfigProvider implements ConfigProvider {
    @Override
    public Object load(File file) throws Exception {
        if (!file.exists()) return new JsonObject();
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            JsonParser parser = new JsonParser();
            return parser.parse(reader).getAsJsonObject();
        }
    }

    @Override
    public void save(File file, Object data) throws Exception {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, writer);
        }
    }

    @Override
    public Object get(Object root, String path) {
        String[] parts = path.split("\\.");
        JsonElement element = (JsonElement) root;
        for (String part : parts) {
            if (element.isJsonObject() && element.getAsJsonObject().has(part)) {
                element = element.getAsJsonObject().get(part);
            } else {
                return null;
            }
        }
        if (element.isJsonPrimitive()) {
            JsonPrimitive prim = element.getAsJsonPrimitive();
            if (prim.isString()) return prim.getAsString();
            if (prim.isNumber()) return prim.getAsNumber();
            if (prim.isBoolean()) return prim.getAsBoolean();
        }
        if (element.isJsonArray()) return element.getAsJsonArray();
        if (element.isJsonObject()) return element.getAsJsonObject();
        return null;
    }

    @Override
    public void set(Object root, String path, Object value) {
        String[] parts = path.split("\\.");
        JsonObject obj = (JsonObject) root;
        for (int i = 0; i < parts.length - 1; i++) {
            if (!obj.has(parts[i]) || !obj.get(parts[i]).isJsonObject()) {
                obj.add(parts[i], new JsonObject());
            }
            obj = obj.getAsJsonObject(parts[i]);
        }
        if (value instanceof String) obj.addProperty(parts[parts.length - 1], (String) value);
        else if (value instanceof Number) obj.addProperty(parts[parts.length - 1], (Number) value);
        else if (value instanceof Boolean) obj.addProperty(parts[parts.length - 1], (Boolean) value);
        else if (value instanceof JsonElement) obj.add(parts[parts.length - 1], (JsonElement) value);
        else obj.add(parts[parts.length - 1], new Gson().toJsonTree(value));
    }
}
