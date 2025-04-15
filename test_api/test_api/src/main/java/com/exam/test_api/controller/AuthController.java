package com.exam.test_api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.test_api.config.JwtUtil;
import com.exam.test_api.dto.TokenDto;
import com.exam.test_api.exception.InvalidUsernameException;
import com.exam.test_api.model.User;
import com.exam.test_api.service.AuthService;
import com.exam.test_api.service.MyUserService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthService authService;

	@Autowired
	private MyUserService myUserService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/signup")
	public User signUp(@RequestBody User user) throws InvalidUsernameException {
		return authService.signUp(user);
	}

	@PostMapping("/token/generate")
	public TokenDto generateToken(@RequestBody User user,TokenDto dto) {
		/*Step 1. Build authentication ref based on username,passord given*/
		Authentication auth = 
		new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
	
		authenticationManager.authenticate(auth);
		
		/*Step 2: Generate the token since we know that credentials are correct */
		String token =  jwtUtil.generateToken(user.getUsername()); 
		dto.setToken(token);
		dto.setUsername(user.getUsername());
		dto.setExpiry(jwtUtil.extractExpiration(token).toString());
		return dto; 
	}
	
	@GetMapping("/user/details")
	public UserDetails getUserDetails(Principal principal) {
		String username = principal.getName();
		return myUserService.loadUserByUsername(username);
	}
}