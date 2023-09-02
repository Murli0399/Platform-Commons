package com.murli.dto;

public class StudentAddressDTO {
	private String area;
	private String state;
	private String district;
	private String pincode;
	private String addressType;

	public StudentAddressDTO() {
		super();
	}

	public StudentAddressDTO(String area, String state, String district, String pincode, String addressType) {
		super();
		this.area = area;
		this.state = state;
		this.district = district;
		this.pincode = pincode;
		this.addressType = addressType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

}
