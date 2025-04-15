package com.exam.test_api.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.test_api.dto.Appointmentdto;
import com.exam.test_api.dto.Patientdto;
import com.exam.test_api.model.Patient;
import com.exam.test_api.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	
	@Autowired 
	private PatientService patientservice;
	
	@PostMapping("/add")
	public Patient addpatient(@RequestBody Patientdto patientdto) {
		return patientservice.addpatient(patientdto.getPatient(),
		        patientdto.getMedicalhistory(),
		        patientdto.getUser());
	}
	
	@PostMapping("/book")
	public ResponseEntity<String> bookAppointment(@RequestBody Appointmentdto appointmentdto) {
	    try {
	        String result = patientservice.bookAppointment(appointmentdto.getPatientId(), appointmentdto.getDoctorId());
	        return ResponseEntity.ok(result);
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	  @GetMapping("/bydoctor/{doctorId}")
	    public List<Patient> getPatientsByDoctorId(@PathVariable int doctorId) {
	        return patientservice.getPatientsByDoctorId(doctorId);
	    }
}
	
	
	
	

