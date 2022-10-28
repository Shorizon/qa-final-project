package com.qa.vhsRental.controller;

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
import com.qa.vhsRental.entity.VHS;
import com.qa.vhsRental.service.VHSServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VHSControllerTest {


	@Mock
	private VHSServiceImpl vhsService;
	
	@Autowired
	@InjectMocks
	private VHSController vhsController;
	
	
	@Autowired
	MockMvc mockMvc;
	
	VHS test1;
	VHS test2;
	VHS test3;
	
	List<VHS> vhsList;
	
	@BeforeEach
	public void setUp() {

		
		
		test1 = new VHS(1, "Godsacio", "Scream", true);
		test2 = new VHS(2, "Gosdsdcio2", "Scream2", true);
		test3 = new VHS(3, "Gocdsdsio3", "Scream", false);
		vhsList = Arrays.asList(test1,test2,test3);
		
		mockMvc = MockMvcBuilders.standaloneSetup(vhsController).build();
	}
	
	@AfterEach
	public void tearDown() {
		test1 = test2 = test3 = null;
		vhsList = null;
	}
	
	@Test
	@DisplayName("save-vhs-test")
	public void given_vhs_To_Save_vhs_Should_Return_vhs_As_JSON_With_Status_Create() throws Exception{
		when(vhsService.addVHS(any())).thenReturn(test1);
		mockMvc.perform(post("/api/v1/vhs")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(test1)))
        		.andExpect(status().isCreated())
        		.andExpect(jsonPath("$.name").value("Godsacio"));
				
	}
	
	
	@Test
	@DisplayName("get-foods-test")
	public void given_AllFoods_Should_Return_List() throws Exception {
		when(vhsService.getAllVHS()).thenReturn(vhsList);
		mockMvc.perform(get("/api/v1/vhs")
				        .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].author").value("Scream2"));
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
