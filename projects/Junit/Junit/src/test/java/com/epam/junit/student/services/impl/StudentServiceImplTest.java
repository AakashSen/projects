package com.epam.junit.student.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.epam.junit.student.constants.Constants;
import com.epam.junit.student.entity.Student;
import com.epam.junit.student.exceptions.StudentNotFoundException;
import com.epam.junit.student.respository.StudentRespository;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

	@InjectMocks
	private StudentServiceImpl studentServiceImplMock; //Mock Object of StudentServiceImpl class
	@Mock
	private StudentRespository studentRepositoryMock; //Mock Object of StudentRespository class
	
	@Spy
	private StudentServiceImpl studentServiceImplSpy; // Spy Object of StudentServiceImpl class
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	/**
	 * Test Case to demonstrate "Mocking the Objects" scenario
	 * 
	 */
	@Test	
	public void getStudentById_Student_Response() throws StudentNotFoundException{
		//given
		Long id = 1L;
		Student std = new Student();
		std.setStudentId(id);
		std.setStudentName("testStudent");
		Optional<Student> studentOp = Optional.ofNullable(std); 
		//when
		when(studentRepositoryMock.findById(id)).thenReturn(studentOp);
		//then
		Student student = studentServiceImplMock.getStudentById(id);
		assertNotNull(student);
		assertEquals("testStudent", student.getStudentName());
		verify(studentRepositoryMock).findById(id);
	}
	
	/**
	 * Test Case to demonstrate "Unit test to test Exceptions" scenario
	 * @throws StudentNotFoundException
	 */
	
	@Test
	public void getStudentById_Exception_Response() throws StudentNotFoundException{
		// given
		Long id = 0L;
		Optional<Student> studentOp = Optional.ofNullable(null);
		// when
		when(studentRepositoryMock.findById(id)).thenReturn(studentOp);
		thrown.expect(StudentNotFoundException.class);
		thrown.expectMessage(Constants.STUDENT_NOT_FOUND_ERROR_MSG);
		// then
		studentServiceImplMock.getStudentById(id);
		verify(studentRepositoryMock).findById(id);
	}
	
	/**
	 * Test Case to demonstrate "Mocking a method of the same class using spy object" scenario 
	 * 
	 */
	@Test
	public void getStudentStatus_Pass_Response() throws StudentNotFoundException {
		//given
		Long id = 1L;
		Student student = new Student();
		student.setStudentId(1L);
		student.setStudentMarks(65L);
		//when
		Mockito.doReturn(student).when(studentServiceImplSpy).getStudentById(id);
		//then
		String status = studentServiceImplSpy.getStudentStatus(id);
		assertEquals(Constants.STATUS_PASS, status);
		verify(studentServiceImplSpy).getStudentById(id);
	}
	
	/**
	 * Test Case to demonstrate "Mocking a method of the same class using spy object" scenario 
	 * 
	 */
	@Test
	public void getStudentStatus_Fail_Response() throws StudentNotFoundException {
		//given
		Long id = 1L;
		Student student = new Student();
		student.setStudentId(1L);
		student.setStudentMarks(35L);
		//when
		Mockito.doReturn(student).when(studentServiceImplSpy).getStudentById(id);
		//then
		String status = studentServiceImplSpy.getStudentStatus(id);
		assertEquals(Constants.STATUS_FAIL, status);
		verify(studentServiceImplSpy).getStudentById(id);
	}
	
	/**
	 * Test Case to demonstrate "Mocking a method of the same class using spy object" scenario 
	 * 
	 */
	@Test
	public void getStudentStatus_Exception_Response() throws StudentNotFoundException {
		//given
		Long id = 0L;		
		//when
		Mockito.doThrow(StudentNotFoundException.class).when(studentServiceImplSpy).getStudentById(id);
		thrown.expect(StudentNotFoundException.class);
		//then
		studentServiceImplSpy.getStudentStatus(id);		
		
		verify(studentServiceImplSpy).getStudentById(id);
		
	}

	/**
	 * Test Case to demonstrate Use of Mockito.any() Method in case if input parameter is being formed inside the method itSelf 
	 * 
	 */
	@Test
	public void getStudentStatus() throws StudentNotFoundException {
		//given
		Student student = new Student();
		student.setStudentId(1L);
		student.setStudentMarks(35L);
		//when
		Mockito.doReturn(student).when(studentServiceImplSpy).getStudentById(Mockito.anyLong());
		//then
		String status = studentServiceImplSpy.getStudentStatus();
		assertEquals(Constants.STATUS_FAIL, status);
		verify(studentServiceImplSpy).getStudentById(Mockito.anyLong());
	}
	
	/**
	 * 
	 *  Test case to verify number of calls of a method
	 */
	
	@Test	
	public void getStudentByStudentId_Student_Response() throws StudentNotFoundException{
		//given
		Long id = 1L;
		Student std = new Student();
		std.setStudentId(id);
		std.setStudentName("testStudent");
		Optional<Student> studentOp = Optional.ofNullable(std); 
		//when
		when(studentRepositoryMock.findById(id)).thenReturn(studentOp);
		//then
		Student student = studentServiceImplMock.getStudentByStudentId(id);
		assertNotNull(student);
		assertEquals("testStudent", student.getStudentName());
		Mockito.verify(studentRepositoryMock, Mockito.times(2)).findById(id);
	}
	
}