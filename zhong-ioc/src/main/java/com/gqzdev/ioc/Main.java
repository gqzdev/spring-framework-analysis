package com.gqzdev.ioc;

import com.gqzdev.ioc.bean.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Main
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/27 0:22
 * @Version
 **/

public class Main {
	public static void main(String[] args){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-bean01.xml");

		User user = (User)ac.getBean("user");
		System.out.println(user.toString());
	}

}
