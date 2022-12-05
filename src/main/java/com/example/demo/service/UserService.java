package com.example.demo.service;

import com.example.demo.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

	org.springframework.security.core.userdetails.User loadUserByUsername(String login) throws UsernameNotFoundException;

	Users save(Users users);

	Users getCurrentUser();
}
