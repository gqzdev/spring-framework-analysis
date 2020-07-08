package com.gqzdev.ioc.parsebeandefinition.constructorarg;

import com.gqzdev.ioc.bean.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: BeanDefinitionTest
 * @author: ganquanzhong
 * @date: 2020/7/8 10:32
 */
public class ConstructorArgTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext act = new ClassPathXmlApplicationContext("spring-beandefinition.xml");

		User user01 = act.getBean("user01", User.class);
		System.out.println(user01.toString());
	}
}
