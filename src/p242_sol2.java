/**
 * Several awesome points:
 * 1) firstly check if two strings are same length, return false if not
 * 2) use an alphabetic array to count the appearance of each alphabet in the string.
 * When we search the string, one string will increase the appearance of alphabet, the other one will decrease the appearance 
 * of alphabet
 * 3) finally we scan the array and check if all values are 0,return false if any value is not zero
 * 
 * @author hpPlayer
 * @date Aug 6, 2015 2:41:11 PM
 */
public class p242_sol2 {
    public boolean isAnagram(String s, String t) {
    	if (s.length() != t.length()) return false;
    	int[] counter = new int[s.length()];
    	for(int i = 0; i < s.length(); i++){
    		counter[s.charAt(i) - 'a'] ++;
    		counter[t.charAt(i) - 'a'] --;
    	}
    	
    	for(int i : counter){
    		if (i != 0) return false;
    	}
    	return true;
    }
}
