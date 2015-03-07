import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * Another problem that I worked without help, and my original solution is 99% correct.
 * My original solution should work, but I don't know the exact test cases in the leetCode, so I failed in first attempts.
 * The test cases I failed include:
 * 1) empty string should return empty array, instead of return an array contains empty string
 * 2) the test cases are all valid input, no 0, 1, those special cases, so no sort is needed(I want use sort to skip 0, 1 if it has)
 * 
 * Basic idea:
 * DFS
 * 1)If our temp string has same length as the digits.length, then we have reached end and need return 
 * 2)Due to duplicates from "ae" and "ea", we need use an index indicator(start) to skip indexes that we have visited
 * 3)Each node has several children, so my idea is for each node, visit each of its next node's child and do DFS on them...
 * 4)So, here index indicator is very important, firstly for the same level nodes, it prevent nodes visit previous nodes
 * and secondly for the parent and child nodes, it also helps prevent node visit previous nodes..
 * 
 * Remark:
 * 1) it is convenient to change string to char array first, so we can easily manipualte char in array
 * 2) the hashMap should use char as key instead of integer, int 5 and char 5 are different..
 * @author hpPlayer
 * @date Mar 6, 2015 6:04:01 PM
 */
public class p017_sol1 {
	public static void main(String[] args) {
		List<String> result = letterCombinations("23");
		System.out.println(result);
	}

	public static List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<String>();
		if(digits == null || digits.length() == 0) return result;
		
		HashMap<Character, String> hs = new HashMap<Character, String>();
		hs.put('2', "abc");
		hs.put('3', "def");
		hs.put('4', "ghi");
		hs.put('5', "jkl");
		hs.put('6', "mno");
		hs.put('7', "pqrs");
		hs.put('8', "tuv");
		hs.put('9', "wxyz");


		char[] digitsAry = digits.toCharArray();
		/*
		 Arrays.sort(digitsAry);
		
		int i = 0;
		for(; i < digitsAry.length; i++){
			if(digitsAry[i] >= '2') break;
		}
		int start = i;
		int length = digitsAry.length - i;
		 */
		DFS(result, "", digitsAry, 0, hs);
		return result;
	}

	public static void DFS(List<String> result, String temp, char[] digits,
			int start, HashMap<Character, String> hs) {
		// System.out.println(Arrays.toString(digits));
		if (temp.length() == digits.length) {
			// System.out.println(temp);
			result.add(temp);
			return;
		}

		for (int i = start; i < digits.length; i++) {
			//System.out.println(digits[i]);
			// System.out.println(i);
			String currS = hs.get(digits[i]);
			// System.out.println(currS);
			for (char c : currS.toCharArray()) {
				String a = c + "";
				DFS(result, temp + a, digits, start + 1, hs);
			}

			start++;
		}

	}
}
