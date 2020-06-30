package com.ben4.framework.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 @Author: caokang
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}

