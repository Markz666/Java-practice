package com.zmx.javafxpractice;
import java.util.*;
public class ShiftCipher {
	public static String shiftCipher(String str, int offset) {
		StringBuilder result = new StringBuilder();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char c = (char) (str.charAt(i) + offset);
			if (c > 'z') {
				result.append((char)(str.charAt(i) - 26 + offset));
			} else if (c < 'a') {
				result.append((char)(str.charAt(i) + 26 + offset));
			} else {
				result.append((char)(str.charAt(i) + offset));
			}
		}
		return result.toString();
	}
	public static List<Integer> computeOffset(String s1, String s2) {
		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < arr1.length; i++) {
			int offset = arr2[i] - arr1[i];
			list.add(offset);
		}
		return list;
	}
	public static String decipherStr(String s, List<Integer> offsets) {
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			arr[i] += offsets.get(i);
		}
		return String.valueOf(arr);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(shiftCipher("abc", -3));
		System.out.println(computeOffset("faster", "hcuvgt"));
		List<Integer> offsets = computeOffset("faster", "hcuvgt");
		System.out.println(decipherStr("slower", offsets));
		List<Integer> offsets2 = computeOffset("john", "lsnv");
		System.out.println(decipherStr("mark", offsets2));
	}
}
