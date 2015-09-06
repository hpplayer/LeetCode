/**
 * Here we use count &= count-1 to remove the rightmost 1 in the bits
 * As discussed in sol1, our n may not always > 0, from computer perspective, if the integer is larger than 2^31 -1
 * We need treat is as unsigned integers, so we will continuously remove 1, if current n is not 0
 * @author hpPlayer
 * @date Sep 5, 2015 10:53:49 PM
 */
public class p191_sol2 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){//while (n> 0) is wrong, if the int is larger than Integer.MAX_VALUE, then it actually is negative from PC end
            n &= n-1;
            count ++;
        }
        return count;
    }
}
