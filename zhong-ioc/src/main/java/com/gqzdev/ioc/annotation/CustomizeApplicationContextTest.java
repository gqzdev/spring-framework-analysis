package com.gqzdev.ioc.annotation;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: CustomeApplicationContext
 * @author: ganquanzhong
 * @date: 2020/7/6 17:15
 */
public class CustomizeApplicationContextTest {

	public static void main(String[] args) {
		// AnnotationConfigApplicationContext 创建 应用上下文
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue("id","6601");
		pvs.addPropertyValue("name","花木头");

		//创建BeanDefinition
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Dog.class, null, pvs);
		applicationContext.registerBeanDefinition("dog-自定义元信息",rootBeanDefinition);
		// 刷新  只能刷新一次
		//applicationContext.refresh();

		Dog dog = applicationContext.getBean(Dog.class);
		System.out.println(dog.toString());
	}
}
