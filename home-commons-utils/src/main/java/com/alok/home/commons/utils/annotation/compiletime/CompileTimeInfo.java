package com.alok.home.commons.utils.annotation.compiletime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE) // Can be applied to classes/interfaces
public @interface CompileTimeInfo {
    String author() default "Unknown";
    String version() default "1.0";
}
