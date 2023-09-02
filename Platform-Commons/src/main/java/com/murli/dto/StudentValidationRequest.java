package com.murli.dto;

import java.time.LocalDate;

public class StudentValidationRequest {
	private String studentCode;
	private LocalDate dateOfBirth;

	public StudentValidationRequest() {
		super();
	}

	public StudentValidationRequest(String studentCode, LocalDate dateOfBirth) {
		super();
		this.studentCode = studentCode;
		this.dateOfBirth = dateOfBirth;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
