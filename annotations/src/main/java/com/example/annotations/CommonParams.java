package com.example.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface CommonParams {
    BuildParam[] buildParams() default {};
}
