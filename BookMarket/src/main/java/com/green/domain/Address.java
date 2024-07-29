package com.green.domain;

import java.io.Serializable;

public class Address implements Serializable{
	
	/* Java 에서 직렬화(Serialization)란 자바의 객체를 바이트의 배열로 변환하여 
	 * 파일,메모리,데이터베이스 등을 통해서 Stream(송수신)이 가능하도록 하는 것을 의미함.
	 * 즉, Java 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 Java 시스템 에서도 
	 * 사용 할 수 있도록 바이트(byte) 형태로 데이터 변환 하는것을 의미함.
	 * Java에서는 직렬화를 위해서 Serializable 이라는 interface를 지원하는데
	 * 사용하기 위해서 이를 상속(implements) 하여야 한다. 
	 * Serializable interface를 상속 받은 클래스는 반드시 serialVersionUID를 
	 * 정의 해야 한다. */
	private static final long serialVersionUID = 613846598817670033L;
	
	private String detailName;     //세부주소  
	private String addressName;   //주소 
	private String country;        //국가명
	private String zipCode;        //우편번호
	
	//getter, setter
	public String getDetailName() {
		return detailName;
	}
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	//hashCode() , equals() 오버라이드
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressName == null) ? 0 : addressName.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((detailName == null) ? 0 : detailName.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		Address other = (Address) obj;
		if (addressName == null) {
			if (other.addressName != null)
				return false;
		} else if (!addressName.equals(other.addressName))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (detailName == null) {
			if (other.detailName != null)
				return false;
		} else if (!detailName.equals(other.detailName))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	
}
