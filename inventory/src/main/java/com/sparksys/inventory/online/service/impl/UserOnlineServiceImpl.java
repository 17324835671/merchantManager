package com.sparksys.inventory.online.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.stereotype.Service;

import com.sparksys.common.entity.SysUser;
import com.sparksys.common.entity.SysUserOnline;
import com.sparksys.common.utils.DateUtil;
import com.sparksys.common.utils.CommonProperties;
import com.sparksys.inventory.online.service.UserOnlineService;
@Service
public class UserOnlineServiceImpl implements UserOnlineService {
	
	@Resource
	private RedisSessionDAO redisSessionDAO;
	
	@Resource
	private SessionManager sessionManager;
	@Override
	public List<SysUserOnline> findSysUserOnlineList() {
		Collection<Session> sessions = redisSessionDAO.getActiveSessions();
		Iterator<Session> it = sessions.iterator();
		List<SysUserOnline> userOnlineList = new ArrayList<SysUserOnline>();
		while (it.hasNext()) {
			// 这是shiro已经存入session的
			// 现在直接取就是了
			Session session = it.next();
			//标记为已提出的不加入在线列表
			if(session.getAttribute("kickout")==null?false:true)continue;
			SysUserOnline onlineUser = getSessionBo(session);
			if(onlineUser!=null){
				userOnlineList.add(onlineUser);
			}
		}
		return userOnlineList;
	}
	//根据sessionId执行强制退出
	public void kickout(Serializable sessionId){
		this.getSessionBysessionId(sessionId).setAttribute("kickout", true);
	}
	
	//根据sesisonid获取单个session对象
	private Session getSessionBysessionId(Serializable sessionId){
		Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(sessionId));
		return kickoutSession;
	}
	//从session中获取UserOnline对象
	private SysUserOnline getSessionBo(Session session){
		//获取session登录信息。
		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		if(null == obj){
			return null;
		}
		//确保是 SimplePrincipalCollection对象。
		if(obj instanceof SimplePrincipalCollection){
			SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
			obj = spc.getPrimaryPrincipal();
			if(null != obj && obj instanceof SysUser){
				//存储session + user 综合信息
				SysUserOnline userBo = new SysUserOnline();
				SysUser user = (SysUser) obj;
				userBo.setUserName(user.getUserName());
				userBo.setDisplayName(user.getDisplayName());
				//最后一次和系统交互的时间
				userBo.setLastAccess(DateUtil.dateToString(session.getLastAccessTime(), CommonProperties.YYYY_MM_DD_HH_MM_SS));
				//主机的ip地址
				userBo.setHost(session.getHost());
				//session ID
				userBo.setSessionId(session.getId().toString());
				//session最后一次与系统交互的时间
				//userBo.setLastLoginTime(session.getLastAccessTime());
				//回话到期 ttl(ms)
				userBo.setTimeout(session.getTimeout());
				//session创建时间
				userBo.setStartTime(DateUtil.dateToString(session.getLastAccessTime(), CommonProperties.YYYY_MM_DD_HH_MM_SS));
				//是否踢出
				userBo.setSessionStatus(false);
				return userBo;
			}
		}
		return null;
	}
}
