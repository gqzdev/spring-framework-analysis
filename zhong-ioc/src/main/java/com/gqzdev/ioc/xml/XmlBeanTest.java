package com.gqzdev.ioc.xml;

import com.gqzdev.ioc.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过ClassPathXmlApplication方式 读入
 *
 *
 * @ClassName Main
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/27 0:22
 * @Version
 **/

public class XmlBeanTest {
	public static void main(String[] args){
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-bean01.xml");

		for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}

		User user = (User)ac.getBean("user");
		System.out.println(user.toString());
	}

}
