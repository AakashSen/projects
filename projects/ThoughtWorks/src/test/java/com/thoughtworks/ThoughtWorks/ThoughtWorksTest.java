package com.thoughtworks.ThoughtWorks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.thoughtworks.models.MessageInput;
import com.thoughtworks.models.MessageOutput;
import com.thoughtworks.utils.DecryptData;

@RunWith(SpringRunner.class)
public class ThoughtWorksTest {
	
	private TestRestTemplate restTemplate = new TestRestTemplate();	
	private HttpHeaders headers = new HttpHeaders();
	private static final String  baseURL = "https://http-hunt.thoughtworks-labs.net";
	
	private String createURLWithPort(String uri) {
		return  baseURL + uri;
	}
	
	@Test
	public void testDecryptData() {
	 MessageInput inputData = getInput();
	 System.out.println("inputData : "+inputData.toString());
	 String decryptedData = DecryptData.decryptData(inputData.getEncryptedMessage(), inputData.getKey());
	 MessageOutput outputData = new MessageOutput();
	 outputData.setMessage("testData");
	 System.out.println("decryptedData : "+decryptedData);
	 postOutput(outputData);
	}
	
	@Test
	public void getInstruction() {
		headers.add("userId","Kh59ACuM_");
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/challenge"),HttpMethod.GET,entity,String.class);
		System.out.println(":::::::::::::::::::::::::::::::");
		System.out.println("Instruction : "+response);
		System.out.println(":::::::::::::::::::::::::::::::");
	}
	
	public MessageInput getInput() {
		headers.add("userId","Kh59ACuM_");
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<MessageInput> response = restTemplate.exchange(createURLWithPort("/challenge/input"),HttpMethod.GET,entity,MessageInput.class);
		return response.getBody();
	}

   public void postOutput(MessageOutput ouput) {
//		headers.add("userId","Kh59ACuM_");
		HttpEntity<MessageOutput> entity = new HttpEntity<MessageOutput>(ouput,headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/challenge/output"),HttpMethod.POST,entity,String.class);
		System.out.println(":::::::::::::::::::::::::::::::");
		System.out.println("postOutput : "+response.toString());
		System.out.println(":::::::::::::::::::::::::::::::");
	}
}
