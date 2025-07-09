package dev.yooproject.yoolib.configuration;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigField {
    String path() default "";
    String comment() default "";
}