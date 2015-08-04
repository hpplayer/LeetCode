/**
 * Since here we build a n+1 dp array, 
 * we won't worry much about the boundary case.
 * We will not touch dp[0] = 0 which is actually -1 index, so our dp[1] is acutally the boundary condition and its values will 
 * always be set to 1 or 0 based on value in cell.
 * temp will record the top-left value before it is replaced by left value, then set it to pre for next computation
 * The different between sol2 and sol3 is only the trick to deal with boundary condition. The basic idea is same
 * @author hpPlayer
 * @date Aug 3, 2015 11:41:13 PM
 */
public class p221_sol3 {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] dp = new int[n+1];
        int pre = 0;
        int result = 0;

        for (int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                int temp = dp[j];
                if (matrix[i-1][j-1] == '1'){
                    dp[j] = Math.min(dp[j-1], Math.min(dp[j], pre)) +1;
                    result = Math.max(dp[j], result);
                }else{
                    dp[j] = 0;
                }
                pre = temp;
            }
        }

     return result * result;
    }
}
