package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface DialogProvider {
    BuildParam[] buildParams() default {};

    Class[] extendsParams() default {};

    Class headerDelegate() default void.class;

    Class bodyDelegate() default void.class;

    Class footerDelegate() default void.class;

    ParamValue[] replaceDefaultValues() default {};
}
