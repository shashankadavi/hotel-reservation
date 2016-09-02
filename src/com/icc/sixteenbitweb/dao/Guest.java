package com.icc.sixteenbitweb.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Guest {
	
	private String name;
	private String username;
	private String password;
	private String email;
	private String authority;
	
	@Id
	private int id;
	
	public Guest(){
		
	}
	
	public Guest(String name, String username, String password, String email, String authority) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authority = authority;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Guest [name=" + name + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", authority=" + authority + ", id=" + id + "]";
	}
	
}