import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * DP solution, use similar approach as p63 and p64. 
 * This problem can be converted to a half matrix problem, like:
 * So the boundary case becomes first column, and last column of each row will become the dummy first row
 * 1
 * 23
 * 456
 * 7890
 * Knowing this fact, we can do it with similar DP approach, and slightly change the code.
 * For the last row, we have summed all possible min sums, and we need to get the min among them,
 * so here we sort them first then pick the smallest one
 * 
 * Remark:
 * Above method must use matrix not one array, since before looking at the second boundary case, we have already updated values in dp[j-1], so there
 * is no way looking back. 
 * An optimization approach(see sol2) that use array is looking backward, since the size is shrinking from bottom to top, we can create such an
 * array record bot value, so update of current value will not affect the comparison in next node. Example:
 * 1
 * 23
 * 456
 * and we have dp[].
 * For the last row, the min sum is itself, so dp[0] = 4, dp[1] = 5, dp[2] = 6;
 * For the 23 row, the min sum for 2 is from 2 + min(4,5) = 6, so we update dp[0] = 6//this update will not later comparison
 * For the 23 row, the min sum for 3 is from 3 + min(5,6) = 8, so we update dp[1] = 8 //this update will not later comparison
 * For the first row, the min sum for 1 is from 1 + min(dp[0],dp[1]) = 7, we update dp[0] = 7
 * So our result is 7;
 * 
 * So smart! 
 * Of course the matrix DP approach is also awesome!
 * 
 * @author hpPlayer
 * @date Mar 16, 2015 10:36:02 PM
 */

public class p120_sol3 {
	public static void main(String[] args) {
		List<Integer> inner1 = new ArrayList<Integer>();
		inner1.add(-1);
		List<Integer> inner2 = new ArrayList<Integer>();
		inner2.add(-2);
		inner2.add(-3);
		List<List<Integer>> outer = new ArrayList<List<Integer>>();
		outer.add(inner1);
		outer.add(inner2);
		System.out.println(minimumTotal(outer));

	}
	 public static int minimumTotal(List<List<Integer>> triangle) {
	        if(triangle.isEmpty()) return 0;
	        int m = triangle.size();
	        int n = triangle.get(m-1).size();
	        int[][] dp = new int[m][n];
	        
	        for(int i = 0; i < m; i++){
	            for(int j = 0; j < i+1; j++){
	                if(i == 0 && j == 0) dp[0][0] = triangle.get(0).get(0);
	                else dp[i][j] = triangle.get(i).get(j) + Math.min((j > 0? dp[i-1][j-1] : dp[i-1][j]) , (i != j? dp[i-1][j] : dp[i-1][j-1]));
	            }
	        }
	        
	        Arrays.sort(dp[n-1]);
	        return dp[n-1][0];
	    }
}
