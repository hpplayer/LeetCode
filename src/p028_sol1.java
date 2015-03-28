import java.util.HashMap;
/**
 * This algorithm works in following way:
 * we have two pointers, one check char in haystack, one check char in needle,
 * if found match, both ++, if not match, reset pointer of needle to 0, and push pointer of haystack i - k backward
 * if the pointer has reaches the end of needle.length, then we have found a whole match, just break the loop and return i - k index
 * otherwise return -1
 * 
 * Official solition please see sol2
 * @author hpPlayer
 * @date Mar 27, 2015 7:45:16 PM
 */

public class p028_sol1 {
	public static void main(String[] args){
		System.out.println(strStr("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", "ab"));
		//System.out.println(strStr("", ""));
	}

    
    public static int strStr(String haystack, String needle) {
        int k = 0, i;
         for(i = 0; i < haystack.length(); i++){
             if(k == needle.length()) break;
             if(haystack.charAt(i) == needle.charAt(k)){//if found match, check next char in needle
                 k++;
             }else{
                 i = i - k;//if found non-match, reset pointer in needle to 0, and start check again from i-k previous steps 
                 k = 0;
             }
         }
         return k == needle.length()? i-k :-1;
     }
  
    
   
       
}
