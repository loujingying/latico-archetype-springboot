package com.latico.archetype.springboot.common.util;

import com.latico.archetype.springboot.Application;
import org.springframework.context.ApplicationListener;

import java.util.List;

/**
 * <PRE>
 * 资源工具类
 * </PRE>
 *
 * @author: latico
 * @date: 2019-06-07 3:39
 * @version: 1.0
 */
public class ResourcesUtils extends com.latico.commons.common.util.reflect.ResourcesUtils {

    /**
     * 扫描出当前程序启动监听器
     *
     * @throws Exception
     */
    public static ApplicationListener[] scanCurrentApplicationListeners() throws Exception {
        ApplicationListener[] listeners = null;
        String searchPackageName = Application.class.getPackage().getName();
        List<Class<ApplicationListener>> classes = getAllImplClassByFatherClass(ApplicationListener.class, searchPackageName);
        if (classes != null && !classes.isEmpty()) {
            listeners = new ApplicationListener[classes.size()];
            for (int i = 0; i < classes.size(); i++) {
                listeners[i] = classes.get(i).newInstance();
            }
        }
        return listeners;
    }
}
