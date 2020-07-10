package com.gqzdev.ioc.bean;

/**
 * @ClassName: Teacher
 * @author: ganquanzhong
 * @date: 2020/7/10 10:08
 */
public class Teacher {

	private int tid;

	private String tname;

	private String course;

	public Teacher() {
	}

	public Teacher(int tid, String tname, String course) {
		this.tid = tid;
		this.tname = tname;
		this.course = course;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"tid=" + tid +
				", tname='" + tname + '\'' +
				", course='" + course + '\'' +
				'}';
	}
}
