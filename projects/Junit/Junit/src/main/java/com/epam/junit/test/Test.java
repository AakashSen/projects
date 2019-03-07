package com.epam.junit.test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String str = "{\"1\":{\"name\":\"product1\",\"id\":\"1\"},\"2\":{\"name\":\"product2\",\"id\":\"2\"}}";
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
		Test obj = new Test();
		File file = obj.getFileData();
		
		Map<String, Product> products = objectMapper.readValue(file, new TypeReference<Map<String, Product>>(){});
		System.out.println(products);
		
		
		for (Entry<String, Product> e : products.entrySet()) {
			
			System.out.println(e);
		}
		
		Product p = products.get("1");
		
		System.out.println(p);
	}
	
	private File getFileData() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("static/product.json").getFile());
		return file;
	}
}
