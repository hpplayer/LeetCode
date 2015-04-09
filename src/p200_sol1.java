/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
*/


/**
 * This problem is very similar to p130 Surrounded Regions
 * Basically, just do DFS to each valid bit ('1' in this case)
 * use two array to give the direction of next move, this design can reduce the length of code
 * we will replace any visited island by '2', so next search will not touch those visited island
 * That's it.
 * Not hard if you know those tricks.
 * @author hpPlayer
 * @date Apr 8, 2015 10:40:10 PM
 */
public class p200_sol1 {
	public static void main(String[] args){
		String s = "1111111";
		char[][] c = {s.toCharArray()};
		System.out.println(new p200_sol1().numIslands(c));
	}
	 public int numIslands(char[][] grid) {
	        int count = 0;
	        for(int i = 0; i < grid.length; i++){
	            for(int j = 0; j < grid[0].length; j++){
	                if(grid[i][j] == '1'){
	                    DFS(i, j, grid);
	                    count ++;
	                }
	            }
	        }
	        return count;
	    }
	    
	  
	    public void DFS (int row, int col, char[][] grid){
	        if(row <0 || row > grid.length || col < 0 || col > grid[0].length) return;
	        if(grid[row][col] == '2' || grid[row][col] == '0') return;
	        grid[row][col] = '2';
	        int[] offsetR = {1, 0, -1, 0};
	        int[] offsetC = {0, 1, 0, -1};
	        
	        for(int i = 0; i < offsetR.length; i++){
	            int r = row+offsetR[i];
	            int c = col+offsetC[i];
	            if( r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == '1'){       
	                DFS(r, c, grid);
	                
	            }
	        }
	    }
}
