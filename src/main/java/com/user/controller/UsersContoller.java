package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.user.model.User;
import com.user.repository.UserRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsersContoller {
	
	@Autowired
	private UserRepository userRepository;
	

	
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
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getEmployeeById(@PathVariable Long id) {
		User employee = userRepository.findById(id).get();
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/update/{id}")
    public User updatePatientRecord(@RequestBody User patientRecord, @PathVariable long id){
        
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
	
	@GetMapping("/fname/{firstName}")
	public List<User> getUsersByFirstname(@PathVariable("firstName") String firstName){
		return userRepository.findByFirstName(firstName);
	}
	@GetMapping("/lname/{lname}")
	public List<User> getUsersBySurname(@PathVariable("lname") String lname){
		return userRepository.findByLastName(lname);
	}
	
	@GetMapping("/pincode/{pin}")
	public List<User> getUsersByPincode(@PathVariable String pin){
		return userRepository.findByPincode(pin);
	}
	

//	@GetMapping("/user/{firstName}/{lastName}/{pincode}")
//	public List<User> getUserByOr(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String pincode){
//		return userRepository.findByFirstNameOrLastNameOrPincode(firstName, lastName, pincode);
//	}
	
	@GetMapping("/sort/doj")
	public List<User> getUserSortedByDoj(){
		return userRepository.findByOrderByDoj();
	}
		
	@GetMapping("/sort/dob")
	public List<User> getUserSortedByDob(){
		return userRepository.findByOrderByDob();
	}
		
	
	
}
