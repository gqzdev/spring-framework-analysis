package com.gqz;

import com.gqz.config.WebConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: Test
 * @author: ganquanzhong
 * @date: 2020/4/2 11:32
 */


public class Test {
	Integer runtime = 202004;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	String strutsPepare = sdf.format(new Date());
	//boolean strutsPepareFlag = strutsPepare.equals(this.runtime);


	public static  void  main(String[] args){

		ApplicationContext ac = new AnnotationConfigApplicationContext(WebConfig.class);

		String[] names = ac.getBeanDefinitionNames();
		for (String name: names){
			System.out.println(name);
		}

		Test test = new Test();
		System.out.println(Integer.parseInt(test.strutsPepare));
		if (Integer.parseInt(test.strutsPepare)<=test.runtime){
			System.out.println("可用时间");
		}else{
			System.out.println("不可用时间");
		}
		System.out.println(WebConfig.class);
		System.out.println("整合其他第三方组件！");
	}


}
