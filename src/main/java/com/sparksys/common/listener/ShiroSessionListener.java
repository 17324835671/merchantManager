package com.sparksys.common.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ShiroSessionListener implements SessionListener{
	
	private static final Logger Logger = LoggerFactory.getLogger(ShiroSessionListener.class);
	
	@Override
	public void onStart(Session session) {
		Logger.info("session已创建:sessionId="+session.getId());
	}

	@Override
	public void onStop(Session session) {
		Logger.info("session已关闭:sessionId="+session.getId());
	}

	@Override
	public void onExpiration(Session session) {
		Logger.info("session已过期:sessionId="+session.getId());
	}
}
