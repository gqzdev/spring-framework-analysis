package com.gqzdev.ioc.bean;

/**
 * @ClassName User
 * @Description
 * @Author ganquanzhong
 * @Date2019/12/3 22:38
 * @Version
 **/
public class User {
	private int uid;
	private String username;
	private String pwd;
	private String tel;
	private String addr;

	/**
		必须有一个空的构造方法
	 */
	public User() {
	}

	/**
	 通过xml的bean中的  setter getter注入 构造器注入方式
	 */
	public User(int uid, String username, String pwd, String tel, String addr) {
		this.uid = uid;
		this.username = username;
		this.pwd = pwd;
		this.tel = tel;
		this.addr = addr;
	}
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "User{" +
				"uid=" + uid +
				", username='" + username + '\'' +
				", pwd='" + pwd + '\'' +
				", tel='" + tel + '\'' +
				", addr='" + addr + '\'' +
				'}';
	}

}
