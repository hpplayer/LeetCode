/*
Integer to Roman 

Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
 * 
 */

/**
 * This is my original solution.
 * But I do get some help from Internet in learning how to convert an integer to string
 * The basic idea is:
 * 1) get the largest key in the hashset that is smaller than input (ex: for input 11, key 10 is the largest key)
 * 2) convert this key to string and append to stringBuilder
 * 3) Subtract this key from original input (ex: for input 11, we get 11 - 10)
 * 4) repeat 1)-3) until input become 0 (ex: we got 10 and string became "X" and the new input is 1, then we got 1 and string became "XI"
 * 
 * 
 * Remark:
 * This approach needs us know the table first
 * This table should contain all special Roman numerals like 1, 4, 5, 9, 10; 10, 40; 50, 90 100; 100, 400, 500, 900, 1000)
 * 
 * sol2 uses the similar idea, but it avoids the use of hashMap
 */

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class p012_sol1 {
	public static void main(String[] args){
		System.out.println(new p012_sol1().intToRoman(11));
	}
	public String intToRoman(int num) {
		//long start = System.nanoTime();
		HashMap<Integer, String> hs = new HashMap<Integer, String>();
		hs.put(1, "I");
		hs.put(4, "IV");
		hs.put(5, "V");
		hs.put(9, "IX");
		hs.put(10, "X");
		hs.put(40, "XL");
		hs.put(50, "L");
		hs.put(90, "XC");
		hs.put(100, "C");
		hs.put(400, "CD");
		hs.put(500, "D");
		hs.put(900, "CM");
		hs.put(1000, "M");

		StringBuilder sb = new StringBuilder();
		//System.out.println(hs.keySet());
		//Set<Integer> keys = new TreeSet<Integer>(hs.keySet());
		int[] keys2 = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
		//long elapsedTime = System.nanoTime() - start;
		//System.out.println(elapsedTime);
		//start = System.nanoTime();
		int offset = 0;
		while (num > 0) {
			int min = Integer.MAX_VALUE;
			offset = 0;
			for (int i : keys2) {
				if ((num - i) < min && i <= num) {
					min = num -i;
					offset = i;
				} else {
					break;
				}
			}
			//System.out.println(num - offset);
			sb.append(hs.get(offset));
			num = num - offset;
		}
		//System.out.println(System.nanoTime() - start);
		return sb.toString();
	}
}
