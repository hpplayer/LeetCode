/*
 Dungeon Game 
  
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
The dungeon consists of M x N rooms laid out in a 2D grid.
Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2(K) -3	 3
-5	  -10	 1
10	   30	-5(P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
*/
/**
 * This is my original recursive approach, it failed the tests with LTE
 * And I doubt the correctness.
 * So lets move on to the DP approach, see sol2
 * @author hpPlayer
 * @date Apr 18, 2015 12:41:44 AM
 */
public class p174_sol1 {
	public static void main(String[] args){
		int[][] matrix = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
		System.out.println(calculateMinimumHP(matrix));
	}
	  public static int calculateMinimumHP(int[][] dungeon) {
	        result = dungeon[0][0] >= 0? 1 : -dungeon[0][0];
	        DFS(0, 1, dungeon, dungeon[0][0], result);
	        DFS(1, 0, dungeon, dungeon[0][0], result);
	        return result;
	    }
	    static int result = 0;
	    public static void DFS(int row, int col, int[][] dungeon, int currentSum, int currentResult){
	        if(row == dungeon.length && col == dungeon[0].length){
	            result = Math.min(currentResult, result);
	            return;
	        }
	        if(row >= dungeon.length || col >= dungeon[0].length){
	        	return;
	        }
	        if(row < dungeon.length -1 ){
	            int bot = dungeon[row][col];
	            int botResult = currentResult - bot;
	            DFS(row+1, col, dungeon, currentSum+bot, botResult);
	        }
	        
	        if(col < dungeon[0].length-1){
	            int right = dungeon[row][col];
	            int rightResult = currentResult - right;
	            DFS(row, col+1, dungeon, currentSum+right, rightResult);
	        }
	    }
}
