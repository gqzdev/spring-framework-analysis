package com.gqzdev.ioc.annotation;

/**
 * @ClassName: Dog
 * @author: ganquanzhong
 * @date: 2020/7/6 17:29
 */
public class Dog {

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Dog{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
