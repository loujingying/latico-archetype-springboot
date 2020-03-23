package com.latico.archetype.springboot.common.anno;

import java.lang.annotation.*;

/**
 * <PRE>
 *  时间统计
 * </PRE>
 * @author: latico
 * @date: 2019-07-12 16:28:25
 * @version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TimeStatisAspectAnnotation {
    String value() default "";
}
