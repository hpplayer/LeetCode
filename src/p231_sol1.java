/*
Power of Two 

Given an integer, write a function to determine if it is a power of two.
*/

/**
 * This is my original AC solution without help
 * I am not familiar with bit manipulation, so my idea may be immature at first glance
 * My idea is that I know integer with power of 2 will be represented by 1 follow many zeros
 * So what i am doing I want to get the size of binary representation of input, then scan the input
 * if any bit is not 0 and also is not the first bit, then return false
 * After the loop end and we checked all bits, we will return true
 * 
 * Shift is >> 1, so we can always check bit as the last bit
 * Check 0 is &1 != 0, so we can know whether current bit is 1 or 0
 * That's it
 * 
 * Remark:
 * 1) be careful with n <= 0, those numbers will never be power of 2, and 0 will pass our program to return true
 * so check n <= 0 in the first place then return false if needed
 * 2) Actually, we don't need to get the size, as we know there would be only 1 in the ideal input, 
 * so we can count number of 1 when scanning the input with the loop
 * 3) But actually, we have a better solution that even don't need loops, see sol2
 * 
 * sol2 is a more clever way, that is short without loop and clear
 * sol3 is a cheating solution that uses built-in Integer.bitCount()
 * sol4 is python version of sol2
 * 
 * Sol2 is the best solution!
 * @author hpPlayer
 * @date Aug 14, 2015 12:20:42 AM
 */
public class p231_sol1 {
	public static void main(String[] args){
		 //System.out.println(Integer.toBinaryString(8));
		// System.out.println(1&2);
		 System.out.println(new p231_sol1().isPowerOfTwo(0));
	}
    public boolean isPowerOfTwo(int n) {
       if (n == 0) return false;
       int temp = n;
       int size = 0;
       while(temp > 0){
    	   temp >>= 1;
    	   size ++;
       }
       //System.out.println(size);
       int count = 1;
       while (n != 0){
    	  if ((n&1) != 0 && count != size){
    		  return false;
    	  }
    	  n>>=1;
    	  count ++;
       }
       
       return true;
    }
}
