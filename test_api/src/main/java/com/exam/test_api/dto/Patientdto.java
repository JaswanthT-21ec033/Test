package com.exam.test_api.dto;

import com.exam.test_api.model.Medicalhistory;
import com.exam.test_api.model.Patient;
import com.exam.test_api.model.User;

public class Patientdto {
	
	private Patient patient;
	private User user;
	private Medicalhistory medicalhistory;
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Medicalhistory getMedicalhistory() {
		return medicalhistory;
	}
	public void setMedicalhistory(Medicalhistory medicalhistory) {
		this.medicalhistory = medicalhistory;
	}
	

}
