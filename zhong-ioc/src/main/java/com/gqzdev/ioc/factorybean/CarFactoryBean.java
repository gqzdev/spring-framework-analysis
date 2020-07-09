package com.gqzdev.ioc.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName: CarFactoryBean
 *
 * 	实现FactoryBean接口，
 * 	Spring通过反射机制发现CarFactoryBean实现了FactoryBean的接口，
 * 		这时Spring容器就调用接口方法CarFactoryBean#getObject()方法返回。
 *
 * @author: ganquanzhong
 * @date: 2020/7/9 9:59
 */
public class CarFactoryBean implements FactoryBean {

	private String carInfo;

	/**
	 *	自定义创建bean的过程，可以定制复杂的创建过程
	 */
	@Override
	public Object getObject() throws Exception {
		Car car = new Car();
		String[] infos = carInfo.split(",");
		car.setBrand(infos[0]);
		car.setMaxSpeed(Integer.valueOf(infos[1]));
		car.setPrice(Double.valueOf(infos[2]));
		return car;
	}

	/**
	 *	获取FactoryBean创建bean的类型
	 */
	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

	/**
	 *	创建的bean，是否是单例
	 */
	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getCarInfo() {
		return carInfo;
	}

	/**
	 *	接受逗号分隔符，设置属性
	 */
	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}
}
