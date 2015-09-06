/**
 * Similar solution to sol1 but here, we only right shift input n without shift marker
 * here we use n&1 to get the value in index 0
 * Since 1 is always 000000....00001, so we don't need to care about the sign bit 
 * We can either write (n&1) == 1 or (n&1) != 0 to check the bit there
 * @author hpPlayer
 * @date Sep 5, 2015 10:57:55 PM
 */
public class p191_sol3 {
	public static void main(String[] args){
		System.out.println(new p191_sol3().hammingWeight(Integer.parseUnsignedInt("4294967295")));
	}
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            if ((n&1) != 0) count ++;
            n >>>= 1;
        }
        
        return count;
    }
}
