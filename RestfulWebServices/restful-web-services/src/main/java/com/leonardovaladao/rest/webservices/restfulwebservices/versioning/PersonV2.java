package com.leonardovaladao.rest.webservices.restfulwebservices.versioning;

public class PersonV2 {

	private Name name;

	public PersonV2(String firstName, String lastName) {
		super();
		this.name = new Name(firstName, lastName);
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PersonV2 [name=" + name + "]";
	}
}
