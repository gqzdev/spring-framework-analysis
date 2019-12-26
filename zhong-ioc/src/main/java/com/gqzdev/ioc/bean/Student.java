package com.gqzdev.ioc.bean;

/**
 * @ClassName: Student
 * @author: ganquanzhong
 * @date: 2019/12/4 14:26
 */
public class Student {

	private int sid;
	private String sname;
	private int age;
	private String addr;

	public Student() {
	}

	public Student(int sid, String sname, int age, String addr) {
		this.sid = sid;
		this.sname = sname;
		this.age = age;
		this.addr = addr;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Student{" +
				"sid=" + sid +
				", sname='" + sname + '\'' +
				", age=" + age +
				", addr='" + addr + '\'' +
				'}';
	}
}
