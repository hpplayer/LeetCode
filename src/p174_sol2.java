/**
 * DP approach. we fill the dp matrix from bottom right.
 * The value in dp matrix means the min health requirement when entering current room
 * So, when we got negative room value, we need reverse the value and plus the min requirement from prev step(right or bottom)
 * If we get positive or zero value, and it makes min health requirement >= 0, we need manually set it to 0, so we know it is the min health
 * requirement for current room
 * 
 * This problem does not have many boundary cases to deal with, so once we got the main idea, it is easy to implement without error
 * 
 * As other dp problem that uses matrix can replaced by a rolling array,
 * we can convert it to a rolling array dp approach to reduce the memory complexity from O(mn) to O(n), check sol3
 * @author hpPlayer
 * @date Apr 18, 2015 1:02:48 AM
 */
public class p174_sol2 {
	 public int calculateMinimumHP(int[][] dungeon) {
			int m = dungeon.length, n = dungeon[0].length;
			int dp[][] = new int[m][n];
			/*
			 * if dungeon[m-1][n-1] < 0, we will store the min health entering here that can maintain live
			 * if dungeon[m-1][n-1] >= 0, we will store 1 here, since we know 1 would be the min heath entering this room
			 */
			dp[m-1][n-1] = Math.max(1, -dungeon[m-1][n-1] + 1);//1 for maintain live (we should not just get health = room value, we need have > room value +1 )
			
			//handle boundary case last row
			for(int i = n-2; i >=0; i--){
				dp[m-1][i] = Math.max(1, dp[m-1][i+1] - dungeon[m-1][i]);//same as before, here we record the min health that we need enter this room, no +1 since +1 has been counted before
			}
			//handle boundary case last col
			for(int i = m-2; i >=0; i--){
				dp[i][n-1] = Math.max(1, dp[i+1][n-1] - dungeon[i][n-1]);//same as above
			}
			
			for(int i = m-2; i >= 0; i--){
				for(int j = n-2; j >=0; j--){
					//we need to pick the smaller health requirement from right or bot
					dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]);//same as before
				}
			}
			return dp[0][0];
		}
}
