package com.murli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murli.entity.User;
import com.murli.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository ur;

	public User addUser(User user) {
		User newUser = ur.save(user);
		return newUser;
	}

}
