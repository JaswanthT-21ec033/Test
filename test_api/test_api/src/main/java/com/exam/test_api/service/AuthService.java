package com.exam.test_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.test_api.exception.InvalidUsernameException;
import com.exam.test_api.model.User;
import com.exam.test_api.repository.AuthRepository;

@Service
public class AuthService {

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	public User signUp(User user) throws InvalidUsernameException {
 		//check if Username is unique 
 		User user1 =  authRepository.findByUsername(user.getUsername());
 		if(user1 != null) { 
 			//if user exists it will be not null. if its a new username then it will be null 
 			throw new InvalidUsernameException("Username already exists");
 		}
 		
		if (user.getRole() == null)
			user.setRole("USER_DEFAULT");
		// encode the password
		String encodedPass = bcrypt.encode(user.getPassword());

		// attach encoded pass to user
		user.setPassword(encodedPass);

		return authRepository.save(user);
	}

}
