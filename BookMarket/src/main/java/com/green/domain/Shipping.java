package com.green.domain;

import java.io.Serializable;
import java.util.Date;

public class Shipping implements Serializable{
	
	private static final long serialVersionUID = 8121814661110003493L;
	 
	 
	private String name;        //��� ����
	private Date date;          //�����
	private Address address;   //��� �ּ� ��ü
	
	
	public Shipping() {
		this.address = new Address();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
