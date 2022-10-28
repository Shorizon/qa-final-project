package com.qa.vhsRental.repository;

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
import com.qa.vhsRental.entity.VHS;
import com.qa.vhsRental.repository.VHSRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class VHSRepositoryTest {
	
	@Autowired
	private VHSRepository vhsRepository;
	
	VHS test1;
	VHS test2;
	VHS test3;
	
	List<VHS> vhsList;
	
	@BeforeEach
	public void setUp() {

		
		
		test1 = new VHS(1, "Godsacio", "Scream", true);
		test2 = new VHS(2, "Gosdsdcio2", "Scream", true);
		test3 = new VHS(3, "Gocdsdsio3", "Scream", false);
		vhsList = Arrays.asList(test1,test2,test3);
		
	}
	
	@AfterEach
	public void tearDown() {
		test1 = test2 = test3 = null;
		vhsRepository.deleteAll();
		vhsList = null;
	}
	
	@Test
	@DisplayName("save-VHS-test")
	public void given_VHS_To_Save_Return_Saved_VHS() {
		VHS savedVHS = vhsRepository.save(test1);
		assertNotNull(savedVHS);
		assertEquals("Godsacio", savedVHS.getName());
	}
	
	@Test
	@DisplayName("get-VHS-with-non-existing-id-test")
	public void given_VHS_With_Non_Existing_ID_Return_Optional_Empty() {
		vhsRepository.save(test1);
		assertThat(vhsRepository.findById(321321).isEmpty());
	}
	
	@Test
	@DisplayName("get-food-list-test")
	public void given_AllFood_Return_Food_List() {
		
		
		vhsRepository.save(test1); 
		vhsRepository.save(test2); 
		vhsRepository.save(test3); 
		
		List<VHS> VHSList = vhsRepository.findAll();
		System.out.println(VHSList);
		assertEquals(3, VHSList.size());
		assertEquals("Scream", VHSList.get(2).getAuthor());
		assertEquals(false, VHSList.get(0).getIsRented());
	}	

}
