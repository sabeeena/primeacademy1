package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Users create(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public org.springframework.security.core.userdetails.User loadUserByUsername(String login) throws UsernameNotFoundException {
		Users users = userRepository.findByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		return new org.springframework.security.core.userdetails.User(
				users.getLogin(),
				users.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("USER")));
	}

	@Override
	public Users save(Users users) {
		return userRepository.save(users);
	}

	@Override
	public Users getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User principal =
				(org.springframework.security.core.userdetails.User) authentication.getPrincipal();
		return userRepository.findByLogin(principal.getUsername())
				.orElseThrow(() -> new IllegalArgumentException("user not found"));
	}

}
