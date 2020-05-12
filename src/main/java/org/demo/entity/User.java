package org.demo.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	
	public User() {
	}
	
	public User(Integer id, String username, String password, String gender, String address, Date birthday,
			String avatar, String hobbies, Date regTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.birthday = birthday;
		this.avatar = avatar;
		this.hobbies = hobbies;
		this.regTime = regTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender
				+ ", address=" + address + ", birthday=" + birthday + ", avatar=" + avatar + ", hobbies=" + hobbies
				+ ", regTime=" + regTime + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String gender;
	
	private String address;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	
	private String avatar;
	
	private String hobbies;
	
	private Date regTime;

}
