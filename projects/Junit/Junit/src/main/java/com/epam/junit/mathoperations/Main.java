package com.epam.junit.mathoperations;

/* Save this in a file called Main.java to compile and test it */

/* 
   Example file showing how to write a program that reads
   input from `input.txt` in the current directory
   and writes output to `output.txt` in the current directory
*/

/* Do not add a package declaration */
import java.util.*;
import java.io.*;

/* DO NOT CHANGE ANYTHING ABOVE THIS LINE */
/* You may add any imports here, if you wish, but only from the 
   standard library */

/* Do not add a namespace declaration */

public class Main {
    public static void main (String[] args) {
        Set<String> ouputData = new HashSet<>();
        Map<String,Integer> apiVersionMap = new HashMap<>();
        Map<String,String> apiLatestVersionAndAppNameMap = new HashMap<>();
        try {
            Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));

            /* Here we can read in the input file */
            /* In this example, we're reading all the lines of file
               `input.txt` and then ignoring them. 
               You should modify this part of the
               program to read and process the input as desired */
            while(in.hasNextLine()) {
                String line = in.nextLine();
                if (!line.isEmpty()) {
                	String[] lineArr = line.split(",");
                    int version = Integer.parseInt(lineArr[2].substring(2));
                    String apiName = lineArr[1];
                    if (apiVersionMap.containsKey(apiName)) {
                    	
                    	int apiVersion = apiVersionMap.get(apiName);
                    	
                    	if(apiVersion < version) {
                    		apiVersionMap.put(apiName, version);
                    		ouputData.add(apiLatestVersionAndAppNameMap.get(apiName));
                    		apiLatestVersionAndAppNameMap.put(apiName,lineArr[0]);
                    	}
                    	
                    } else {
                    	apiVersionMap.put(apiName, version);
                    	apiLatestVersionAndAppNameMap.put(apiName,lineArr[0]);
                    }
                }
            }

            /* In this example, we're writing `num_lines` to
               the file `output.txt`.
               You should modify this part of the
               program to write the desired output */
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
            output.println("" + ouputData);
            output.close();
        } catch (IOException e) {
            System.out.println("IO error in input.txt or output.txt");
        }
    }
}

