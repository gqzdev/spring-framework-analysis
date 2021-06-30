package com.gqzdev.event;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * @ClassName HelloEvent
 * @Description 自定义事件
 * 				除了需要继承ApplicationEvent也没什么了，这个类有一个构造方法 需要调用super()
 *
 * 		Spring的事件(Application Event)为Bean与Bean之间的消息通信提供了支持。
 * 		当一个Bean处理完一个任务之后，希望另一个Bean知道并能做出相应的处理，这是我们就需要让另外一个Bean监听当前Bean所发送的事件。
 * @Author ganquanzhong
 * @Date 2020/9/23 20:56
 * @Version
 **/

public class HelloEvent extends ApplicationEvent implements Serializable {

	private static final long serialVersionUID = -9109444282647763145L;

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

	public String getName() {
		return name;
	}

}
