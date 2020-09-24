package com.gqzdev.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName HelloEvent 事件
 * @Description
 * 		自定义事件
 * 		除了需要继承ApplicationEvent也没什么了，这个类有一个构造方法需要super
 * @Author ganquanzhong
 * @Date2020/9/23 20:56
 * @Version
 **/
public class HelloEvent extends ApplicationEvent {

	private String name;

	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public HelloEvent(Object source, String name) {
		//需要有一个构造方法 ，super
		super(source);
		this.name = name;
	}

	public String getName(){
		return name;
	}

}
