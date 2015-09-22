import java.util.Arrays;
/*
Permutations

Given a collection of numbers, return all possible permutations.
For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */


/**
 * This is KMP algorithm that can reduce the time complexity to O(n+k), where n is size of haystack, k is size of needle
 * Build array k, visit char in haystack n
 * The basic idea is as following: 
 * we record the pattern of needle and find if there is some overlap parts existing in pattern.
 * Example: (needle: ABCDABCE) we know ABC and ABC will be potential match, if there is we got match error in second ABC, then we 
 * can just let correspond haystack part to match the first ABC and see if it will succeed. The place where we record the overlap
 * pattern is called next array. each value at i indicates from 0 to i -1, the maximum length of overlapping pattern. We dont have value
 * in the left of first index, so set its value to -1, we have only one value in the left of second index, and single char will not form 
 * pattern, so set its value to 0. For other value, we compare the start char and end char(1 vs i-k, 2 vs i-k+1,...)
 * In above example, the value in array should be:
 * A  B  C D A B C E
 * -1 0  0 0 0 1 2 3
 * take E as example, it means in previous part, our max repeat pattern is ABC, whose length is 3
 * After got this array, whenever we found unmatched char in comparison, we can simply set the pointer in array to next[i], it means 
 * we dont need revert i - k index overtime, we can just set it to the value of next[i] which usually is smaller than i-k.
 * More detailed explantation, please see code below
 * 
 * Remark:
 * Sol2 of Shortest Palindrome (p214) also uses the KMP algorithm, we can take it as reference
 * 
 * @author hpPlayer
 * @date Mar 27, 2015 7:58:25 PM
 */

public class p028_sol4 {
	public static void main(String[] args){
		System.out.println(strStr("abcabcdef", "abcdabce"));
		//System.out.println(strStr("mississippi", "mississippi"));
		//System.out.println("mississippi".equals("mississippi"));
		//System.out.println(strStr("", ""));
	}
	
	private static int[] buildNexttable(char[] pattern){
		int table[] = new int[pattern.length]; 
		//table[0] = -1;//we dont have string before patter[0], and to distinguish this case with other cases like patter[i] = 0, we set it to j 
		table[0] = 0;//OMG set table[0] = 0 also works!, I used to think set it to -1 is a must! 
		int prev = -1;//set it to -1 is meaningful, so in later operation, we can use -1 to set unmatched table value to 0
		int post = 0;
		while(post < pattern.length -1){//post is the loop pointer, we have set table value at 0 to -1, so we only needs update length -1 times
//Like described before, if we are reseting prev pointer to head, then prev == -1 will force loop fill 0 to the corresponding table value
//if we found match, then we just increase the prev and post to indicate now we are extending the matched part			
			if(prev == -1 || pattern[prev] == pattern[post]){ 
				prev ++;
				post ++;
				table[post] = prev;
			}else{
				/*why set prev = -1 ? cuz we need to reset the prev pointer to the start, 
				 *if initial pointer does not match with current post pointer,then its not a valid matching substring(i.e. not in the
				 *form of abab), we set its table value to 0, means our substring needs move one step right
				 *So the only operation here is reset prev pointer to -1, if we cant find matching
				 */
				prev = -1;//
				//prev = table[prev];
			}
		}
		return table;
	}
	
    public static int strStr(String haystack, String needle) {
    	if(needle.length() == 0) return 0;
    	if(needle.length() > haystack.length()) return -1;
    	int next[] = buildNexttable(needle.toCharArray());
    	System.out.println(Arrays.toString(next));
    	int i = 0, j = 0;//i is haystack pointer, j is needle pointer
    	while(i < haystack.length()){
    		if(haystack.charAt(i) == needle.charAt(j)){
    			i++; j++;
    			if(j == needle.length()) return i - j;
    		}else if ( j > 0){//if curr value in needle and haystack are not matched, we need first lookup how far we need turn back
    			/*if need turn back, this value indicate how far we need turn back
    			 * Example. if current j = 5, next[j] = 2, then set j = 2, means we move substring rightward by 5 -2 = 3 steps
    			 * If j = 0, then our loop continue, need in next loop it will fall into else case, where we will force haystack string
    			 * move leftward, which looks like we move needle rightward by 1 step
    			 */
    			j = next[j];
    			
    		}else{//else here only means we need handle case that  j == 0, i.e. we need move haystack leftward by 1 
    			/*we are still looking for the start of our needle, also this case controls the flow of loop, 
    			 * if we reset the head of needle (let j = next[j] = 0), then this case will help us skip current index in haystack
    			 */
    			i++;
    		}
    	}
    	return -1;
    }
    /**************************************************************************************************************************/
    private static int[] failureFunction(char[] str) {
        int[] f = new int[str.length+1];
        for (int i = 2; i < f.length; i++) {
            int j = f[i-1];
            while (j > 0 && str[j] != str[i-1]) j = f[j];
            if (j > 0 || str[j] == str[i-1]) f[i] = j+1;
        }
        return f;
    }

    public static int strStr2(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (needle.length() <= haystack.length()) {
            int[] f = failureFunction(needle.toCharArray());
            System.out.println(Arrays.toString(f));
            int i = 0, j = 0;
            while (i < haystack.length()) {
            	System.out.println(j);
                if (haystack.charAt(i) == needle.charAt(j)) {
                    i++; j++;
                    if (j == needle.length()) return i-j;
                } else if (j > 0) j = f[j];
                else{
                	System.out.println("im ehre");
                	i++;
                }
            }
        }
        return -1;
    }
}
