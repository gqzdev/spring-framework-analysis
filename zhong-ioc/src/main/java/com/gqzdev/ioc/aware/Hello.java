package com.gqzdev.ioc.aware;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: Hello
 * @author: ganquanzhong
 * @date: 2020/7/10 11:39
 */
@Configuration
@ComponentScan("com.gqzdev.ioc.aware")
public class Hello {

	public void say(String sayContent){
		System.out.println(sayContent+" hello world!");
	}


	/*@Bean(initMethod = "initMethod")
	public HelloAware helloAware(){
		return new HelloAware();
	}*/
}
