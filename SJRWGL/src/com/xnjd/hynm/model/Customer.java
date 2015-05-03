package com.xnjd.hynm.model;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */

public class Customer implements java.io.Serializable {

	// Fields

	private Integer id;
	private String customerName;
	private String contact;
	private String province;
	private String city;
	private String county;
	private String person;
	private String number;
	private String address;

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/**
	 * @param customerName
	 */
	public Customer(String customerName) {
		super();
		this.customerName = customerName;
	}

	/** full constructor */
	public Customer(String customerName, String contact, String province,
			String city, String county, String person, String number,
			String address) {
		this.customerName = customerName;
		this.contact = contact;
		this.province = province;
		this.city = city;
		this.county = county;
		this.person = person;
		this.number = number;
		this.address = address;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



}