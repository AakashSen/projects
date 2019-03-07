package com.thoughtworks.utils;

public class DecryptData {

	private DecryptData() {}
	
	public static String decryptData(String str,int n) {
        StringBuilder calCharReverseStr = new StringBuilder();
        
        String[] strArr = str.split(" ");
        for(String s : strArr) {
       	 calCharReverseStr.append(rotateLeft(s,n));
       	 calCharReverseStr.append(" ");
        }
        return calCharReverseStr.toString();
	}
	
	private static String rotateLeft(String text, int count) {
	    char[] buf = text.toCharArray();
	    for (int i = 0; i < buf.length; i++) {
	    	
	    	if (!String.valueOf(buf[i]).matches("[^a-zA-Z0-9\\\\s+]")) {
	    		 buf[i] = (char)((buf[i] - 'A' + 26 - count) % 26 + 'A');
	    	}
	    
	    }
	    return new String(buf);
	}
}
