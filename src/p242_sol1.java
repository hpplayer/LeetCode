import java.util.Arrays;
/*
Valid Anagram

Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.
*/

/**
 * My original Solution without help
 * Simple solution, no tricky part
 * There will only lowercase alphabets exist in the input, so we don't need to consider 1-9 or other signs(!@#$%) 
 * Time complexity is O(nlogn), space complexity is O(len(s) + len(t))
 * A better solution with O(n) time and space complexity can be found in sol2
 * @author hpPlayer
 * @date Aug 6, 2015 2:25:53 PM
 */

public class p242_sol1 {
	public static void main(String[] args){
		System.out.println(new p242_sol1().isAnagram("anagram", "nagaram"));
	}
    public boolean isAnagram(String s, String t) {
        char[] array_s = s.toCharArray();
        char[] array_t = t.toCharArray();
        Arrays.sort(array_s);
        Arrays.sort(array_t);
        
        return Arrays.equals(array_s, array_t);
    }
}
