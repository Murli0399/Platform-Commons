package com.murli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.murli.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

}
