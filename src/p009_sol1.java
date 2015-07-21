/*
 * Palindrome Number 
 * 
 * Determine whether an integer is a palindrome. Do this without extra space.
 */

/**
 * This is my own solution towards this problem
 * Basically, I reverse the integer from previous problem's (p007) method.
 * Then, compare the reversed integer with the original input.
 * I use the double type to catch the overflow case
 * 
 * This solution is intuitive, but it uses the extra space (ret) to store the reversed integer
 * Also it needs us to check the overflow problem
 * 
 * For a more clever and more mathematical solution, please ref to sol2
 * 
 * Remark:
 * 1) negative number here is considered to be non-palindrome
 * 2) Assert x >= 0 might can be used to replace
 * if (x < 0) return false
 * it is awesome!
 * BUT.....
 * assert in my local machine will print false
 * while in the leetcode, it will give the runtime error.
 * For the safe purpose,
 * still keeping use original one
 * @author hpPlayer
 * @date Jul 21, 2015 1:23:58 PM
 */
public class p009_sol1 {
	public static void main(String[] args){
		System.out.println(new p009_sol1().isPalindrome(-781));
	}
    public boolean isPalindrome(int x) {
    	if (x < 0) return false;
    	//assert x >= 0;//the use of assert is awesome!
        double ret = 0;
        int y = x;
        while(x != 0){
            ret = ret * 10 + x%10;
            x /= 10;
        }
        if (ret >= Integer.MAX_VALUE || ret <= Integer.MIN_VALUE) return false;  
        return ret == y;
    }
    
}
