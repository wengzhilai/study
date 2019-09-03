package com.wjbjp.retention;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseGenerated {
    enum DatabaseGeneratedOption{
        None ,
        Identity ,
        Computed
    }//定义性别枚举
    DatabaseGeneratedOption value()  default DatabaseGeneratedOption.Computed;
    String Name() default "";
}
