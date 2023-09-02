package com.murli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.murli.dto.StudentDTO;
import com.murli.service.StudentService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private StudentService ss;

	@GetMapping("/welcome")
	public ResponseEntity<String> welcomeAdmin() {
		return ResponseEntity.ok("Welcome, Admin!");
	}

	@PostMapping("/admit")
	public ResponseEntity<StudentDTO> admitStudent(@RequestBody StudentDTO studentDTO) {
		StudentDTO admittedStudent = ss.admitStudent(studentDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(admittedStudent);
	}

	@PostMapping("/{studentId}/courses")
	public ResponseEntity<Void> assignCoursesToStudent(@PathVariable Integer studentId,
			@RequestBody List<Integer> courseIds) {
		ss.assignCoursesToStudent(studentId, courseIds);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{studentId}/courses/{courseId}")
	public ResponseEntity<Void> leaveCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
		ss.leaveCourse(studentId, courseId);
		return ResponseEntity.ok().build();
	}
}
