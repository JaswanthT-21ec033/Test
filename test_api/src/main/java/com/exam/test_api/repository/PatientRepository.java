package com.exam.test_api.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.exam.test_api.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

	  

}
