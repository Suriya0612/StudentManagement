package com.management.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.student.model.StudentModel;
import com.management.student.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentRepository studentRepository;

	@GetMapping("/getdata")
	public List<StudentModel> getStudent(){
		return studentRepository.findAll();
	} 
	
	@PostMapping("/adddata")
	public StudentModel createStudent(@RequestBody StudentModel student) {
		return studentRepository.save(student);
	}
	
	@PutMapping("/updatedata/{id}")
	public ResponseEntity<StudentModel> updateStudent(@PathVariable Long id, @RequestBody StudentModel studentdetails){
		StudentModel student= studentRepository.findById(id)
				.orElse(null);
		student.setFirstName(studentdetails.getFirstName());
		student.setLastName(studentdetails.getLastName());
		student.setEmailId(studentdetails.getEmailId());
		StudentModel updatedstudentdata=studentRepository.save(student);
		return ResponseEntity.ok(updatedstudentdata);
	}
	
	@DeleteMapping("/deldata/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteStudent(@PathVariable Long id){
		StudentModel student= studentRepository.findById(id).orElse(null);
		studentRepository.delete(student);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted successfully", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
