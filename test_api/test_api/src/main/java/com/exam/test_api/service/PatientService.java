package com.exam.test_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.test_api.model.Doctor;
import com.exam.test_api.model.Medicalhistory;
import com.exam.test_api.model.Patient;
import com.exam.test_api.model.Patientdoctor;
import com.exam.test_api.model.User;
import com.exam.test_api.repository.DoctorRepository;
import com.exam.test_api.repository.MedicalhistoryRepository;
import com.exam.test_api.repository.PatientRepository;
import com.exam.test_api.repository.PatientdoctorRepository;
import com.exam.test_api.repository.UserRepository;

@Service
public class PatientService {
	
	@Autowired 
	private PatientRepository patientrepository;
	
	@Autowired 
	private MedicalhistoryRepository medicalhistoryrepository;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired 
	private PatientdoctorRepository patientdoctorrepository;
	
	@Autowired 
	private DoctorRepository doctorrepository;
	
	
	public Patient addpatient(Patient patient, Medicalhistory medicalhistory, User user) {
		User saveduser = userrepository.save(user);
		Medicalhistory savedmedical = medicalhistoryrepository.save(medicalhistory);
		
		patient.setUser(saveduser);
		patient.setMedicalhistory(savedmedical);
		
		return patientrepository.save(patient);
		
		
	}
	
	 public String bookAppointment(int patientId, int doctorId) {
	        try {
	            // Fetch patient and doctor from the database
	            Patient patient = patientrepository.findById(patientId)
	                .orElseThrow(() -> new RuntimeException("Invalid patient ID: " + patientId));

	            Doctor doctor = doctorrepository.findById(doctorId)
	                .orElseThrow(() -> new RuntimeException("Invalid doctor ID: " + doctorId));

	            // Create a new patient-doctor association for the appointment
	            Patientdoctor patientDoctor = new Patientdoctor(patient, doctor);
	            patientdoctorrepository.save(patientDoctor);

	            return "Appointment booked successfully.";

	        } catch (RuntimeException e) {
	            return e.getMessage();  // Return the error message
	        }
	    }
	 
	 public List<Patient> getPatientsByDoctorId(int doctorId) {
	        List<Patientdoctor> patientDoctorList = patientdoctorrepository.findByDoctorId(doctorId);
	        List<Patient> patients = new ArrayList<>();

	        for (Patientdoctor pd : patientDoctorList) {
	            patients.add(pd.getPatient());
	        }

	        return patients;
	    }
	}
	

	


