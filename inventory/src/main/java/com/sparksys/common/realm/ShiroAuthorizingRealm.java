package com.sparksys.common.realm;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;

import com.sparksys.common.entity.SysPermission;
import com.sparksys.common.entity.SysRole;
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.service.RedisService;
import com.sparksys.common.utils.RedisConstant;
import com.sparksys.common.utils.UtilTools;
import com.sparksys.system.permission.service.ISysPermissionService;
import com.sparksys.system.role.service.ISysRoleService;
import com.sparksys.system.user.service.ISysUserService;
/**
 * 
*  @application name： 
*  @author: zhouxinlei
*  @time：2018年7月18日
*  @version：ver 1.1
 */
public class ShiroAuthorizingRealm extends AuthorizingRealm {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroAuthorizingRealm.class);
	
	@Resource
    @Lazy
	private ISysUserService userService;
	@Resource
    private ISysRoleService roleService;
	@Resource
    private ISysPermissionService permissionService;
	@Resource
    private RedisService redisService;

	/**
	 *  权限认证 
	 *  获取用户的权限信息，这是为下一步的授权做判断，获取当前用户的角色和这些角色所拥有的权限信息 
	 */
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		List<SysRole> roleList = roleService.findUserRoles(user.getUserId());
		for (SysRole role : roleList) {
			authorizationInfo.addRole(role.getRoleCode());
		}
		List<SysPermission> permissionList = permissionService.findUserPermissions(user.getUserId());
		for (SysPermission permission : permissionList) {
			authorizationInfo.addStringPermission(permission.getPermissionCode());
		}
		System.out.println("权限验证成功");
		if (permissionList.size() == 0) {
            return null;
        }
		return authorizationInfo;
	}
	
	/*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
	
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
		UsernamePasswordToken authcToken = (UsernamePasswordToken) authenticationToken;
		String userName = authcToken.getUsername();
		String password = String.valueOf(authcToken.getPassword());
		LOGGER.info("登录开始认证>>>sessionId[{}]>>>用户名[{}]>>>密码[{}]",sessionId,userName,password);
		/** 访问一次，计数一次*/
		//redisService.increment(RedisConstant.SHIRO_LOGIN_COUNT+userName, 1);
		/** 计数大于5时，设置用户被锁定一小时*/
	    /*if(Integer.parseInt(redisService.get(RedisConstant.SHIRO_LOGIN_COUNT+userName))>5){
	    	redisService.set(userName+"_lock", "LOCK");
	        redisService.expire(userName+"_lock",1);
	    }
	    if ("LOCK".equals(redisService.get(RedisConstant.SHIRO_IS_LOCK+userName))){
	        throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
	    }*/
	    Map<String, Object> map = new HashMap<>();
	    map.put("userName", userName);
		//String pawSM3 = UtilTools.SM3(password);
		map.put("password", password);
		SysUser user = userService.findSysUerByUserNamePwd(map);
		if (user == null) {
			LOGGER.info("帐号或密码不正确！");
			throw new AccountException("帐号或密码不正确！");
		}else if(user.getState().equals(0L)){
			LOGGER.info("此帐号已经设置为禁止登录！");
			throw new DisabledAccountException("此帐号已经设置为禁止登录！");
		}else {
			user.setLastLoginTime(new Date());
			userService.updateSysUer(user);
			redisService.set(RedisConstant.SHIRO_LOGIN_COUNT + userName, "0");
		}
		return new SimpleAuthenticationInfo(user, password, getName());
	}
	public void clearAuthz(){
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
}