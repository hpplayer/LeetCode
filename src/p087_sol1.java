import java.util.Arrays;
import java.util.HashMap;
/**
 * Recursion version
 * The basic idea is that is there must exists a pivotal in the string that we used to swap substrings 
 * So we will try each possible pivotal in the string s1 to see if s2 has a match.
 * Ex:
 * s1: part A |part B
 * s2: part A' |part B'
 * we recursively do DFS on part A, A'and part B, B' if there are in normal order without swap,then this call will return true
 * if it returns false, then one of s1 and s2 may already got swapped 
 * so we can try do DFS on part A, B' and part B, A', if one of them already got swapped, then this call will return true
 * otherwise s1 and s2 are not scrambled.
 * This idea is not too hard to come up with, but the difficulty is how to improve it ?
 * 
 * The biggest improvement should be sorting their chars first to see if we got same candidate characters, if not just return false, no need do DFS
 * Another idea is using HashMap as a cache, we use substring from A + substring from B as the key, and their scrambled status as the value
 * 
 * Remark:
 * When comparing two char array, 
 * directly call equals() on char array is very slow (the running time is 600ms+)
 * Instead, using new String().equals will be much faster (around 260 ms)
 * Or using Arrays.equals(s1, s2) is also faster (around 260 ms)
 * 
 * @author hpPlayer
 * @date Mar 28, 2015 12:00:27 AM
 */

public class p087_sol1 {
	public static void main(String[] args){
		//System.out.println(isScramble("abcdefghijklmn", "efghijklmncadb"));
		System.out.println(isScramble("ab", "ba"));
	}
    public static boolean isScramble(String s1, String s2) {
        return DFS(s1, s2);
    }
    
    public static boolean DFS(String s1, String s2){
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if(!Arrays.equals(c1, c2)) return false;
       // if(!new String(c1).equals(new String(c2))) return false;
        //if(c1.equals(c2)) return false;
        if(s1.equals(s2)) return true;
        for(int i = 1; i < s1.length(); i++){
            String s11 = s1.substring(0, i), s12 = s1.substring(i);
            String s21 = s2.substring(0, i), s22 = s2.substring(i);
            if(DFS(s11, s21) && DFS(s12, s22)){
            	return true;
            }
            
            s21 = s2.substring(0, s2.length() - i);
            s22 = s2.substring(s2.length()-i,s2.length());
            if(DFS(s11, s22) && DFS(s12, s21)){
            	return true;
            }
        }
       // System.out.println("im here");
        return false;
    }
    
    public static boolean DFS(String s1, String s2, HashMap<String, Boolean> hs){
    	if(hs.containsKey(s1+s2)) return hs.get(s1+s2);
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if(!Arrays.equals(c1, c2)) return false;
        //if(c1.equals(c2)) return false;
        if(s1.equals(s2)) return true;
        for(int i = 1; i < s1.length(); i++){
            String s11 = s1.substring(0, i), s12 = s1.substring(i);
            String s21 = s2.substring(0, i), s22 = s2.substring(i);
            if(DFS(s11, s21, hs) && DFS(s12, s22, hs)){
            	hs.put(s1+s2, true);
            	return true;
            }
            
            s21 = s2.substring(0, s2.length() - i);
            s22 = s2.substring(s2.length()-i,s2.length());
            if(DFS(s11, s22, hs) && DFS(s12, s21, hs)){
            	hs.put(s1+s2, true);
            	return true;
            }
        }
       // System.out.println("im here");
        hs.put(s1+s2, false);
        return false;
    }
}
