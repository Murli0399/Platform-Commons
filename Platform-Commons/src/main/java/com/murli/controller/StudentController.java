package com.murli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.murli.dto.StudentDTO;
import com.murli.dto.StudentValidationRequest;
import com.murli.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService ss;

	@PutMapping("/{studentId}")
	public ResponseEntity<StudentDTO> updateStudentProfile(@PathVariable Integer studentId,
			@RequestBody StudentDTO studentDTO) {
		StudentDTO updatedStudent = ss.updateStudentProfile(studentId, studentDTO);
		return ResponseEntity.ok(updatedStudent);
	}

	@GetMapping("/search")
	public ResponseEntity<List<StudentDTO>> getStudentsByName(@RequestParam String name) {
		List<StudentDTO> students = ss.getStudentsByName(name);
		return ResponseEntity.ok(students);
	}

	@PostMapping("/validate")
	public ResponseEntity<?> validateStudent(@RequestBody StudentValidationRequest validationRequest) {
		boolean isValid = ss.validateStudent(validationRequest.getStudentCode(), validationRequest.getDateOfBirth());

		if (isValid) {
			return ResponseEntity.ok("Student validation successful!");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid student credentials");
		}
	}
}
