package com.sparksys.common.service;

import java.io.UnsupportedEncodingException;

public interface DroolsRuleRuleService {
	
	public void reloadRule() throws UnsupportedEncodingException;
	
	public void reloadRule(String fileName) throws UnsupportedEncodingException;
}
