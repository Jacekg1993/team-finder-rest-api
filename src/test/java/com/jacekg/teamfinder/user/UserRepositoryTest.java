package com.jacekg.teamfinder.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.jacekg.teamfinder.role.Role;

@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void findByUsername_ShouldReturn_Valid_User() {
		
		String username = "user";
		
		User user = new User(
				1L,
				"user",
				"password",	
				"email@com",
				true,
				true,
				true,
				true, 
				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
		
		userRepository.save(user);
		
		User returnedUser = userRepository.findByUsername(username);
		
		assertEquals("user", returnedUser.getUsername());
		assertEquals("ROLE_ADMIN", returnedUser.getRoleName());
	}
	
	@Test
	void findByUsername_ShouldReturn_Null() {
		
		String username = "user";
		
		User returnedUser = userRepository.findByUsername(username);
		
		assertThat(returnedUser).isNull();
	}

	@Test
	void findByUserId_ShouldReturn_Valid_User() {
		
		Long userID = 1L;
		
		User user = new User(
				1L,
				"user",
				"password",
				"email@com",
				true,
				true,
				true,
				true, 
				Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
		
		userRepository.save(user);
		
		User returnedUser = userRepository.findByUserId(userID);
		
		assertEquals(1L, returnedUser.getId());
		assertEquals("ROLE_ADMIN", returnedUser.getRoleName());
	}
	
	@Test
	void findByUserId_ShouldReturn_Null() {
		
		Long userID = 1L;
		
		User returnedUser = userRepository.findByUserId(userID);
		
		assertThat(returnedUser).isNull();
	}
}