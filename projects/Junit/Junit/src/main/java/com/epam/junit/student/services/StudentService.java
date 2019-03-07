package com.epam.junit.student.services;

import com.epam.junit.student.entity.Student;
import com.epam.junit.student.exceptions.StudentNotFoundException;

public interface StudentService{

	public Student getStudentById(Long id) throws StudentNotFoundException;
	public String getStudentStatus(Long id) throws StudentNotFoundException;
}
