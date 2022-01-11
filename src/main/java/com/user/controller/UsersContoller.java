package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.user.model.User;
import com.user.repository.UserRepository;

@RestController
public class UsersContoller {
	
	@Autowired
	private UserRepository userRepository;
	

	
//	@GetMapping("/userss")
//	public List<User> getAllUserss(){
//		return userRepository.findAll();	
//	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findByStatus();
	}
	
	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	
//	@PutMapping("/update/{id}")
//	public User updateEmployee(@RequestBody User user, @PathVariable long id) {
//		user.setId(id);
//		if(userRepository.findById(id).isPresent())
//			userRepository.save(user);
//	}
	
	@PutMapping("/update/{idB}")
    public User updatePatientRecord(@RequestBody User patientRecord){
        
        Optional<User> optionalRecord = userRepository
        		.findById(patientRecord.getId());
       
        User existingPatientRecord = optionalRecord.get();

     
        existingPatientRecord.setId(patientRecord.getId());
        existingPatientRecord.setFirstName(patientRecord.getFirstName());
        existingPatientRecord.setLastName(patientRecord.getLastName());
        existingPatientRecord.setEmail(patientRecord.getEmail());
        existingPatientRecord.setGender(patientRecord.getGender());
        existingPatientRecord.setAge(patientRecord.getAge());
        existingPatientRecord.setAddress(patientRecord.getAddress());
        existingPatientRecord.setPincode(patientRecord.getPincode());
        existingPatientRecord.setDoj(patientRecord.getDoj());
        existingPatientRecord.setDob(patientRecord.getDob());
        existingPatientRecord.setPassword(patientRecord.getPassword());
        existingPatientRecord.setStatus(patientRecord.getStatus());
        return userRepository.save(existingPatientRecord);
    }
    
	
//	@DeleteMapping("/user/{id}")
//	public void deleteEmployee(@RequestBody User user, @PathVariable long id) {
//		user.setId(id);
//		userService.updateUser(user, id);
//	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable long id) {
		userRepository.deleteById(id);
	}
	
	@PutMapping("/softDelete/{id}")
	public void softDeleteStudent(@PathVariable long id) {
		userRepository.softDeleteUSer(id);
	}
	
	

	@GetMapping("/user/{firstName}/{lastName}/{pincode}")
	public List<User> getUserByOr(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String pincode){
		return userRepository.findByFirstNameOrLastNameOrPincode(firstName, lastName, pincode);
	}
	
	@GetMapping("/sort/doj")
	public List<User> getUserSortedByDoj(){
		return userRepository.findByOrderByDoj();
	}
		
	@GetMapping("/sort/dob")
	public List<User> getUserSortedByDob(){
		return userRepository.findByOrderByDob();
	}
		
	
	
}
