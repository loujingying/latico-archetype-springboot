package com.latico.archetype.springboot.common.anno;

import java.lang.annotation.*;

/**
 * <PRE>
 *  示例Aspect日志记录注解
 * </PRE>
 * @Author: latico
 * @Date: 2019-07-12 16:28:25
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DemoAspectAnnotation {
    String value() default "";
}
