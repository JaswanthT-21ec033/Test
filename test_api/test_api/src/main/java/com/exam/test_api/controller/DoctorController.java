package com.exam.test_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.test_api.model.Doctor;
import com.exam.test_api.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	
	
	@Autowired
	private DoctorService doctorservice;
	
	@PostMapping("/add")
	public Doctor adddoctor(@RequestBody Doctor doctor) {
		return doctorservice.addDoctor(doctor);
	}

}
