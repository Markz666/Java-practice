package com.zmx.practice;
import java.util.*;
public class CountOccurrenceOfWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "These measures of clustering performance can be quite informative. They summarize the overall quality of the clustering process and can pinpoint clusters that "
				+ "differ in performance from others. Taken with the other forms of summarization, such as key words and extracts, the clustering results can help people make informed "
				+ "decisions and interpretations. Although a fully automated process can be specified " + 
	"for document clustering, it is wise to use knowledge to enhance the final results. We all read and comprehend documents, so we can examine the clustering results and " +
	"evaluate the results on our own.";
		
		Map<String, Integer> map = new TreeMap<>();
		String[] words = text.split("[ \n\t\r.,;:!?(){]");
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();
			
			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				}
				else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		
		for (Map.Entry<String, Integer> entry: entrySet)
			System.out.println(entry.getValue() + "\t" + entry.getKey());

	}
}
