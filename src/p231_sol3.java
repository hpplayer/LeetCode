/**
 * As described in sol2, the int with power of 2 will only have one 1
 * and this 1 must appeared at first digit and no else where to go
 * So we simply count num of 1.
 * Integer.bitCount() will return the number of 1 in input, if return number is 0, we just return true
 * Otherwise return false
 * @author hpPlayer
 * @date Aug 14, 2015 12:41:25 AM
 */
public class p231_sol3 {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }
}
