package com.eminpolat.restapi.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eminpolat.restapi.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
}
