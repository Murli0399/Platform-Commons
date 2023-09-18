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
import com.murli.entity.Student;
import com.murli.service.CourseService;
import com.murli.service.StudentService;


@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private StudentService ss;

	@Autowired
	private CourseService cs;

	@GetMapping
	public String welcomeAdmin() {
		return "Welcome, Admin!";
	}

	@PostMapping("/admit")
	public ResponseEntity<Student> admitStudent(@RequestBody Student student) {
		Student admittedStudent = ss.admitStudent(student);
		return new ResponseEntity<>(admittedStudent, HttpStatus.CREATED);
	}

	@PostMapping("/upload")
	public ResponseEntity<CourseDTO> uploadCourseDetails(@RequestBody CourseDTO courseDTO) {
		CourseDTO uploadedCourse = cs.uploadCourseDetails(courseDTO);
		return new ResponseEntity<>(uploadedCourse, HttpStatus.CREATED);
	}

	@GetMapping("/name/{studentName}")
	public ResponseEntity<List<StudentDTO>> getStudentsByName(@PathVariable String studentName) {
		List<StudentDTO> students = ss.getStudentsByName(studentName);
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@PostMapping("/{studentId}/courses")
	public ResponseEntity<String> assignCoursesToStudent(@PathVariable Integer studentId,
			@RequestBody List<Integer> courseIds) {
		ss.assignCoursesToStudent(studentId, courseIds);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@GetMapping("/{courseId}/students")
	public ResponseEntity<List<StudentDTO>> getStudentsByCourse(@PathVariable Integer courseId) {
		List<StudentDTO> students = cs.getStudentsByCourse(courseId);
		return ResponseEntity.ok(students);
	}

}
