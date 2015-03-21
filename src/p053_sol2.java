/**
 * Another DP value takes O(1) memory
 * this solution only uses 1 variable to store the global max value 
 * when update the current max value, we compare it with max value to see if we need update global value
 * @author hpPlayer
 * @date Mar 19, 2015 11:48:11 PM
 */
public class p053_sol2 {
    public int maxSubArray(int[] A) {
        if (A.length == 0) return 0;
        int length = A.length;
        int dp = A[0];
        int result = A[0];
        for(int i = 1; i < length; i++){
            dp = Math.max(dp + A[i], A[i]);//current Max
            result = Math.max(result, dp);//overall Max
        }
        
        return result;
    }
}
