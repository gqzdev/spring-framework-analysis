package com.gqzdev.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName HelloEventListener
 * @Description
 * 		事件监听器需要实现ApplicationListener接口，这是个泛型接口
 * @Author ganquanzhong
 * @Date2020/9/23 21:06
 * @Version
 **/
@Configuration
public class HelloEventListener implements ApplicationListener<HelloEvent> {
	@Override
	public void onApplicationEvent(HelloEvent event) {
		if (event.getName().equals("hello")){
			System.out.println("监听到事件了！！");
		}
	}
}
