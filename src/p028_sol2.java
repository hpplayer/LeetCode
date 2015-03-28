/**
 * Official solution from leetcode, the code is not complex, but nor easy to write.
 * we have nested loops, the inner loop check matches start with current i in haystack
 * if we found any unmatched char, then we know our start index with not be the current one, we break nested loop, 
 * and continue main loop,  if we found a whole match of needle , we can simply return i, which is convenient. 
 * @author hpPlayer
 * @date Mar 27, 2015 7:37:15 PM
 */
public class p028_sol2 {
	public static void main(String[] args){
		//System.out.println(strStr("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", "ab"));
		System.out.println(strStr("ab", "ab"));
		//System.out.println(strStr("", ""));
	}
	
	public static int strStr(String haystack, String needle){
		for(int i =0; ; i++){
			for(int j = 0; ; j++){
				if(j == needle.length()) return i;
				if(i + j == haystack.length()) return -1;//reach end of haystack, still not found match
				if(needle.charAt(j) != haystack.charAt(i+j)) break;
			}
		}
	}
	

}
