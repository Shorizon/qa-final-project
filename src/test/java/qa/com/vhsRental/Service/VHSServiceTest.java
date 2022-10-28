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

import com.qa.vhsRental.entity.VHS;
import com.qa.vhsRental.exception.VHSAlreadyExistsException;
import com.qa.vhsRental.repository.VHSRepository;
import com.qa.vhsRental.service.VHSServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VHSServiceTest {
	
	
	@Mock
	private VHSRepository vhsRepository;

	@Autowired
	@InjectMocks
	private VHSServiceImpl vhsService;
	
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
	@DisplayName("save-vhs-test")
	public void given_vhs_To_Save_Return_Saved_vhs() throws VHSAlreadyExistsException {
		when(vhsRepository.findById(any())).thenReturn(Optional.empty());
		when(vhsRepository.save(any())).thenReturn(test1);
		
		VHS savedVHS = vhsService.addVHS(test1);
		
		assertNotNull(savedVHS);
		assertEquals(1, savedVHS.getId());
		
	}

	
	@Test
	@DisplayName("save-vhs-test-throws-exception")
	public void given_Existing_VHS_To_Save_Should_Throw_VHSAlreadyExistsException() throws VHSAlreadyExistsException{
		
		when(vhsRepository.findById(any())).thenReturn(Optional.of(test1));
		
		assertThrows(VHSAlreadyExistsException.class, () -> vhsService.addVHS(test1));

	} 
}
