package com.gqzdev.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Test
 * @Description
 * @Author ganquanzhong
 * @Date2020/7/10 0:12
 * @Version
 **/
public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-aop.xml");
		TestBean testBean = (TestBean) ac.getBean("testBean");
		testBean.test();
	}
}
