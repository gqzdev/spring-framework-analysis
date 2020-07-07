package com.gqzdev.ioc.parsebeandefinition.placedmethod;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: ParseReplacedMethod
 *
 * 		方法替换：可以在运行时用新的方法替换现有的方法。与之前的lookup不同的是，
 * 		replaced-method不但可以动态地替换返回实体bean ，而且还能动态地更改原有方法的逻辑。
 *
 * @author: ganquanzhong
 * @date: 2020/7/7 17:09
 */
public class ParseReplacedMethodTest {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext act = new ClassPathXmlApplicationContext("spring-beandefinition.xml");
		ChangeMethod changeMethod = act.getBean("changeMethod", ChangeMethod.class);
		changeMethod.changeMe();
	}
}
