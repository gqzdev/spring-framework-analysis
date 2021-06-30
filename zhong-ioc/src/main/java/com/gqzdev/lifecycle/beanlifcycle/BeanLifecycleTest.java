package com.gqzdev.lifecycle.beanlifcycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ganquanzhong
 * @Date 2019/12/30
 * @Description Bean生命周期测试
 */

public class BeanLifecycleTest {

	public static void testJavaConfig() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifecycle.class);

		BeanLifecycle beanLifecycle = applicationContext.getBean(BeanLifecycle.class);

		beanLifecycle.sayHello();

		applicationContext.close();
	}

	/**
	 * 1. 【Bean级别】构造器执行了
	 * 2. 【Bean级别】setBeanName方法执行了beanLifecycle
	 * 3. 【Bean级别】setApplicationContext方法执行了
	 * 5. 【Bean级别】init-method指定的方法执行了
	 * 4. 【Bean级别】afterPropertiesSet方法执行了
	 * 6. 【Bean级别】业务方法体--sayHello方法执行了
	 * 8. 【Bean级别】destroy-method属性指定的方法执行了
	 * 7. 【Bean级别】destroy方法执行了
	 */

	public static void testXmlConfig() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc-beanlifecycle.xml");

		BeanLifecycle beanLifecycle = applicationContext.getBean(BeanLifecycle.class);

		beanLifecycle.sayHello();

		applicationContext.close();
	}
	/**
	 * 运行结果:
	 * 1. 【Bean级别】构造器执行了
	 * 2. 【Bean级别】setBeanName方法执行了
	 * 3. 【Bean级别】setApplicationContext方法执行了
	 * 4. 【Bean级别】afterPropertiesSet方法执行了
	 * 5. 【Bean级别】init-method指定的方法执行了
	 * 6. 【Bean级别】sayHello方法执行了
	 * 7. 【Bean级别】destroy方法执行了
	 * 8. 【Bean级别】destroy-method属性指定的方法执行了
	 */


	public static void main(String[] args) {
//		testXmlConfig();

		testJavaConfig();

	}

}


