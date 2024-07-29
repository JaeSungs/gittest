package com.green.domain;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 3636831123198280235L;
	
	private String customerId;         //°í°´ID 
	private String name;               //°í°´¸í
	private Address address;          //°í°´ ÁÖ¼Ò °´Ã¼
	private String phone;             //°í°´ ÀüÈ­¹øÈ£
	
	//getter, setter
	public Customer() {
		this.address= new Address();
	}
	public Customer(String customerId, String name) {
		 this();
		this.customerId = customerId;
		this.name = name;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	//hashCode(), equals() ¿À¹ö¶óÀÌµå
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}
	
	
	
	
}
