package com.epam.junit.student.exceptions;

public class StudentNotFoundException extends Exception{
	
	private static final long serialVersionUID = -4810066070964082600L;
	private final String errorMessage;	
	
	public StudentNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
}
