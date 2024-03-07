package com.yu.annotation;
/*
 * 自定义注解，用于标识某个方法需要进行功能字段自动填充处理
 */

import com.yu.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    //数据库操作类型 INSERT UPDATE
    OperationType value();
}
