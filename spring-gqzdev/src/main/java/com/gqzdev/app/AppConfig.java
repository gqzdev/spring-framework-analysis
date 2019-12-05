package com.gqzdev.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AppConfig
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/5 22:58
 * @Version
 **/

//@Configuration  //该注解保证@Bean对象为单例的
public class AppConfig {

	@Bean
	public X x(){
		return new X("x1");
	}

	@Bean
	public Y y(){
		x();
		return new Y();
	}
}
