package com.example.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface BuildParam {
    String name();

    Class type();

    Class[] typeVariable() default {};

    Class[] annotations() default {};

    String defaultValue() default "";
}
