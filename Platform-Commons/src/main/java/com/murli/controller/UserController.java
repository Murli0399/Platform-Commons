package com.murli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.murli.entity.User;
import com.murli.repository.UserRepository;
import com.murli.service.UserService;


@RestController
public class UserController {

	@Autowired
	private UserService us;

	@Autowired
	private UserRepository ur;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/add-user")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_ADMIN");
		User addedUser = us.addUser(user);
		return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
	}

	@GetMapping("/forAll")
	public ResponseEntity<String> helloforAll() {
		return new ResponseEntity<String>("hello for All ", HttpStatus.ACCEPTED);
	}

	@GetMapping("/signin")
	public ResponseEntity<User> getLoggedInCustomerDetailsHandler(Authentication auth) {

		User user = ur.findByUsername(auth.getName())
				.orElseThrow(() -> new RuntimeException("Invalid Username or password"));

		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);

	}
}
