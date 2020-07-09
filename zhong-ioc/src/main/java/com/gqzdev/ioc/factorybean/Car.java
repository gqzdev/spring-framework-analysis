package com.gqzdev.ioc.factorybean;

/**
 * @ClassName: Car
 * @author: ganquanzhong
 * @date: 2020/7/9 9:52
 */
public class Car {
	private String brand;
	private int maxSpeed;
	private double price;

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car{" +
				"brand='" + brand + '\'' +
				", maxSpeed=" + maxSpeed +
				", price=" + price +
				'}';
	}
}
