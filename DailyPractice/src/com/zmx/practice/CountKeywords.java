package com.zmx.practice;

import java.io.*;
import java.util.*;


public class CountKeywords {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a Java source file: ");
		String filename = input.nextLine();
		
		File file = new File(filename);
		if (file.exists()) {
			System.out.println("The number of keywords in " + filename
					+ " is " + countKeywords(file));			
		}
		else {
			System.out.println("File " + filename + " does not exist");
		}
	}
	public static int countKeywords(File file) throws Exception {
		String[] keywordString = {"abstract", "assert", "this", "null"};
		
		Set<String> keywordSet = 
				new HashSet<>(Arrays.asList(keywordString));
		int count = 0;
		
		Scanner input = new Scanner(file);
		
		while (input.hasNext()) {
			String word = input.next();
			if (keywordSet.contains(word))
				count ++;
		}
		return count;
	}

}
