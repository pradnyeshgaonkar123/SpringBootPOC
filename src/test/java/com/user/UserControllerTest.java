package com.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


import static org.hamcrest.Matchers.*;


import com.user.controller.UsersContoller;
import com.user.model.User;
import com.user.repository.UserRepository;


////@SpringBootTest(properties = "spring.main.lazy-initialization=true",
//classes = {UsersContoller.class})

//@RunWith();
@WebMvcTest(UsersContoller.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
//@SpringBootTest
//@AutoConfigureMockMvc

class UserControllerTest {

	
	@Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;  
    //ObjectMapper provides functionality for reading and writing JSON,
    // either to and from basic POJOs
    
   
    @MockBean
    UserRepository userRepository;
    
    
    
    User RECORD_1 = new User(1l, "Ram", "Gaonkar", "ram@gmail.com", "Male", 21, "Kankavli", "416602", new Date(2000-12-12),new Date(2022-12-12), "Pradnyesh123",0);
    User RECORD_2 = new User(2l, "Rakesh", "Joshi", "fdsafa@gmail.com", "Male", 32, "Pune", "416432", new Date(1990-12-12),new Date(2021-12-12), "Ramesh123",0);
    User RECORD_3 = new User(3l, "Odsafm", "Gaonkar", "gasdgfas@gmail.com", "Male", 18, "Mumbai", "434343", new Date(2008-12-12),new Date(2022-12-12), "Om123",0);

    @Test
   	void contextLoads() {
   	}
	
	@Test
    public void getAllRecords_success() throws Exception {
        List<User> records = new ArrayList<>
        		(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        
        Mockito.when(userRepository.findByStatus()).thenReturn(records);
        //When findAll called then ready with records  (No DB calls) 
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].firstName", is("Odsafm")));
    }
	
	@Test
    public void updatePatientRecord_success() throws Exception {
        User updatedRecord = User.builder()
        		 .id(3l)
                 .firstName("Vedesh")
                 .lastName("Salgaonkar")
                 .email("sdaf@gmail.com")
                 .gender("Male")
                 .age(18)
                 .address("Malvan")
                 .pincode("416602")
                 .doj(new Date(2004-12-12))
                 .dob(new Date(2022-12-12))
                 .password("Vedesh1123")
                 .status(0)
                 .build();
        Mockito.when(userRepository.findById(RECORD_3.getId()))
        .thenReturn(Optional.of(RECORD_3));
        
        Mockito.when(userRepository.save(updatedRecord)).thenReturn(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/update/3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", is("Vedesh")));
    }
//	@Test
//	public void createUser_success() throws Exception{
//		User newUser = User.builder()
//				.id(10l)
//                .firstName("Madhav")
//                .lastName("Joshi")
//                .email("mathdav@gmail.com")
//                .gender("Male")
//                .age(18)
//                .address("Malvan")
//                .pincode("416602")
//                .doj(new Date(2004-12-12))
//                .dob(new Date(2022-12-12))
//                .password("Madhav1123")
//                .status(0)
//				.build();
//		
//		Mockito.when(userRepository.save(newUser)).thenReturn(newUser);
//		
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/add")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(this.mapper.writeValueAsString(newUser));
//		
//		mockMvc.perform(mockRequest)
//		.andDo(print())
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$", notNullValue()))
//			.andExpect(jsonPath("$.firstName", is("Madhav")));
//	}
	@Test
	public void createUser_success() throws Exception{
		User newUser = User.builder()
				.id(10l)
                .firstName("Madhav")
                .lastName("Joshi")
                .email("mathdav@gmail.com")
                .gender("Male")
                .age(18)
                .address("Malvan")
                .pincode("416602")
                .doj(new Date(2004-12-12))
                .dob(new Date(2022-12-12))
                .password("Madhav1123")
                .status(0)
				.build();
		
		Mockito.when(userRepository.save(newUser)).thenReturn(newUser);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/add")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(newUser));
		
		mockMvc.perform(mockRequest)
		.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.firstName", is("Madhav")));
	}
	
	@Test
    public void deletePatientById_success() throws Exception {
        
    	Mockito.when(userRepository.findById(RECORD_2.getId()))
        .thenReturn(Optional.of(RECORD_2));
    	
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/delete/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());              
    }
	
	
	
	@Test
    public void fetUserByOr() throws Exception {
        List<User> records = new ArrayList<>
        		(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        
        Mockito.when(userRepository.findByFirstNameOrLastNameOrPincode(RECORD_1.getFirstName(),RECORD_1.getFirstName(),RECORD_1.getPincode())).thenReturn(records);
        //When findAll called then ready with records  (No DB calls) 
        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/Ram/Gaonkar/416602")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); //200
             
    }
	
	@Test
    public void getUserSortedByDoj() throws Exception {
        List<User> records = new ArrayList<>
        		(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        
        Mockito.when(userRepository.findByOrderByDoj()).thenReturn(records);
        //When findAll called then ready with records  (No DB calls) 
        mockMvc.perform(MockMvcRequestBuilders
                .get("/sort/doj")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //200
        		.andExpect(jsonPath("$", hasSize(3)))
        		.andExpect(jsonPath("$[0].firstName", is("Ram")));
             
    }
	
	@Test
    public void getUserSortedByDob() throws Exception {
        List<User> records = new ArrayList<>
        		(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        
        Mockito.when(userRepository.findByOrderByDob()).thenReturn(records);
        //When findAll called then ready with records  (No DB calls) 
        mockMvc.perform(MockMvcRequestBuilders
                .get("/sort/dob")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$", hasSize(3)))
        		.andExpect(jsonPath("$[1].firstName", is("Rakesh")));
             
    }
	
	@Test
    public void softDeleteUser() throws Exception {
        User updatedRecord = User.builder()
        		 .id(3l)
                 .status(1)
                 .build();
        Mockito.when(userRepository.softDeleteUSer(RECORD_3.getId()))
        .thenReturn(1);
        
        Mockito.when(userRepository.save(updatedRecord)).thenReturn(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/softDelete/3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
        		.andExpect(status().isOk());
        		
    }
	
	
	@Test
	public void applicationContextLoaded() {
	}

	
	
	

}
