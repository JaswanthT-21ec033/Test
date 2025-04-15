package com.exam.test_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.test_api.model.Medicalhistory;

public interface MedicalhistoryRepository extends JpaRepository<Medicalhistory, Integer> {

}
