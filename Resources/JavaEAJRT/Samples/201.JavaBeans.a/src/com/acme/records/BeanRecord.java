package com.acme.records;

import java.io.Serializable;

public class BeanRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
	private String name;
	private String userId;
	private String password;

	public BeanRecord() {
	}

	public BeanRecord(String email, String name, String userId, String password) {
		this();
		this.email = email;
		this.name = name;
		this.userId = userId;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "BeanRecord" + this.hashCode() + " [email=" + email + ", name="
				+ name + ", userId=" + userId + ", password=" + password + "]";
	}
}
