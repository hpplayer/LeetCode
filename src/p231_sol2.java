/**
 * This solution is so awesome.
 * It works in following way:
 * since the int with power of 2 will only have one 1, the 1 must be in the first bit
 * if we minus 1 from that, we will get all 11111111, except the first bit become 0
 * so n&(n-1) should return all 000000, i.e 0
 * If the return result is not 0, we return false, otherwise return true
 * 
 * BEST solution!
 * 
 * Remark:
 * 1) don't forget check if 0 <= 0
 * 2) add parentheses around bit manipulation
 * @author hpPlayer
 * @date Aug 14, 2015 12:33:33 AM
 */
public class p231_sol2 {
	public static void main(String[] args){
		 //System.out.println(Integer.toBinaryString(8));
		// System.out.println(1&2);
		 System.out.println(new p231_sol2().isPowerOfTwo(8));
	}
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n-1)) == 0;
    }
}
