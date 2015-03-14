import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
/**
 * This approach is similar to sol3, where we look empty element one by one. More explanation please refer to sol3
 * 
 * @author hpPlayer
 * @date Mar 14, 2015 12:34:56 AM
 */

public class p037_sol1 {
	static char[] a= {'5', '3', '.', '.', '7', '.','.','.','.'};
	static char[] b= {'6', '.', '.', '1', '9', '5','.','.','.'};
	static char[] c= {'.', '9', '8', '.', '.', '.','.','6','.'};
	
	static char[] d= {'8', '.', '.', '.', '6', '.','.','.','3'};
	static char[] e= {'4', '.', '.', '8', '.', '3','.','.','1'};
	static char[] f= {'7', '.', '.', '.', '2', '.','.','.','6'};
	
	
	static char[] g= {'.', '6', '.', '.', '.', '.','2','8','.'};
	static char[] h= {'.', '.', '.', '4', '1', '9','.','.','5'};
	static char[] i= {'.', '.', '.', '.', '8', '.','.','7','9'};
	
	
	static char[][] board = {a,b,c,d,e,f,g,h,i};
	
	public static void main(String[] args){
		solveSudoku(board);
		for(int i = 0; i < board.length; i++){
			System.out.println(Arrays.toString(board[i]));
		}

	}
	public static class P{
		int x;
		int y;
		public P(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void solveSudoku(char[][] board) {
		ArrayList<P> empty = new ArrayList<P>();
        if(board == null || board.length == 0){
            return;
        }else{
          	for(int i = 0; i < board.length; i++){
          		for(int j = 0; j < board[0].length; j++){
          			if(board[i][j] == '.'){
          				empty.add(new P(j, i));
          			}
          		}
          	}
          	int goal = empty.size();
          	DFS(empty, board, 0, goal);
        }
    }
    
    public static boolean DFS(ArrayList<P> empty, char[][] board, int currIndex, int goal){
    	if(currIndex ==  goal ){
    		return true;
    	}
    	int row = empty.get(currIndex).y;
    	int col = empty.get(currIndex).x;

    		for(int j = 1; j < 10; j++){
    			if(isValid(board, col, row, (char)(j + '0'))){
    				board[row][col] = (char)(j + '0');
    				if(DFS(empty, board, currIndex+1, goal)){
    					return true;
    				}
    					board[row][col] = '.';//case this value is incorrect
    				}
    		}
   

    	return false;//case all fail, and the error is from last input
 
    }
    
    public static boolean isValid(char[][] board, int col, int row, char value){
        for(int i = 0; i < board[0].length; i++){
            if(board[row][i] == value) return false;
            if(board[i][col] == value) return false;
            
            /*
             * from the last row:
             * 2*3 + 2 = 8
             * 2*3 + 1 = 7
             * 2*3 + 0 = 6
             * 
             * from the last second row:
             * 1*3 + 2 = 5
             * 1*3 + 1 = 4
             * 1*3 + 0 = 3
             * ...
             * so the divisor is row number, and the remainder is col number
             * 
             */
            int row_s = 3*(row/3) + i/3;//find first row, and add 0 or 1 or 2 ( 8/3 cant get value >= 3)
            int col_s = 3*(col/3) + i%3;//find first col, and 0 or 1 or 2 ( 8%3 cant get value >= 3)
          //  System.out.println("row: " + row + " row_s: " + row_s + " col: " + col + " col_s: " + col_s );
            if(board[row_s][col_s]  == value) return false;
        }
        
 
        return true;
    }
}
