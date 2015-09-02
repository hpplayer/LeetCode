/*
Bitwise AND of Numbers Range 

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
*/	
		

/**
 * This problem is not hard if you found the observation.
 * The problem requires us return the value that produced by bitwise AND of all numbers in the range.
 * Actually, this is a very strict condition. Any of the number who has 0 in the index i, will cause the 0 in the index i of result
 * 
 * When we will get non zero value in index i? only all of our numbers have 1 in this index i, which means our goal is to find the common
 * part of all numbers in range. Since we are given the maximum and minimum number of the range (inclusively), we can safely say if we found 
 * the common part of m and n, then those part will reserved in the result and all remaining index will have 0
 *  
 * So, where can we find such common part of m and n? It is not hard to think about.
 * Assume our m is like 
 * xxxxy
 * our n is like 
 * zzzzy
 * 
 * Will y be the common part of all numbers in range m to n?
 * No, unless m = n, since we will have many numbers between m and n, the value will go like 0->1->0->1
 * 
 * However, if now we have m like 
 * yxxxx
 * n like
 * yzzzz
 * 
 * Will y be the common part of all numbers in range m to n?
 * Yes, since numbers are consecutive, the largest number will of course keep still.
 * 
 * So the basic idea is to find the leftmost common part of m and n, and keep this part as the prefix of our result
 * 
 * Then, we need to find the postfix of our result, How?
 * Well, we will only have postfix if m and n have same length.
 * Think about the case  that n is longer than b,
 * then we will never have common part of this pair.
 * like: 
 * m: 0010 (length 2)
 * n: 1000 (length 4)
 * the leftmost share part is just 0, which means we don't have postfix
 * 
 * So if we know the postfix will exist only when m and n have same length,
 * then the length of postfix is just the length of m or n minus the length of leftmost common part
 * 
 * Solution here works in this way:
 * right shift m and n to see if we found the leftmost common part,
 * if not repeatedly do this, until m and n become equal or both become 0
 * During the loop, we use a counter to check how long the postfix is.
 * If they have same length, then this counter will help us build the postfix
 * if they don't have same length, then we will always get 0
 * 
 * Both sol1 and sol2 use similar idea but different implementation
 * Speed is very close.
 * But considering the easiness of understanding, sol1 is more recommended
 * 
 * @author hpPlayer
 * @date Sep 2, 2015 12:16:39 AM
 */
public class p201_sol1 {
	public static void main(String[] args){
		//System.out.println(new p201_sol1().rangeBitwiseAnd(0, 2147483647));
		System.out.println(new p201_sol1().rangeBitwiseAnd(6, 13));

	}
    public int rangeBitwiseAnd(int m, int n) {
        int postfix = 0;
        while(m != n){
            m >>= 1;
            n >>= 1;
            postfix ++;
        }
        return m << postfix;
    }
}
