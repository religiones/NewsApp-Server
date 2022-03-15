package com.kronos.newsapp.tool;

import java.lang.annotation.*;

/**
 * 定义一个主键自增的标志注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AutoInc {

}
