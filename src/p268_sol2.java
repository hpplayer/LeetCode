/**
 * The baisc idea is same to sol1, but here we got the expected sum by sum equation, which is 
 * (a1 + an)*n/2
 * 
 * Be careful, the expected sum start from 0 to n, and totally n + 1 elements 
 * so the sum should be 
 * (0 + n) * (n+ 1)/2
 * 
 * 
 * @author hpPlayer
 * @date Aug 23, 2015 5:20:04 PM
 */
public class p268_sol2 {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int num : nums){
            sum += num;
        }
        int n = nums.length;
        return (n+1)*(0+n)/2 - sum;
        
    }
}
