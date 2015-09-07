/**
 * Iterative solution, very similar to sol2
 * Each loop, we calculate how many 5s current input has, record it in result.
 * Then remove those 5s away from multipies of 5.
 * 
 * @author hpPlayer
 * @date Sep 7, 2015 12:41:58 AM
 */
public class p172_sol3 {
    public int trailingZeroes(int n) {
        int result = 0;
        while(n > 0){
            n /= 5;
            result += n;
        }
        
        return result;
    }
}
