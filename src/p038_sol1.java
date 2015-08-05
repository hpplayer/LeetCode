/*
Count and Say

The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/


/**
 * My original AC solution without help
 * The problem description is misleading.
 * The example  given by the problem is actually a sequence, 1th is 1, 2nd is 11, 3rd is 21....
 * The next value in the sequence is the read value of previous value, like 11 is read value of 1, 21 is the read value of 11
 * It actually asks us return the nth value in the sequence
 * 
 * It is easily come up with a DFS(recursive) solution, each recursion we do a read value of previous value in the sequence, then pass this
 * value to next recursion.
 * 
 * For creating the read value of previous value, I use the string method, in which I control the read pointer of this string and use a "prev"
 * to store the previous char, so that we can count the number of same consecutive char in the string
 * 
 * Remark:
 * Since the sequence starts with number 1, so our recursion ends at n = 1
 * 
 * This is recursive approach, iterative approach see sol2
 * @author hpPlayer
 * @date Aug 4, 2015 11:16:39 PM
 */
public class p038_sol1 {
	public static void main(String[] args){
		int a = 4;
		System.out.println(new p038_sol1().countAndSay(a));
	}
    public String countAndSay(int n) {
    	return DFS(n, "1");
    }
    
    public String DFS(int n, String s){
    	if(n == 1){
    		return s;
    	}
    	StringBuilder sb = new StringBuilder();
    	char prev = s.charAt(0);
        int count = 1;
        for(int i = 1; i < s.length(); i++){
              char curr = s.charAt(i);
              if ( curr == prev){
                  count ++;
              }else{
            	  sb.append(count);
            	  sb.append(prev);
                  count = 1;//don't forget set count to 1, as we always have value stored in prev
              }
              prev = curr;
        }
        
        //boundary case
        sb.append(count);
        sb.append(prev);
    	
    	return DFS(n -1, sb.toString());
    	

    }
}
