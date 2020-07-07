package com.gqzdev.ioc.parsebeandefinition.lookupmethod;

/**
 * @ClassName GetBeanTest
 *
 *  获取器注入是一种特殊的方法注入，
 *  它是把一个方法声明为返回某种类型的bean ，
 *  但实际要返回的bean 是在配置文件里面配置的，此方法可用在设计有些可插拔的功能上，解除程序依赖。
 *
 * @Description
 * @Author ganquanzhong
 * @Date2020/7/7 21:58
 * @Version
 **/
public abstract class LoudSpeaker {

	// 获取user对象的抽象方法
	public abstract User getUser();

	public void speak(){
		this.getUser().speak();
	}
}
