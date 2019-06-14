package com.sparksys.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtools implements ApplicationContextAware {
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
    	SpringContextUtools.context = context;
    }

    public static ApplicationContext getContext(){
        return context;
    }


}
