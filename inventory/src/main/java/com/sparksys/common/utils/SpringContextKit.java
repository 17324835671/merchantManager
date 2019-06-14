package com.sparksys.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
 *  @application 
 *  @author zhouxinlei 
 *  @time 2019年1月29日 下午3:53:51 
 *  @version 0.1 
 */
public class SpringContextKit implements ApplicationContextAware {
    private static ApplicationContext context;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static String getAppName() {
        return context.getEnvironment().getProperty("spring.application.name");
    }

    public static <T> T getBean(Class<T> tClass) {
        return context.getBean(tClass);
    }

    public static <T> T getBean(String var1, Class<T> var2) {
        return context.getBean(var1, var2);
    }

    public static Object getBean(String idOrName) {
        return context.getBean(idOrName);
    }


}
