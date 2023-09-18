package com.murli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.murli.dto.CourseDTO;
import com.murli.dto.StudentDTO;
import com.murli.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService ss;

	@PutMapping("/{studentId}")
	public ResponseEntity<StudentDTO> updateStudentProfile(@PathVariable Integer studentId,
			@RequestBody StudentDTO studentDTO) {
		StudentDTO updatedStudent = ss.updateStudentProfile(studentId, studentDTO);
		return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
	}

	@GetMapping("/courses/{studentId}")
	public ResponseEntity<List<CourseDTO>> coursesByStudentId(@PathVariable Integer studentId) {
		List<CourseDTO> courseDTO = ss.coursesByStudentId(studentId);
		return new ResponseEntity<>(courseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{studentId}/courses/{courseId}")
	public ResponseEntity<String> leaveCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
		ss.leaveCourse(studentId, courseId);
		return new ResponseEntity<>("Leave Success", HttpStatus.OK);
	}

}
