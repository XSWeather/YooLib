package dev.yooproject.yoolib.configuration;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConfigurationFile {
    String file();
    ConfigFormat format() default ConfigFormat.YAML;
}
