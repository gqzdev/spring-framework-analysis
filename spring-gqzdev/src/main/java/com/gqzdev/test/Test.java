package com.gqzdev.test;

import com.gqzdev.app.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName Test
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/5 22:58
 * @Version
 **/

public class Test {

	public static void main(String[] args){

		ApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);

		Object x =  ac.getBean("x");
		System.out.println(x);

	}
}
