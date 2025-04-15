package com.exam.test_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.test_api.model.Doctor;
import com.exam.test_api.repository.DoctorRepository;

@Service
public class DoctorService {
	

	
	@Autowired 
	private DoctorRepository doctorrepository;
	
	
	public Doctor addDoctor(Doctor doctor) {
		return doctorrepository.save(doctor);
		
	}
	
	

}
