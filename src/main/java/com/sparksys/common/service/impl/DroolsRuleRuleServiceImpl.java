package com.sparksys.common.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.internal.io.ResourceFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.sparksys.common.service.DroolsRuleRuleService;
import com.sparksys.common.utils.KieUtils;
/**
 *  @application name:规则加载 
 *  @author: zhouxinlei 
 *  @time：2018年10月8日
 *  @version：ver 1.1
 */
@Service
public class DroolsRuleRuleServiceImpl implements DroolsRuleRuleService{
	
	private static final String RULES_PATH = "rules/";
	
	public void reloadRule() throws UnsupportedEncodingException {
        KieServices kieServices = getKieServices();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        try {
			for (Resource file : getRuleFiles()) {
				kfs.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }
        KieUtils.setKieContainer(kieServices.newKieContainer(getKieServices().getRepository().getDefaultReleaseId()));
        System.out.println("reload新规则重载成功");
    }
	public void reloadRule(String fileName) throws UnsupportedEncodingException {
        KieServices kieServices = getKieServices();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write(ResourceFactory.newClassPathResource(RULES_PATH + fileName, "UTF-8"));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }
        KieUtils.setKieContainer(kieServices.newKieContainer(getKieServices().getRepository().getDefaultReleaseId()));
        System.out.println("reload新规则重载成功");
    }
	
    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }
    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }
}
