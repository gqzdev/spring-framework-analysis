package com.gqzdev.ioc;

import com.gqzdev.ioc.bean.User;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName Test
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/26 23:24
 * @Version
 **/

public class AnnotationTest {

	public static void main(String[] args){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-bean01.xml");

		User user = (User)ac.getBean("user");
		System.out.println(user.toString());
	}
}
