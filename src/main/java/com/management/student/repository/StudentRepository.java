package com.management.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.student.model.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Long> {

}
