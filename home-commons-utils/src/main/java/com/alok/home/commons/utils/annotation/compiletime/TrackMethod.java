package com.alok.home.commons.utils.annotation.compiletime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)  // Compile-time only
public @interface TrackMethod {
}
