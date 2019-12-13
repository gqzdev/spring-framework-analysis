package com.gqzdev.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AppConfig
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/5 22:58
 * @Version
 **/

//@Configuration 全注解    判断是否需要代理  isFullAnnotated.....
//该注解保证@Bean对象为单例的
@Configuration
@ComponentScan("com.gqzdev.bean")
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
