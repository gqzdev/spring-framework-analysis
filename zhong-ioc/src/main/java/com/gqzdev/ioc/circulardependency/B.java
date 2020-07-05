package com.gqzdev.ioc.circulardependency;

/**
 * @ClassName B
 * @Description
 * @Author ganquanzhong
 * @Date2020/7/5 14:31
 * @Version
 **/
public class B {

	private A a;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
}
