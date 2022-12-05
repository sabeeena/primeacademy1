package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;


@RestController
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/users")
	public Users create(@RequestBody Users users) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		return service.save(users);
	}

}
