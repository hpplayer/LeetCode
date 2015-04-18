
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
