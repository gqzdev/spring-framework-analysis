package com.gqzdev.ioc.autowire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: Test
 * @author: ganquanzhong
 * @date: 2020/7/10 10:16
 */
public class AutowireTest {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext act = new AnnotationConfigApplicationContext(JavaConfig.class);
		for (String beanName : act.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}

		System.out.println(act.getBean("classRoom", ClassRoom.class).toString());
	}
}
