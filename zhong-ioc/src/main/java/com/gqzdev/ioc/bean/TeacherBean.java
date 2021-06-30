package com.gqzdev.ioc.bean;

/**
 * @ClassName: Teacher
 * @author: ganquanzhong
 * @date: 2020/7/10 10:08
 */
public class TeacherBean {

	private int tid;

	private String tname;

	private String course;

	public TeacherBean() {
	}

	public TeacherBean(int tid, String tname, String course) {
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
		return "TeacherBean{" +
				"tid=" + tid +
				", tname='" + tname + '\'' +
				", course='" + course + '\'' +
				'}';
	}
}
