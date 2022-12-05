package com.example.demo;


import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private PasswordEncoder passwordEncoder;
	@Test
	public void testCreate() {
		Users user = new Users();
		user.setPassword("Qwerty123!");
		String encodedPassword = "jf2w0jisdlfnweijdf0qjiedsolkfmwepofdjmsd";
		Mockito.doReturn(encodedPassword).when(passwordEncoder)
				.encode(user.getPassword());
		userService.create(user);
		Mockito.verify(userRepository).save(user);
		Assertions.assertEquals(encodedPassword, user.getPassword());
	}
}
