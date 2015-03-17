/**
 * Similar problem to p062, this approach is cleaner and shorter since it checks the condition inside the nested loop
 * When we encounter obstacle, we just set the value to 0 in either 2D-matrix or 1D-array, also the statement like xx? a:b make it shorter
 * 
 * @author hpPlayer
 * @date Mar 16, 2015 6:48:20 PM
 */
public class p063_sol2 {
	
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		if(m == 0 || n == 0) return 0;
		int[] dp = new int[n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(obstacleGrid[i][j] == 0){//we dont need update obstacle grid, since its default value is 0
					if(i == 0 && j == 0) dp[0] = 1;
					else dp[j] = (i > 0? dp[j] : 0) + (j > 0 ? dp[j-1] : 0);
				}else{
					dp[j] = 0;
				}
			}
		}
		return dp[n-1];
	}
	
	public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		if(m == 0 || n == 0) return 0;
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(obstacleGrid[i][j] == 0){//we dont need update obstacle grid, since its default value is 0
					if(i == 0 && j == 0) dp[i][j] = 1;
					else dp[i][j] = (i > 0? dp[i-1][j] : 0) + (j > 0 ? dp[i][j-1] : 0);
				}
			}
		}
		return dp[m-1][n-1];
	}
}
