package com.gqzdev.ioc.annotation;

import org.springframework.stereotype.Service;

/**
 *
 */
@Service("student01")
public class Student implements HomeWork {

	private String sid;
	private String sname;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	/**
     * 写家庭作业
     */
    @Override
    public void doHomeWork() {
        System.out.println("我是学生，我要写家庭作业");
    }
}
