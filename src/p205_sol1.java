import java.util.HashMap;
import java.util.HashSet;
/*
Isomorphic Strings 

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/

/**
 * This is my naive AC solution
 * I used a hashmap to record the conversion relationship between chars in two strings
 * But I forgot that the problem also requires "No two characters may map to the same character but a character may map to itself"
 * So I also need a boolean array to check if one char has been used in the conversion already
 * 
 * This solution is naive without any tricky, and the speed is not so fast, maybe due to the creation of hashMap
 * But the time complexity should still be O(n)
 * 
 * Sol2 lists another neat solution uses O(n) without HashMap
 * 
 * Remark:
 * Since the problem assumes two input strings have same length, we don't need extra check
 * Otherwise, we need to compare the length of two strings in the beginning
 * 
 * Sol2 uses less space and is much faster 
 * So sol2 is recommended
 * 
 * @author hpPlayer
 * @date Sep 1, 2015 2:03:53 PM
 */

public class p205_sol1 {
    public boolean isIsomorphic(String s, String t) {
        HashSet<Character> visited = new HashSet<Character>();
        HashMap<Character, Character> hs = new HashMap<Character, Character>();
        for(int i = 0; i < s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(hs.containsKey(c1)){
                c1 = hs.get(c1);
                if(c1 != c2) return false;
            }else{
                if(visited.contains(c2)) return false;
                hs.put(c1, c2);
                visited.add(c2);
            }
            
        }
        
        return true;
    }
}
