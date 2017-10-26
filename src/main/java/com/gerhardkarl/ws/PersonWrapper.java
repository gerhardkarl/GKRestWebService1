package com.gerhardkarl.ws;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Persons")
public class PersonWrapper {

	private List<Person> list;

	public PersonWrapper() {
	}

	public List<Person> getList() {
		return list;
	}

	public void setList(List<Person> list) {
		this.list = list;
	}
	
	
	
	
}
