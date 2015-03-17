/**
 * This problem proves the power of DP.
 * Each position may be reached only but its left and top grid, so the ways to reach here is the sum of its top and left grid.
 * Boundary case is the topmost and leftmost row/col. which can only be reached by its left/top grid, so the boundary values are 1
 * for these col/row. We can simply create a matrix to record the value of each previous grid, and get the next value by looking up 
 * the matrix and found its left and top value. This method's space complexity is O(mn)
 * 
 * But one observation is that we only need to know the left grid and top grid, no need to all previous rows.
 * Start from the leftmost col in this, our value is the boundary value which is 1, the second col should be the sum of 1 + its top
 * grid's value, the third col should be the sum of the second col's value + its top grid's value. So we only need to know the values 
 * in the top row, i.e. one row is enough, so we only need one array to represent the row, and update it grid by grid once we got the 
 * new value for the grid
 * 
 * Remark:
 * Dont forget the boundary case! and also the case that input is 0, 0
 * 
 * @author hpPlayer
 * @date Mar 16, 2015 5:38:56 PM
 */
public class p062_sol2 {
	public static void main(String[] args) {
		p062_sol2 temp = new p062_sol2();
		System.out.println(temp.uniquePaths(8, 10));
	}
	public int uniquePaths(int m, int n) {
		if(m == 0 ||n == 0) return 0;//dont forget special case
		int[] mn = new int[n];
		for(int i = 0; i < m; i++){//i: row , j : col
			mn[0] = 1;//all row at col 0 is 0
			for(int j = 1; j < n; j++){
				if(i == 0) mn[j] = 1;//all col at row 0 is 0
				else mn[j] = mn[j-1] + mn[j];//other row, start at 1
			}
		}
		return mn[n-1];
	}
	
	public int uniquePaths2(int m, int n) {
		if(m == 0 ||n == 0) return 0;//dont forget special case
		int[][] mn = new int[m][n];
		for(int i = 0; i < m; i++){//i: row , j : col
			mn[i][0] = 1;//all row at col 0 is 0
			for(int j = 1; j < n; j++){
				if(i == 0) mn[i][j] = 1;//all col at row 0 is 0
				else mn[i][j] = mn[i-1][j] + mn[i][j-1];//other row, start at 1
			}
		}
		return mn[m-1][n-1];
	}
	
}
