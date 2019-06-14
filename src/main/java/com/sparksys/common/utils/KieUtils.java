package com.sparksys.common.utils;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.KieBase;
import org.kie.api.KieServices;

/**
 * @application name:KIE通用类
 * @author: zhouxinlei
 * @time：2018年10月8日
 * @version：ver 1.1
 */
public class KieUtils {

	private static KieContainer kieContainer;
	private static KieServices kieServices;
	private static KieBase kieBase;
	
	public static KieContainer getKieContainer() {
		return kieContainer;
	}
	public static void setKieContainer(KieContainer kieContainer) {
		KieUtils.kieContainer = kieContainer;
	}
	public static KieServices getKieServices() {
		return kieServices;
	}
	public static void setKieServices(KieServices kieServices) {
		KieUtils.kieServices = kieServices;
	}
	public static KieSession getKieSession() {
		KieSession kieSession = kieContainer.newKieSession("ksession-rule");
		return kieSession;
	}
	public static void setKieSession(KieSession kieSession) {
	}
	public static KieBase getKieBase() {
		return kieBase;
	}
	public static void setKieBase(KieBase kieBase) {
		KieUtils.kieBase = kieBase;
	}
	
	public static KieSession getKieSession(String agendaGroupName) {
		KieSession kieSession = getKieSession();
		kieSession.getAgenda().getAgendaGroup(agendaGroupName).setFocus();
		return kieSession;
	}
	public static KieSession getKieSessionByName(String sessionName) {
		KieSession kieSession = kieContainer.newKieSession(sessionName);
		return kieSession;
	}
	
}
