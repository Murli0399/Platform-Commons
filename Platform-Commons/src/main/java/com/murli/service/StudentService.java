package com.murli.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murli.dto.StudentDTO;
import com.murli.entity.Course;
import com.murli.entity.Student;
import com.murli.repository.CourseRepository;
import com.murli.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentRepository sr;

	@Autowired
	private CourseRepository cr;

	public StudentDTO admitStudent(StudentDTO studentDTO) {
		Student student = new Student();
		student.setName(studentDTO.getName());
		student.setDateOfBirth(studentDTO.getDateOfBirth());
		student.setGender(studentDTO.getGender());
		student.setUniqueStudentCode(studentDTO.getUniqueStudentCode());

		student = sr.save(student);

		return mapStudentToDTO(student);
	}

	public StudentDTO updateStudentProfile(Integer studentId, StudentDTO studentDTO) {
		Optional<Student> optionalStudent = sr.findById(studentId);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			student.setName(studentDTO.getName());
			student.setDateOfBirth(studentDTO.getDateOfBirth());
			student.setGender(studentDTO.getGender());
			student.setUniqueStudentCode(studentDTO.getUniqueStudentCode());

			student = sr.save(student);
			return mapStudentToDTO(student);
		} else {
			throw new EntityNotFoundException("Student not found with ID: " + studentId);
		}
	}

	public List<StudentDTO> getStudentsByName(String name) {
		List<Student> students = sr.findByName(name);
		return students.stream().map(this::mapStudentToDTO).collect(Collectors.toList());
	}

	public void assignCoursesToStudent(Integer studentId, List<Integer> courseIds) {
		Optional<Student> optionalStudent = sr.findById(studentId);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			List<Course> courses = cr.findAllById(courseIds);
			student.getCourses().addAll(courses);
			sr.save(student);
		} else {
			throw new EntityNotFoundException("Student not found with ID: " + studentId);
		}
	}

	public void leaveCourse(Integer studentId, Integer courseId) {
		Optional<Student> optionalStudent = sr.findById(studentId);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
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

		return studentDTO;
	}

	public boolean validateStudent(String studentCode, LocalDate dateOfBirth) {

		Student student = sr.findByUniqueStudentCode(studentCode);

		if (student == null) {
			return false;
		}

		if (student.getDateOfBirth().isEqual(dateOfBirth)) {
			return true;
		}

		return false;
	}

}
