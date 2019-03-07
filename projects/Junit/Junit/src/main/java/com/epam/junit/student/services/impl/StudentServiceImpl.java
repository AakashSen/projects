package com.epam.junit.student.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.junit.student.constants.Constants;
import com.epam.junit.student.entity.Student;
import com.epam.junit.student.exceptions.StudentNotFoundException;
import com.epam.junit.student.respository.StudentRespository;
import com.epam.junit.student.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
 
	@Autowired	
	private StudentRespository studentRepository;
	
	public Student getStudentById(Long id) throws StudentNotFoundException {
		Optional<Student> studentOp = studentRepository.findById(id);
		if(studentOp.isPresent()) {
			return studentOp.get();
		}else {
			throw new StudentNotFoundException(Constants.STUDENT_NOT_FOUND_ERROR_MSG);
		}
	}
	
	public String getStudentStatus(Long id) throws StudentNotFoundException {		
		Student student = getStudentById(id);		
		return getStatus(student.getStudentMarks());			
	}
	
	private String getStatus(Long marks) {
		if(marks >= 60) {
			return Constants.STATUS_PASS;
		}else {
			return Constants.STATUS_FAIL;
		}
	}
	
	public String getStudentStatus() throws StudentNotFoundException {
		Long id = 1L;
		Student student = getStudentById(id);		
		return getStatus(student.getStudentMarks());			
	}
	
	public Student getStudentByStudentId(Long id) throws StudentNotFoundException {
		Optional<Student> studentOp = studentRepository.findById(id);
		if(studentOp.isPresent()) {
			studentOp = studentRepository.findById(id);
			return studentOp.get();
		}else {
			throw new StudentNotFoundException(Constants.STUDENT_NOT_FOUND_ERROR_MSG);
		}
	}
}
