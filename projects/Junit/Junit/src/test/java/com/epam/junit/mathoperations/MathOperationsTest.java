package com.epam.junit.mathoperations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathOperationsTest {
	
	private MathOperations mathOperations;
	
	/**
	 *  Test case to demonstrate "Sample Unit Test to check the logic of addition of two numbers" scenario 	
	 */
	@Test
	public void add_number_Response() {
		mathOperations = new MathOperations();
		int sum = mathOperations.add(10,20);
		assertEquals(30,sum);
	}
}
