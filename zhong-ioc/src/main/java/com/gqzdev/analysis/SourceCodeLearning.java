package com.gqzdev.analysis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @description: IoC源码分析
 * @Author: ganquanzhong
 * @Date: 2019/12/30 9:42
 */
public class SourceCodeLearning {
	public static void main(String[] args) {

		//ApplicationContext：Spring的上下文。通过对源码的类的集成关系可以看出，
		// FileSystemXmlApplicationContext是ApplicationContext的一个标准实现
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:spring-bean02.xml");

		//从容器中获取名字为user的bean
		PressService pressService = (PressService) applicationContext.getBean("pressService");

		//调用bean的方法
		String price = pressService.say();
		System.out.println(price);
	}
}
