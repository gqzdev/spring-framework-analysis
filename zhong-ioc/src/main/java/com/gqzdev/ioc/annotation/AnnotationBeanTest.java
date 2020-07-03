package com.gqzdev.ioc.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过注解方式  分析入口
 *
 * @ClassName: AnnotationBeanTest
 * @author: ganquanzhong
 * @date: 2020/7/3 9:33
 */
public class AnnotationBeanTest {


	public static void main(String[] args) {
		// AnnotationConfigApplicationContext 创建 应用上下文
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);

		for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}
	}
}
