package com.leonardovaladao.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;

public class User {

	private Integer id;
	
	@Min(value = 2, message = "The name should have atleast 2 characters.")
	private String name;
	
	@Past(message = "The birth date should be in the past.")
	private LocalDate birth;
	
	public User(Integer id, String name, LocalDate birth) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birth=" + birth + "]";
	}
}
