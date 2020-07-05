package com.gqzdev.ioc.circulardependency;

/**
 * @ClassName A
 * @Description
 * @Author ganquanzhong
 * @Date2020/7/5 14:31
 * @Version
 **/
public class A {

	private B b;

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
}
