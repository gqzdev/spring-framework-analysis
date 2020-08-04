package com.gqzdev.aop;

/**
 * @ClassName TestBean
 * @Description
 *
 *  此bean 可能是满足业务需要的核心逻辑，例如test 方法中可能会封装着某
 * 个核心业务，但是， 如果我们想在test 前后加入日志来跟踪调试，如果直接修改源码并不符合
 * 面向对象的设计方法，而且随意改动原有代码也会造成一定的风险，还好接下来的Spring 帮我
 * 们做到了这一点。
 *
 * @Author ganquanzhong
 * @Date2020/8/4 23:12
 * @Version
 **/
public class TestBean {
	private String testStr = "testStr";

	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}

	/**
	 * 	使用aop  增强test方法
	 *
	 */
	public void test(){
		System.out.println("test");
		//int a = 1/0;
	}

	public static void main(String[] args){

	}
}
