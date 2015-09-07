/**
 * Another iterative solution.
 * Instead of changing the original input.
 * We use a variable i to calculate the multipies of power of 5 (power from 1...i, until 5^i > n)
 * But here, we are increasing i each loop and may cause overflow problem. So I declare i as long type
 * The main idea is similar to sol2 and sol3
 * 
 * We firstly calculate how many 5^1 the input has.
 * 
 * Then we calculate how many 5^2 the input has, each of them will provide one additional 0s
 * Why we say additional 0s? because we already touch this number when calculate 5^1, and include one of 0 in first loop
 * This is like in sol2 and sol3, we remove 5s from each loop/recursion, so each remaining multipies of 5^2 may still provide one 5
 * 
 * Then we calculate how many 5^3 the input has, each of them will provide one additional 0s
 * Why we say one additional 0s? because we already touch this number when calculate 5^1 and 5^2, and include two of 0 in prev loops
 * 
 * then loop continue...
 * until 5^i is larger than n then we stop calculation
 * 
 * 
 * @author hpPlayer
 * @date Sep 7, 2015 12:50:13 AM
 */
public class p172_sol4 {
    public int trailingZeroes(int n) {
        int result = 0;
        for(long i = 5; i <= n; i*= 5){
            result += n/i;
        }
        
        return result;
    }
}
