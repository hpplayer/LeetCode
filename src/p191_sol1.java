/*
Number of 1 Bits 

Write a function that takes an unsigned integer and returns the number of ¡¯1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ¡¯11' has binary representation 00000000000000000000000000001011, so the function should return 3.
*/

/**
 * OK, this is a very interesting problem. It teaches me what are signed and unsigned integers
 * My original idea is to get the value in each index, if it is 1 then let counter ++ 
 * How we get value in each index? By shift! Each int is 32 bit, so my main program will loop 32 times, and each time we leftshift 1
 * hammingWeight2() is the program implemented my original thought. But it failed at the test case 2147483648 (10000000000000000000000000000000)
 * 
 * Why?
 * This is because the input of problem is unsigned integers. Originally, the leftmost bit in the int is used as sign bit
 * 
 * if the sign bit is 0 then it will be treated as positive int:
 * int(2147483647) = (01111111111111111111111111111111)binary
 * 
 * if the sign bit is 1 then it will be treated as negative int
 * int(2147483648) = (10000000000000000000000000000000)binary = -2147483648
 * 
 * If we are asked to treat the int as unsigned int, then we will treat the sign bit as other bits in the number.
 * However, if we use general right shift sign (>>), it is an actually signed right shift, which means it will preserve the sign of
 * the integer. So, if we simply replace >> by unsighed right shift sign (>>>) which will treat sign bit as other bits, then we will
 * get right solution. In other words, we will keep sign bit as 1, if we use signed right shift when the input is larger than 2^31 -1
 * 
 * Based on this observation, we will have several variations of solution
 * like hammingWeight() here, instead of using signed right shift sign, we right shift the marker to compare the bit
 * 
 * Remark:
 * 1) Only right shift has the signed/unsigned difference
 * 2) If you exceed the maximum positive value(2^31 -1), it will then recycle to a negative value. As in signed int,
 * the negative integers are stored as the complement of positive number
 * 
 * Sol2 lists another solution that use n & (n-1) which can be used to remove rightmost 1
 * Sol3 lists a similar solution to sol1, but with different implementations
 *
 * Since all solutions are very short, sol1 and sol3 are similar while sol2 provides a new approach
 * All of solutions are recommended!
 * 
 * @author hpPlayer
 * @date Sep 5, 2015 9:58:36 PM
 */
public class p191_sol1 {
	public static void main(String[] args){
		System.out.println(new p191_sol1().hammingWeight(Integer.parseUnsignedInt("4294967295")));
	}
	
	public int hammingWeight(int n) {
		int count = 0;
		for(int i = 0; i < 32; i++){
			if((n&(1<<i)) == 1<<i){
				count ++;
			}
		}
		return count;
	}
	
	public int hammingWeight2(int n) {
		int count = 0;
		for(int i = 0; i < 32; i++){
			if((n&(1<<i))>>>i == 1){
				count ++;
			}
		}
		return count;
	}
	

}
