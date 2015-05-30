/**
 * My modified DP approach with one array, we need to handle last row outside main loop
 * And handle last column outside inner loop of main loop
 * The code looks more concise and clean now
 * 
 * @author hpPlayer
 * @date Apr 18, 2015 1:13:13 AM
 */
public class p174_sol3 {
	public int calculateMinimumHP(int[][] dungeon) {
		int m = dungeon.length, n = dungeon[0].length;
		int dp[] = new int[n];
		dp[n-1] = Math.max(1, -dungeon[m-1][n-1] + 1);//1 for survive when decreased health by dungeon[m-1][n-1], if it is <0
		//initialize array
		for(int i = n-2; i >= 0; i--){
			dp[i] = Math.max(1,  dp[i+1]-dungeon[m-1][i]);
		}
		for(int i = m -2; i >=0; i --){  
			dp[n-1] = Math.max(1, dp[n-1] - dungeon[i][n-1]);
			for(int j = n-2; j >= 0; j--){
				dp[j] = Math.max(1, Math.min(dp[j], dp[j+1]) - dungeon[i][j]);
			}
		}
		return dp[0];
	}
}
