package com.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Object class to be written in database
 * 
 * @author alperenp
 *
 */
@Entity
public class Person {
	public Person() {
		// Needed by hibernate
		// Else you get:
		// Caused by: org.hibernate.InstantiationException: No default constructor for entity: :com.hibernate.Person
	}
	
	public Person(String string, int i) {
		name = string;
		age = i;
	}
	
	@Id @GeneratedValue private int id;
	@Column(name = "FULL_NAME") private String name;
	private int age;
	
	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
}
