package com.zmx.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BreakdownArticle {
public static String[] breakdownArticle(String strLine) {
	String delimiter = "[.,;:!?(){]";
	Pattern p = Pattern.compile(delimiter);
	
	String[] strSentences = p.split(strLine);
	for (int i = 0; i < strSentences.length; i++) {
		strSentences[i] = strSentences[i].trim();
	}
	System.out.println("---------------------------------------------");
	System.out.println("Result of breakdown paragraph into sentences:");
	int m = 1;
	for (String str:strSentences) {
		System.out.println("s" + m++ +": " + str);
	}
	return strSentences;
}

public static String[] breakdownSentence(String strSentence) {
	String regEx = "\\s";
	Pattern p = Pattern.compile(regEx);
	
	String[] strWords = p.split(strSentence);
	for (int i = 0; i < strWords.length; i++) {
		strWords[i] = strWords[i].trim();
	}
	strWords = filterIllegalChar(strWords);
	System.out.println("------------------------------");
	System.out.println("Result of breakdown sentence: ");
	for (String strItems:strWords) {
		System.out.println(strItems);
	}
	return strWords;
}

public static String[] filterIllegalChar(String[] strArr) {
	if (strArr == null || strArr.length == 0) return null;
	for (int i = 0; i < strArr.length; i++) {
		String strReg = "[^a-zA-Z0-9]";
		Pattern p = Pattern.compile(strReg);
		Matcher m = p.matcher(strArr[i]);
		strArr[i] = m.replaceAll("").trim();
	}
	return strArr;
}
public static void main(String[] args) {
	String a = "These measures of clustering performance can be quite informative. They summarize the overall quality of the clustering process and can pinpoint clusters that "
			+ "differ in performance from others. Taken with the other forms of summarization, such as key words and extracts, the clustering results can help people make informed "
			+ "decisions and interpretations. Although a fully automated process can be specified " + 
"for document clustering, it is wise to use knowledge to enhance the final results. We all read and comprehend documents, so we can examine the clustering results and " +
"evaluate the results on our own.";
//	BreakdownArticle.breakdownArticle(a);
	BreakdownArticle.breakdownSentence(a);
}
}