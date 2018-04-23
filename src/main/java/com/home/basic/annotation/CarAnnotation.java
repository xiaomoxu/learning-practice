package com.home.basic.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})//Target表示该注解修饰的对象范围
@Inherited//
@Retention(RetentionPolicy.RUNTIME)//Retention表示该注解的生命周期
public @interface CarAnnotation {
    String name() default "Toyota Prado";
    int price() default 350000;
}
