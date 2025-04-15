package com.exam.test_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.test_api.model.User;

public interface UserRepository extends JpaRepository <User, Integer> {

}
