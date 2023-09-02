package com.murli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.murli.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByName(String name);

	Student findByUniqueStudentCode(String studentCode);

}
