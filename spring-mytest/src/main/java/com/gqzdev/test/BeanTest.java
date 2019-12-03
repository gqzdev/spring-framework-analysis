package com.gqzdev.test;

import com.gqzdev.bean.User;
import com.gqzdev.config.JavaConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName BeanTest
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/3 22:41
 * @Version
 **/
public class BeanTest {
	public static void main(String[] args){

		ApplicationContext ac =new AnnotationConfigApplicationContext(JavaConfig.class);
		User user = (User) ac.getBean("user");
		System.out.println(user.toString());
	}
}
