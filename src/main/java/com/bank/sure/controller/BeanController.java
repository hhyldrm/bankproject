package com.bank.sure.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class BeanController {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@GetMapping("/bean")
	public ResponseEntity<Map<String, String>>getBeans(){
		String [] beanNames= applicationContext.getBeanDefinitionNames();
		
		Map<String,String>map=new HashMap<>();
		for(String beanName:beanNames) {
			map.put(beanName, applicationContext.getBean(beanName).toString());
		}
		
		return ResponseEntity.ok(map);
	}
}
