package com.gqzdev;

import com.gqzdev.config.WebConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * description:
 * @Author：ganquanzhong
 * @Date： 2020/7/7 22:58
 */
public class Test {
	public static  void  main(String[] args){

		ApplicationContext ac = new AnnotationConfigApplicationContext(WebConfig.class);
		String[] names = ac.getBeanDefinitionNames();
		for (String name: names){
			System.out.println(name);
		}

		System.out.println("整合其他第三方组件！");
	}
}
