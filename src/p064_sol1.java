/**
 * My AC solution without help!
 * For first row, its value is from its left + its value 
 * For first column, its value is from its top + its value
 * For other positions, we chose between its top and left grid and get the min one to add its own values..
 * @author hpPlayer
 * @date Mar 16, 2015 10:07:05 PM
 */
public class p064_sol1 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(m ==0 || n == 0) return 0;
        int[] DP = new int[n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if( i == 0 && j == 0) DP[0] = grid[0][0];
                else DP[j] = grid[i][j] + Math.min((i > 0? DP[j] : DP[j-1]) , (j > 0? DP[j-1] : DP[j]) );
            }
        }
        return DP[n-1];
    }
}
