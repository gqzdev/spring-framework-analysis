package com.gqzdev.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName Test
 * @Description
 * @Author ganquanzhong
 * @Date2020/9/24 23:35
 * @Version
 **/
public class Test {

	public static void main(String[] args){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(HelloEventListener.class);

		ac.publishEvent(new HelloEvent(ac,"hello"));

		/**
		 *	org.springframework.context.support.AbstractApplicationContext#refresh()
		 *
		 *	publishEvent(new ContextRefreshedEvent(this));
		 */
		System.out.println("Spring容器在启动后，会发布事件");
	}
}
