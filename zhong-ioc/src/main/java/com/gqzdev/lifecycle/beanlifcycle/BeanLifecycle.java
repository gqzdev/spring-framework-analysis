package com.gqzdev.lifecycle.beanlifcycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author ganquanzhong
 * @description: Bean生命周期
 * @date 2019/12/30 9:24
 * <p>
 * 实现接口
 * BeanNameAware
 * <p>
 * ApplicationContextAware
 * <p>
 * InitializingBean
 * <p>
 * DisposableBean
 */
 /*
 	1. 【Bean级别】构造器执行了
	2. 【Bean级别】setBeanName方法执行了
	3. 【Bean级别】setApplicationContext方法执行了
	4. 【Bean级别】afterPropertiesSet方法执行了
	5. 【Bean级别】init-method指定的方法执行了
	6. 【Bean级别】sayHello方法执行了
	7. 【Bean级别】destroy方法执行了
	8. 【Bean级别】destroy-method属性指定的方法执行了
  */

//@Configuration注解标识这是一个JavaConfig配置类
@Configuration
public class BeanLifecycle implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {
	/**
	 * 1. 构造器
	 */
	public BeanLifecycle() {
		System.out.println("1. 【Bean级别】构造器执行了");
	}

	/**
	 * 2. BeanNameAware接口方法实现
	 */
	@Override
	public void setBeanName(String name) {
		System.out.println("2. 【Bean级别】setBeanName方法执行了" + name);
	}

	/**
	 * 3. ApplicationContextAware接口方法实现
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("3. 【Bean级别】setApplicationContext方法执行了");
	}

	/**
	 * 4. InitializingBean接口方法实现
	 */
	@Override
	public void afterPropertiesSet() {
		System.out.println("4. 【Bean级别】afterPropertiesSet方法执行了");
	}

	/**
	 * 在XML配置中可以通过 init-method="lifecycleInit" 指定
	 * <p>
	 * 还可以通过注解     @PostConstruct
	 * <p>
	 * 5. init-method属性指定的方法
	 */
	@PostConstruct
	public void lifecycleInit() {
		System.out.println("5. 【Bean级别】init-method指定的方法执行了");
	}

	/**
	 * 6. Bean中的业务方法
	 */
	public void sayHello() {
		System.out.println("6. 【Bean级别】业务方法体--sayHello方法执行了");
	}

	/**
	 * 7. DisposableBean接口方法实现
	 */
	@Override
	public void destroy() throws Exception {
		System.out.println("7. 【Bean级别】destroy方法执行了");
	}

	/**
	 * destroy-method="lifecycleInitDestroy"
	 * <p>
	 * 还可以通过注解    @PreDestroy
	 * <p>
	 * 8. destroy-method属性指定的方法
	 */
	@PreDestroy
	public void lifecycleInitDestroy() {
		System.out.println("8. 【Bean级别】destroy-method属性指定的方法执行了");
	}
}
