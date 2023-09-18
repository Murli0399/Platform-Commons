package com.murli.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;
	private String name;
	private LocalDate dateOfBirth;
	private String gender;

	@Column(unique = true)
	private String uniqueStudentCode;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<StudentAddress> addresses;

	@JsonIgnore
	@ManyToMany(mappedBy = "students")
	private List<Course> courses;

}
