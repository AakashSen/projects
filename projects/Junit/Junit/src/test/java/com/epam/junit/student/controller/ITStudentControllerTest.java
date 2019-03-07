package com.epam.junit.student.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.epam.junit.JunitApplication;
import com.epam.junit.student.constants.Constants;
import com.epam.junit.student.entity.Student;
import com.epam.junit.student.response.ErrorResponse;
import com.epam.junit.student.respository.StudentRespository;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = JunitApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ITStudentControllerTest {

	@LocalServerPort
	private int port;
	private TestRestTemplate restTemplate = new TestRestTemplate();	
	private HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	private StudentRespository studentRepository;
	
	private String createURLWithPort(String uri) {
		return  Constants.INTEGRATION_TEST_URI + port + uri;
	}
	@Test
	public void stage1_getStudent_Error_Response() {
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);		
		ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort("/students/1"),HttpMethod.GET,entity,ErrorResponse.class);
		assertNotNull(response);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());		
		assertNotNull(response.getBody());		
		assertEquals(Constants.STUDENT_NOT_FOUND_ERROR_MSG, response.getBody().getErrorMsg());		
		
	}
	
	@Test
	public void stage2_getStudent_Student_Response() {
		
		Student student = new Student();
		student.setStudentId(1L);
		student.setStudentName("testStudent1");
		student.setStudentMarks(65L);		
		studentRepository.save(student);
		
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);		
		ResponseEntity<Student> response = restTemplate.exchange(createURLWithPort("/students/1"),HttpMethod.GET,entity,Student.class);
		assertNotNull(response);
		assertEquals(HttpStatus.OK,response.getStatusCode());		
		assertNotNull(response.getBody());		
		assertEquals("testStudent1", response.getBody().getStudentName());		
		
	}
	
	@Test
	public void stage3_getStudentStatus_Pass_Response() {
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/students/status/1"),HttpMethod.GET,entity,String.class);
		assertNotNull(response);
		assertEquals(Constants.STATUS_PASS,response.getBody());
	}
	
}
