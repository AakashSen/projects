package com.epam.junit.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.junit.student.entity.Student;
import com.epam.junit.student.exceptions.StudentNotFoundException;
import com.epam.junit.student.response.ErrorResponse;
import com.epam.junit.student.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Long id) throws StudentNotFoundException{
		Student std = studentService.getStudentById(id);
		return ResponseEntity.ok().body(std);
	}
	@GetMapping("/status/{id}")
	public ResponseEntity<String> getStudentStatus(@PathVariable Long id) throws StudentNotFoundException{
		String status = studentService.getStudentStatus(id);
		return ResponseEntity.ok().body(status);
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleErrorResponse(Exception ex){
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMsg(ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
