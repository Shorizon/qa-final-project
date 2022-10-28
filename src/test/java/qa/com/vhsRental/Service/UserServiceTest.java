package qa.com.vhsRental.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.exception.UserAlreadyExistsException;
import com.qa.vhsRental.repository.UserRepository;
import com.qa.vhsRental.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	
	@Mock
	private UserRepository userRepository;
	
	@Autowired
	@InjectMocks
	private UserServiceImpl userService;
	
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
	public void given_user_To_Save_Return_Saved_user() throws UserAlreadyExistsException{
		when(userRepository.findById(any())).thenReturn(Optional.empty());
		when(userRepository.save(any())).thenReturn(test1);
		
		User savedUser = userService.addUser(test1);
		
		assertNotNull(savedUser);
		assertEquals(1, savedUser.getId());
	}
	
	@Test
	@DisplayName("save-user-test-throws-exception")
	public void given_Existing_User_To_Save_Should_Throw_UserAlreadyExistsException() throws UserAlreadyExistsException{
		
		when(userRepository.findById(any())).thenReturn(Optional.of(test1));
		
		assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(test1));

	} 
	
	
}
