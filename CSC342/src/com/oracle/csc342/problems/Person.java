package com.oracle.csc342.problems;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
	
public class Person {
	
	BigDecimal personId;
	String firstName;
	String lastName;
	Address address;
	Timestamp birthDate;
	String phoneNum;
	String faxNum;
	
	public Person() {
		
	}
	/**
	 * @param personId
	 */
	public Person(BigDecimal personId) {
		this.personId = personId;
		this.address = new Address();
	}
	public BigDecimal getPersonId() {
		return personId;
	}
	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Timestamp getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getFaxNum() {
		return faxNum;
	}
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String toString() {
		return ("Id: " + getPersonId() + "Name: " + getFirstName() + " " + getLastName() + " " + "Birthdate: " + (new SimpleDateFormat("MM/dd/yyyy").format(getBirthDate())) +
				" " + "Phone Number: " + " " + getPhoneNum());
	}

	
}
