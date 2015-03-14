import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * CTCI has same question in chapter 9, solution is in p.333
 * My AC solution, without help...
 * Basic idea:
 * Basically, I was doing DFS on each elements in first column in first run, then check all elements in second row in second run,
 * and use isValid function to check if current element can be placed without counting previous placed element, so a tree is built 
 * on this logic. Of course, we need to reset the node back after done its DFS, so it will not interfere next node 
 *  
 * We need three major functions:
 * 1) DFS on each element in columns, i.e. try all elements in that column
 * 2) isValid function to check if this placement is legal
 * 3) convert result to String
 * 
 * Remark:
 * when I was working on this problem, I made two faults
 * 1) DFS on col, we only need one loop in each DFS, i.e. check all elements in current col, 
 * 2) The way I check isValid. Initially, I use similar steps to previous problems that use offsetX, offsetY, but thats not true.
 * We are not checking all connected Q, instead we need to check left top diagonal, left bot diagonal, and left row of the current node
 * so we can't use offSetx, offSet y which is used to check all connected nodes.
 * 
 * @author hpPlayer
 * @date Mar 13, 2015 12:27:50 PM
 */

public class p051_sol1 {
	public static void main(String[] args){
		List<String[]> result = solveNQueens(4);
		System.out.println(result.size());
		for(String[] str:  result){
			System.out.println(Arrays.toString(str));
			System.out.println();
		}
	}
	 public static List<String[]> solveNQueens(int n) {
	        List<String[]> result = new ArrayList<String[]>();
	        if(n <= 0){
	            return result;
	        }
	        boolean[][] board = new boolean[n][n];

	        DFS(result, board, 0, 0);
	        return result;
	    }
	    
	    public static void DFS(List<String[]> result, boolean[][] board, int x, int y){
	     	//System.out.println("I reach here x: " + x + " y: " + y);
	        if(x == board[0].length){
	            result.add(buildString(board));
	            return;
	        }
	       // for(int i = x; i < board[0].length; i++){//look col by col, j: row, i: col
	            for(int j = 0; j < board.length; j++){
	            	if(x == 2 && y == 0){
	            	//	System.out.println(isValid(board, x, j));
	            	}
	                if(isValid(board, x, j)){
	                    board[j][x] = true;
	                    DFS(result, board, x+1, j);
	                    board[j][x] = false;
	                }
	            }
	        //}
	    }
	    
	    public static String[] buildString(boolean[][] board){
	        String[] result = new String[board.length];
	        for(int i = 0; i < board.length; i++){
	             StringBuilder sb = new StringBuilder();
	            for(int j = 0; j < board[0].length; j++){
	                if(board[i][j]){
	                    sb.append('Q');
	                }else{
	                    sb.append('.');
	                }
	            }
	            result[i] = sb.toString();
	        }
	        return result;
	    }
	    public static boolean isValid(boolean[][] board, int x, int y){
	    	for(int i = x-1; i >= 0; i--){
	    		if(board[y][i]){//same row check
	    			return false;
	    		}
	    	}	    	
	    	
	    	for(int i = 1; i <= x && i + y < board.length; i++){
	    		if(board[y+i][x-i]){//same row check
	    			return false;
	    		}
	    	}	
	    	
	     	for(int i = 1; i <= x && y -i >=0; i++){
	    		if(board[y-i][x-i]){//same row check
	    			return false;
	    		}
	    	}	
	        return true;
	    }
}
