package com.exam.test_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.test_api.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
