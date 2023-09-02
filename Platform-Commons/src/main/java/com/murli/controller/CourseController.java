package com.murli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.murli.dto.CourseDTO;
import com.murli.dto.StudentDTO;
import com.murli.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService cs;

	@PostMapping("/upload")
	public ResponseEntity<CourseDTO> uploadCourseDetails(@RequestBody CourseDTO courseDTO) {
		CourseDTO uploadedCourse = cs.uploadCourseDetails(courseDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(uploadedCourse);
	}

	@GetMapping("/{courseId}/students")
	public ResponseEntity<List<StudentDTO>> getStudentsByCourse(@PathVariable Integer courseId) {
		List<StudentDTO> students = cs.getStudentsByCourse(courseId);
		return ResponseEntity.ok(students);
	}
}
