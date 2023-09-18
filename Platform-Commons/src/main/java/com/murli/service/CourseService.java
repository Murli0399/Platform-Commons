package com.murli.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murli.dto.CourseDTO;
import com.murli.dto.StudentDTO;
import com.murli.entity.Course;
import com.murli.entity.Student;
import com.murli.repository.CourseRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseService {

	@Autowired
	private CourseRepository cr;

	// Upload Course
	public CourseDTO uploadCourseDetails(CourseDTO courseDTO) {
		Course course = new Course();
		course.setCourseName(courseDTO.getCourseName());
		course.setDescription(courseDTO.getDescription());
		course.setCourseType(courseDTO.getCourseType());
		course.setDuration(courseDTO.getDuration());
		course.setTopics(courseDTO.getTopics());

		course = cr.save(course);

		return mapCourseToDTO(course);
	}

	// Get Students By Course Id
	public List<StudentDTO> getStudentsByCourse(Integer courseId) {
		Course course = cr.findById(courseId)
				.orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

		List<Student> students = course.getStudents();
		return students.stream().map(this::mapStudentToDTO).collect(Collectors.toList());
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

	private StudentDTO mapStudentToDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setName(student.getName());
		studentDTO.setDateOfBirth(student.getDateOfBirth());
		studentDTO.setGender(student.getGender());
		studentDTO.setUniqueStudentCode(student.getUniqueStudentCode());

		return studentDTO;
	}
}
