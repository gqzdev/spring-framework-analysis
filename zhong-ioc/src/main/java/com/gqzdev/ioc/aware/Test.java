package com.gqzdev.ioc.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: Test
 * @author: ganquanzhong
 * @date: 2020/7/10 13:36
 */
public class Test {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext act = new AnnotationConfigApplicationContext(Hello.class);
		HelloAware helloAware = act.getBean("helloAware", HelloAware.class);
		helloAware.testAware();
	}
}
