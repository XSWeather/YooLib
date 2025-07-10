package dev.yooproject.yoolib.commands;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String name();
    String[] aliases() default {};
    String permission() default "";
    String description() default "";
}