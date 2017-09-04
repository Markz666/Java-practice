package com.zmx.practice;
import java.util.Scanner;
import java.net.*;
import java.io.*;
public class ReadFileFromURL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      System.out.print("Enter a URL: ");
      @SuppressWarnings("resource")
	String URLString = new Scanner(System.in).next();
      
      try {
    	  URL url = new URL(URLString);
    	  int count = 0;
    	  @SuppressWarnings("resource")
		Scanner input = new Scanner(url.openStream());
    	  while (input.hasNext()) {
    		  String line = input.nextLine();
    		  count += line.length();
    	  }
    	  System.out.println("The file size is " + count + " characters");
      }
      catch (MalformedURLException muex) {
    	  System.out.println("Invalid URL");
      }
      catch (IOException ioe) {
    	  System.out.println("I/O Errors: no such file");
      }
	}

}
