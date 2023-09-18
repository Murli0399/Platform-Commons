package com.murli.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murli.dto.CourseDTO;
import com.murli.dto.StudentDTO;
import com.murli.entity.Course;
import com.murli.entity.Student;
import com.murli.entity.StudentAddress;
import com.murli.repository.CourseRepository;
import com.murli.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentRepository sr;

	@Autowired
	private CourseRepository cr;

	// Admit Student
	public Student admitStudent(Student student) {

		List<StudentAddress> sa = student.getAddresses();

		sa.forEach(x -> x.setStudent(student));

		Student newStudent = sr.save(student);

		return newStudent;
	}

	// Update Student Profile
	public StudentDTO updateStudentProfile(Integer studentId, StudentDTO studentDTO) {
		Optional<Student> optionalStudent = sr.findById(studentId);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			student.setName(studentDTO.getName());
			student.setDateOfBirth(studentDTO.getDateOfBirth());
			student.setGender(studentDTO.getGender());
			student.setUniqueStudentCode(studentDTO.getUniqueStudentCode());
			student.setAddresses(studentDTO.getAddress());

			student = sr.save(student);
			return mapStudentToDTO(student);
		} else {
			throw new EntityNotFoundException("Student not found with ID: " + studentId);
		}
	}

	// Get Students By Name
	public List<StudentDTO> getStudentsByName(String name) {
		List<Student> students = sr.findByName(name);

		if (students.isEmpty())
			throw new EntityNotFoundException("Student Not present with " + name);

		return students.stream().map(this::mapStudentToDTO).collect(Collectors.toList());
	}

	// Assign Courses to Students
	public void assignCoursesToStudent(Integer studentId, List<Integer> courseIds) {
		Optional<Student> optionalStudent = sr.findById(studentId);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			List<Course> courses = cr.findAllById(courseIds);
			courses.forEach(c -> c.getStudents().add(student));
			student.getCourses().addAll(courses);
			sr.save(student);
		} else {
			throw new EntityNotFoundException("Student not found with ID: " + studentId);
		}
	}

	// Get Courses By Student Id
	public List<CourseDTO> coursesByStudentId(Integer studentId) {
		Optional<Student> std = sr.findById(studentId);
		if (!std.isPresent()) {
			throw new EntityNotFoundException("Student Not Found with Id : " + studentId);
		}
		Student student = std.get();
		List<Course> courses = student.getCourses();

		if (courses.isEmpty())
			throw new EntityNotFoundException("Courses Not Register with Student Id : " + studentId);

		return courses.stream().map(this::mapCourseToDTO).collect(Collectors.toList());

	}

	// Student Leave Course
	public void leaveCourse(Integer studentId, Integer courseId) {
		Optional<Student> std = sr.findById(studentId);
		if (std.isPresent()) {
			Student student = std.get();
			student.getCourses().removeIf(course -> course.getId().equals(courseId));
			sr.save(student);
		} else {
			throw new EntityNotFoundException("Student not found with ID: " + studentId);
		}
	}

	private StudentDTO mapStudentToDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setName(student.getName());
		studentDTO.setDateOfBirth(student.getDateOfBirth());
		studentDTO.setGender(student.getGender());
		studentDTO.setUniqueStudentCode(student.getUniqueStudentCode());
		studentDTO.setAddress(student.getAddresses());

		return studentDTO;
	}

	private CourseDTO mapCourseToDTO(Course course) {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setCourseName(course.getCourseName());
		courseDTO.setDescription(course.getDescription());
		courseDTO.setCourseType(course.getCourseType());
		courseDTO.setDuration(course.getDuration());
		courseDTO.setTopics(course.getTopics());
		return courseDTO;
	}

}
