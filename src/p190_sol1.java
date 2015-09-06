/*
Reverse Bits

Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer (p07)
*/


/**
 * This is my AC solution without help
 * 
 * I just finished similar problem p191, so now I am familiar with signed/unsigned operation
 * The input int is still unsigned, obviously, we need be careful with sign bit. Here I use unsigned right shift to deal with it
 * 
 * My idea:
 * I always read the last bit of input int, then put it forward on the result int. For "put", I use the "|" to do that
 * then I use unsigned right shift to update last bit of input int, which now become the last second bit of input
 * 
 * 
 * My solution is very simple and efficient
 * But here we have another solution that uses the idea of swap, it can be found in sol2
 * 
 * Since the length of int is fixed, which is 32 bits. Both sol1 and sol2 can run very fast
 * For simplicity, sol1 wins, for the speed, sol2 wins (only 5 swaps vs 32 loops in sol1)
 * Both are recommended!
 * 
 * @author hpPlayer
 * @date Sep 5, 2015 11:24:56 PM
 */
public class p190_sol1 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for(int i = 31; i >= 0; i--){
            result |= (n&1)<<i;
            n >>>= 1;
        }
        
        return result;
    }
}
