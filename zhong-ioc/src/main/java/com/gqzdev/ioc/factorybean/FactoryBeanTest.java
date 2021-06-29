package com.gqzdev.ioc.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: FactoryBeanTest
 * @author: ganquanzhong
 * @date: 2020/7/9 10:07
 */
public class FactoryBeanTest {

	public static void main(String[] args) throws Exception {

		/**
		 * 当调用getBean("car")时，Spring通过反射机制发现CarFactoryBean实现了FactoryBean的接口，
		 * 这时Spring容器就调用接口方法CarFactoryBean#getObject()方法返回。
		 *
		 * 如果希望获取CarFactoryBean的实例，
		 * 则需要在使用getBean(beanName)方法时在beanName前显示的加上"&"前缀：如getBean("&car");
		 */
		ClassPathXmlApplicationContext act = new ClassPathXmlApplicationContext("spring-factorybean.xml");
		// 获取的是bean对象
		Object car = act.getBean("carFactoryBean");
		System.out.println("=========car=========");
		System.out.println(car.toString());

		// 获取的是bean的FactoryBean对象
		Object carFactoryBean = act.getBean("&carFactoryBean");
		System.out.println("=========carFactoryBean=========");
		System.out.println(carFactoryBean.toString());


		FactoryBean fb = (FactoryBean) carFactoryBean;
		//通过FactoryBean的getObject方法获取真实对象
		System.out.println("=========carFactoryBean.getObject()=========");
		System.out.println(fb.getObject().toString());

	}

}
