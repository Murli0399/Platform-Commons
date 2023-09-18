package com.murli.dto;

import java.time.LocalDate;
import java.util.List;

import com.murli.entity.StudentAddress;

public class StudentDTO {
	private String name;
	private LocalDate dateOfBirth;
	private String gender;
	private String uniqueStudentCode;

	private List<StudentAddress> address;

	public StudentDTO() {
		super();
	}

	public StudentDTO(String name, LocalDate dateOfBirth, String gender, String uniqueStudentCode,
			List<StudentAddress> address) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.uniqueStudentCode = uniqueStudentCode;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUniqueStudentCode() {
		return uniqueStudentCode;
	}

	public void setUniqueStudentCode(String uniqueStudentCode) {
		this.uniqueStudentCode = uniqueStudentCode;
	}

	public List<StudentAddress> getAddress() {
		return address;
	}

	public void setAddress(List<StudentAddress> address) {
		this.address = address;
	}

}
