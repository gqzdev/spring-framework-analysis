package com.gqzdev.ioc.parsebeandefinition.lookupmethod;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName lookupMethodTest
 *
 * 		获取器注入；
 *		把一个方法声明为返回某种类型的bean
 *		但实际要返回的bean 是在配置文件里面配置的
 *
 * @Description
 * @Author ganquanzhong
 * @Date2020/7/7 22:03
 * @Version
 **/
public class lookupMethodTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext act = new ClassPathXmlApplicationContext("spring-beandefinition.xml");

		/*
		 	可插拔的功能上，解除程序依赖  应用场景
		 	抽象方法还没有被实现，怎么可以直接调用呢?
			bean元素的子元素 lookup-method ，这个配置完成的功能是
				动态地将teacher所代表的bean作为getBean 的返回值
		 */
		LoudSpeaker loudSpeaker = act.getBean("loudSpeaker", LoudSpeaker.class);
		loudSpeaker.speak();
	}
}
