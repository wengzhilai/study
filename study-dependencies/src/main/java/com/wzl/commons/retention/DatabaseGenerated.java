package com.wzl.commons.retention;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseGenerated {
    DatabaseGeneratedOption value()  default DatabaseGeneratedOption.Computed;
    String Name() default "";
}
