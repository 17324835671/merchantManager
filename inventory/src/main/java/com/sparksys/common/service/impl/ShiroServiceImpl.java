package com.sparksys.common.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparksys.common.realm.ShiroAuthorizingRealm;
import com.sparksys.common.service.ShiroService;
import com.sparksys.common.service.SysPermissionInitService;
import com.sparksys.system.permission.service.ISysPermissionService;
/**
*
*  @application name：
*  @author: zhouxinlei
*  @time：2018年10月16日
*  @version：ver 1.1
*/
@Service
public class ShiroServiceImpl implements ShiroService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroServiceImpl.class);
	
	@Autowired
	ShiroFilterFactoryBean shiroFilterFactoryBean;
	
	@Resource
	private SysPermissionInitService permissionInitService;
	
	@Resource
    private ISysPermissionService permissionService;
	
	@Override
	public void updateFilterChainDefinition() {
		synchronized (this) {    
			AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(permissionInitService.loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim()
                        .replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            LOGGER.info("==========shiro session过滤器链已成功更新==========");
            clearAuth();
		}
    }
	public static void clearAuth(){
		RealmSecurityManager rsm = (RealmSecurityManager)SecurityUtils.getSecurityManager();
		ShiroAuthorizingRealm realm = (ShiroAuthorizingRealm)rsm.getRealms().iterator().next();
		realm.clearAuthz();
	}
}
