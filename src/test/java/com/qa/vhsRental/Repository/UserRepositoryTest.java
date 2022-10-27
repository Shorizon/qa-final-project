package com.qa.vhsRental.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.repository.UserRepository;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	User test1;
	User test2;
	User test3;	
	
	List<User> userList;
	
	@BeforeEach
	public void setUp() {
		
	
		
		test1 = new User(1, "@Se133dd", "Mario", "Rossi", "On My Way 12",null);
		test2 = new User(2, "@Se133dd", "Marioa", "Rossia", "On My Way 122",null);
		test3 = new User(3, "@Se133dd", "Mariob", "Rossib", "On My Way 123",null);
		userList = Arrays.asList(test1,test2,test3);
		
	}
	
	@AfterEach
	public void tearDown() {
		test1 = test2 = test3 = null;
		userRepository.deleteAll();
		userList = null;
	}
	
	@Test
	@DisplayName("save-user-test")
	public void given_User_To_Save_Return_Saved_User() {
		User savedUser = userRepository.save(test1);
		assertNotNull(savedUser);
		assertEquals("Mario", savedUser.getName());
	}
	
	
	//PLEASE IF IT FAILS , REMOVE AND ADD THE IMPORT AGAIN. THE IDE SOMETIMES DOESN'T LOAD THEM WHILE SPRING IS TURNING ON
	
	@Test
	@DisplayName("get-user-with-non-existing-id-test")
	public void given_user_With_Non_Existing_ID_Return_Optional_Empty() {
		userRepository.save(test1);
		assertThat(userRepository.findById(321321).isEmpty());
	}
	
	@Test
	@DisplayName("get-user-list-test")
	public void given_AllUser_Return_User_List() {
		
		//PLEASE IF IT FAILS TO LOAD, LOAD 1 BY 1 BY COMMENTING OUT AND CHANGING ASSERTEQUAL. IDE HAS SOMETIMES ISSUE LOADING WHILE SPRING IS TURNING ON
		
		
		userRepository.save(test1); 
		userRepository.save(test2);
		userRepository.save(test3);
		
		List<User> UserList = userRepository.findAll();
		
		assertEquals(3, UserList.size());
		
	}
}
