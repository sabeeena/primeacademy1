package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public org.springframework.security.core.userdetails.User loadUserByUsername(String login) throws UsernameNotFoundException {
		Users users = userRepository.findByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		return new org.springframework.security.core.userdetails.User(
				users.getLogin(),
				users.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("USER")));
	}

}