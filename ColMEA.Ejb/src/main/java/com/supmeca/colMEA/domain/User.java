package com.supmeca.colMEA.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer id_user;
	private String login;
	private String password;
	private String first_name;
	private String last_name;
	private Integer age;
	private String email;
	private String image;
	private String service;
	private String note;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)

	public Integer getId_user() {
		return id_user;
	}
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//constructor with fields
	public User(String login,String password ,String first_name, String last_name, Integer age,
			String email, String image, String service, String note) {
		super();
		this.login = login;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.email = email;
		this.image = image;
		this.service = service;
		this.note = note;
	}
	//constructor with superclass
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	
	
}
