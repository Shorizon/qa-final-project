package qa.com.vhsRental.Controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.vhsRental.controller.UserController;
import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@Mock
	private UserServiceImpl userService;
	
	@Autowired
	@InjectMocks
	private UserController userController;
	

	@Autowired
	MockMvc mockMvc;
	

	User test1;
	User test2;
	User test3;	
	
	List<User> userList;
	
	@BeforeEach
	public void setUp() {
		
	
		
		test1 = new User(1, "@Se133dd", "Mario", "Rossi", "On My Way 121",null);
		test2 = new User(2, "@Se133dd", "Marioa", "Rossia", "On My Way 122",null);
		test3 = new User(3, "@Se133dd", "Mariob", "Rossib", "On My Way 123",null);
		userList = Arrays.asList(test1,test2,test3);
		
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@AfterEach
	public void tearDown() {
		test1 = test2 = test3 = null;
		userList = null;
	}
	
	@Test
	@DisplayName("save-user-test")
	public void given_User_To_Save_User_Should_Return_User_As_JSON_With_Status_Create() throws Exception{
		when(userService.signup(any())).thenReturn(test1);
		mockMvc.perform(post("/api/v1/signup")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(test1)))
        		.andExpect(status().isCreated())
        		.andExpect(jsonPath("$.name").value("Mario"));
				
	}

	
	@Test
	@DisplayName("get-user-test")
	public void given_AllUsers_Should_Return_List() throws Exception {
		when(userService.getAllUsers()).thenReturn(userList);
		mockMvc.perform(get("/api/v1/users")
				        .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].address").value("On My Way 122"));
	}


public static String asJsonString(Object obj) {
	ObjectMapper Obj = new ObjectMapper();
	String jsonStr = null;
	
    try {

        jsonStr = Obj.writeValueAsString(obj);
        System.out.println(jsonStr);
    }
    catch (IOException e) {
    	System.out.println("SOME INTERNAL ERROR HAS OCCURED..");
        e.printStackTrace();
    }
    return jsonStr;
}
}