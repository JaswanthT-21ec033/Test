package com.exam.test_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.test_api.model.User;




public interface AuthRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	
}
