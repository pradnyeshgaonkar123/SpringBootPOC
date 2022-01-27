package com.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

//	List<User> findByFirstNameOrLastNameOrPincode(String firstName, String lastName, String pincode);
	
	List<User> findByFirstName(String firstName);
	
	List<User> findByLastName(String lastName);

	List<User> findByPincode(String pincode);
	
	
	
	
//	List<User> findByStatus(int status);
	
	@Query("SELECT t FROM User t where t.status=0")
	List<User> findByStatus();
	
	@Query("SELECT t FROM User t ORDER BY t.doj asc")
	List<User> findByOrderByDoj();
	
	
	@Query("SELECT t FROM User t ORDER BY t.dob asc")
	List<User> findByOrderByDob();
	
	@Transactional
	@Modifying
	@Query("update User t set t.status = 1 where t.id=:id")
	int softDeleteUSer(@Param("id") long id);
	
//	@Transactional
//	@Modifying
//	@Query("UPDATE Trainer t SET t.active=:active_status WHERE t.firstname=:name")
//	int updateTrainerSetStatusForName(@Param("active_status")int active, @Param("name")String firstname);//@Param for remove conflicts
	
}
