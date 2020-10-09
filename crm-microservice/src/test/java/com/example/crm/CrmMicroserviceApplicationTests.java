package com.example.crm;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.crm.dto.CustomerRequest;
import com.example.crm.dto.CustomerResponse;
import com.example.crm.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = CrmMicroserviceApplication.class)
@AutoConfigureMockMvc
class CrmMicroserviceApplicationTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomerService customerService;
    @Autowired
    private ObjectMapper mapper;
    
	@Test
	void addOneCustomer_then_success() throws Throwable {
		// 1. Setup/fixture
		CustomerRequest request = new CustomerRequest();
		request.setIdentity("11111111110");
		request.setFullname("jack bauer");
		request.setHomeAddress("ankara, turkey");
		request.setBusinessAddress("ankara, turkey");
		request.setEmail("jack@example.com");
		request.setSms("5555555");
		request.setBirthYear(1956);
		request.setPhoto(null);
		CustomerResponse response= new CustomerResponse();
		response.setIdentity("11111111110");
		response.setFullname("jack bauer");
		response.setHomeAddress("ankara, turkey");
		response.setBusinessAddress("ankara, turkey");
		response.setEmail("jack@example.com");
		response.setSms("5555555");
		response.setBirthYear(1956);		
		Mockito.when(customerService.add(request)).thenReturn(response);
		// 2. Call exercise method + 3. Verification
		mvc.perform(
				MockMvcRequestBuilders.post("/customers")
				                      .content(mapper.writeValueAsString(request))
				                      .contentType(MediaType.APPLICATION_JSON)
				                      .accept(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("identity", is("11111111110")));    
		// 4. Destroy test environment
	}

}
