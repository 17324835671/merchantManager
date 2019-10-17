package com.sparksys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = {"com.sparksys.common.dao"})
@EnableCaching
public class InventoryApplication {

	/**
	 * 666
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

}
