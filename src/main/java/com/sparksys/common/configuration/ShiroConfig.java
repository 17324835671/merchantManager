package com.sparksys.common.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sparksys.common.filter.PerControlFilter;
import com.sparksys.common.listener.ShiroSessionListener;
import com.sparksys.common.realm.ShiroAuthorizingRealm;
import com.sparksys.common.service.SysPermissionInitService;
/** 
 *  @application 
 *  @author zhouxinlei 
 *  @time 2019年1月29日 下午3:56:39 
 *  @version 0.1 
 */
@Configuration
public class ShiroConfig {
	
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.expire}")
	private int expire;
	@Value("${spring.redis.timeout}")
	private int timeout;
	@Value("${spring.redis.password}")
	private String password;
	
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager,SysPermissionInitService initService) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", perControlFilter());
        //登录成功后要跳转的页面
        shiroFilterFactoryBean.setFilters(filtersMap);
        /*加入自定义过滤器*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(initService.loadFilterChainDefinitions());
        return shiroFilterFactoryBean;
    }
	   
	@Bean
	public ShiroAuthorizingRealm shiroAuthorizingRealm(){
		ShiroAuthorizingRealm realm = new ShiroAuthorizingRealm();
		return realm;
	}
	/**
	 *  开启shiro aop注解支持.
	 *  使用代理方式;所以需要开启代码支持;
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(shiroAuthorizingRealm());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(redisCacheManager());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}
	
	/**
	 * 配置shiro redisManager
	 * 使用的是shiro-redis开源插件
	 * @return
	 */
	
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setExpire(expire);
		redisManager.setTimeout(expire);
		//redisManager.setPassword(password);
		return redisManager;
	}
	
	/**
	 * cacheManager 缓存 redis实现
	 * 使用的是shiro-redis开源插件
	 * @return
	 */
	public RedisCacheManager redisCacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}
	 /**
     * session 管理对象
     *	使用的是shiro-redis开源插件
     * @return DefaultWebSessionManager
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
    	DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        // 设置session超时时间，单位为毫秒
        sessionManager.setGlobalSessionTimeout(expire*1000);
        sessionManager.setSessionListeners(listeners);
        sessionManager.setCacheManager(redisCacheManager());
        sessionManager.setDeleteInvalidSessions(true);//删除过期的session
        //session-redis缓存
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
    /**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}
	/**
	 * Shiro生命周期处理器
	 *
	 */
	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
    /**
     * 限制同一账号登录同时登录人数控制
     * @return
     */
    public PerControlFilter perControlFilter(){
    	PerControlFilter sessionControlFilter = new PerControlFilter();
    	//使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
    	//这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
    	//也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
    	sessionControlFilter.setCacheManager(redisCacheManager());
    	//用于根据会话ID，获取会话进行踢出操作的；
    	sessionControlFilter.setSessionManager(sessionManager());
    	//是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
    	sessionControlFilter.setKickoutAfter(false);
    	//同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
    	sessionControlFilter.setMaxSession(1);
    	//被踢出后重定向到的地址；
    	sessionControlFilter.setKickoutUrl("/kickout");
        return sessionControlFilter;
     }
}