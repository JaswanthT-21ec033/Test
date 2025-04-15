package com.exam.test_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.test_api.model.Patientdoctor;

public interface PatientdoctorRepository extends JpaRepository<Patientdoctor, Integer>{

	List<Patientdoctor> findByDoctorId(int doctorId);

}
