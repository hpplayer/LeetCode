/*
Minimum Window Substring

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

*/


/**
 * I firstly try to solve this problem with hashMap, in which we record the character and the indexes of its appearance
 * It passed like 160 test cases, but I found the most vital point that I missed. We can't get the minimum window by only looking 
 * the indexes of each char, since the final result may be unordered we can't easily find the minimum only through those indexes.
 * 
 * Here is a very clever solution.
 * The basic idea is using two tables with two pointers
 * One of the table is used to record chars that me need to find in t, and the other table is used to record chars we have found in s
 * Let's say the table for t is expected[], the table for s is real[]
 * 
 * We can easily update expected[] by just checking all chars in t without any requirement
 * 
 * But when we need to update real[] we have to do several things:
 * Let's say now we are looking char c in string s:
 * 1) if c is not a char in t, which means its value is 0 in expected[], we just simply skip it
 * 2) if c is a char in t, then we record its occurrence in real[].
 * If we found real[c] > expected[c], can we do something now?
 * Probably no, because at least at this moment, we know nothing about the indexes that c last appeared.
 * 3) Our goal is to find the minimum window, so we have to define another parameter that can tell us if chars we have visited so
 * far has already composed a string contains all chars in t.
 * Let's say this parameter is called count. We will increase it every time we real[c] < expected[c], which means we still need more
 * c.
 * 4) So if at some points, count == t.length(), it means we have found a window that contains all chars in t. Maybe it is not the
 * minimum window, but at least it contains all chars in t. 
 * Then it will comes to the most beautiful part of our algorithm:
 * We will use two pointers skill to shrink this window.
 * We will keep right bound on c, but move left bound as long as left bound points to a char that not in t or it points to a char e
 * that we have real[e] > expected[e], which means we can take one char of e away and still let the updated window has enough char e
 * meets requirements.
 * When left bound stop moving, we can calculate the size of updated window by right bound - left bound + 1, (+1 is transfered index
 * to size), and compare this size with previous window size we recorded, we also record the minLeft where this minWin start
 * 5) We will repeat 1-4 until our right bound has reached the end
 * 6) Last, we check if count == t.length(), if not it means we have not found a window matching requirement yet, just return ""
 * otherwise return the substring based on minLeft and winSize
 *
 *
 * Remark:
 * 1) The input string may contain lowerLetter and upperLetter, so our tables should have 256 length which is size of ASCII
 * 2) It is better to use count == t.length() to check the result, otherwise if the integer.maxValue is the win size, then we will get error
 * or we can simply set winLeft = -1, then check if it is -1 to decide whether we have update minWin
 * 
 * In this algorithm, each char is visited at most 2 times. 1 for insertion and 1 for deletion, so the running time is O(n)
 * Very beautiful algorithm! I checked all hot solutions in the discussion and they all use this algorithm
 * 
 * sol1 uses two array and is very easy-understanding
 * sol2 uses one array but may not so straightforward
 * 
 * So sol1 and sol2 both have advantanges, both are recommended
 * @author hpPlayer
 * @date Sep 10, 2015 4:07:19 PM
 */


public class p076_sol1 {
	public static void main(String[] args){
		String s = "a";//"ADOBECODEBANC";////"cabwefgewcwaefgcf";
		String t = "aa";//"ABC";////"cae";//
		
		System.out.println(new p076_sol1().minWindow(s, t));
	}
    public String minWindow(String s, String t) {
        int[] expected = new int[256];
        int[] real = new int[256];
        
        //update expected[]
        for(int i = 0; i < t.length(); i++){
            expected[t.charAt(i)] ++;
        }
        
        int left = 0; //current left win index
        int minLeft = 0;//min left win index
        int minWin = Integer.MAX_VALUE;//min win size
        int count = 0;//how many chars we have found in t
        for(int i = 0; i < s.length(); i++){
            if (expected[s.charAt(i)] == 0) continue;//if s not contains this char
            
            //we found a matching char, count ++ will only happen if we have not found all chars in t
            if(real[s.charAt(i)] < expected[s.charAt(i)]) count ++;
            
            //real[i] ++ will happen as long as we found an occurence of char
            real[s.charAt(i)] ++;
            
            if(count == t.length()){
                //we will skip all unmatched chars and chars have extra occurence after index left 
                while(expected[s.charAt(left)] == 0 || expected[s.charAt(left)] < real[s.charAt(left)]){
                    real[s.charAt(left)] --;//skip current char
                    left ++;//move left further
                }
                
                //current win size is i - left + 1, if its size is smaller then minWin
                //of course we will update minWin size only when we have found all chars in t
                if(i - left + 1 < minWin){
                    minWin = i - left + 1;
                    minLeft = left;
                }
            }
            

                
        }
        //System.out.println(minWin);
        //we have not found a window matching all chars in t yet
        //if(minWin == Integer.MAX_VALUE) return "";
        if(count != t.length()) return "";//it is better to use count, if the integer.maxValue is the win size, then we will get error
        
        //winMin is size which is 1 larger than end index, so it is perfect to be used as exclusive right index 
        return s.substring(minLeft, minLeft + minWin);
    }
}
