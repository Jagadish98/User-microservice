package com.mobileskins.userservice.reprository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobileskins.userservice.entity.Customer;


@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByUsernameOrEmail(String username, String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
}
