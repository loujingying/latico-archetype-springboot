package com.latico.archetype.springboot.async;

/**
 * <PRE>
 * 异步执行接口类
 * </PRE>
 *
 * @Author: latico
 * @Date: 2020-02-17 22:28
 * @Version: 1.0
 */
@FunctionalInterface
public interface AsyncExecute {
    boolean execute();
}
