import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * good approach, More explanation please refer to sol3
 * 
 * Original DFS is void, and do DFS to each node until the global variable found is set to true; 
 * But It forgot the case that the DFS may also come from non-empty value.
 *
 * So when I changed DFS to boolean, my result will be incorrect, cuz we did not return boolean value from the case 
 * that non-empty node gives next DFS..
 * @author hpPlayer
 * @date Mar 14, 2015 12:36:12 AM
 */

public class p037_sol2 {
	 static boolean found;
	 static int count = 0;
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
			
			System.out.println(count);
		}
		
	 public static void solveSudoku(char[][] board) {
	        DFS(board, 0, 0);
	    }

	 
	    public static boolean DFS(char[][] board, int row, int col){
	  	  count++;
	    	if(row == 9){
	            row = 0;
	            col = col + 1;
	        }
	        
	        if(col == 9){
	     
	            return true;
	        }
	  
	        if(board[row][col] != '.'){
	        	if(DFS(board, row+1, col)){
	        		return true;//dont forget return true in case the father node of DFS is from original element 
	        	}
	          //  DFS(board, row+1, col);
	        }else{
	            for(int m = 1; m < 10; m++){//begin try different values 
	        	if(isValid(board, row, col, (char)(m+'0'))){//if this value can be used
	    	     	board[row][col] = (char)(m+'0');
	        		if(DFS(board, row+1, col)){ //check next row
	        		   // System.out.println("im here");
	        			return true;//
	        		}
	        		board[row][col] = '.';//how can we get here? when we finish next DFS and it does not return anything, then we know this value is incorrect, we need reset it.
	        	}
	        }
	        }
	        return false;
	    }
	    
	    public static boolean isValid(char[][] board, int row, int col, char value){
	    	int rowMid = row/3 *3 +1; //get row of mid point in the block
	    	int colMid = col/3 *3 +1;//get col of mid point in the block
	    	
	    	//check the box
	    	for(int i = -1; i <= 1; i++){
	    		for(int j = -1; j <= 1; j++){
	    			if(board[rowMid+i][colMid+j] == value){
	    				return false;
	    			}
	    		}
	    	}
	    	
	    	for(int i = 0; i < board.length; i++){//we have board.length == board[0].length, so we can combine them together
	    		if(board[i][col] == value){//check each element in current column
	    			return false;
	    		}
	    		if(board[row][i] == value){//check each element in current row
	    			return false;
	    		}
	    	}
	    	
	    	return true;
	    }

		/*
	    public static void solveSudoku(char[][] board) {
	        found = false;
	        dfs(board, 0, 0);
	    }

	    private static void dfs(char[][] board, int x, int y){
	   	  count++;
	        // position correction
	        if(x==9){
	            x = 0;
	            y = y + 1;
	        }

	        // base case
	        if(y==9) {found = true;return;}

	        // recursion
	        if(board[x][y]!='.'){
	            dfs(board, x+1, y);
	        }else{
	            List<Integer> list = validNum(board, x, y);
	            for(int i = 0;i < list.size();i++){
	                board[x][y] = (char)('0' + list.get(i));
	                dfs(board, x+1, y);
	                if(found) return;
	                board[x][y] = '.';
	            }
	        }
	    }

	    //beautiful valid code!!!
	    private static List<Integer> validNum(char[][] board, int x, int y){
	        List<Integer> list = new ArrayList<Integer>();
	        int center_x = x/3 * 3 + 1;
	        int center_y = y/3 * 3 + 1;
	        boolean[] engaged = new boolean[9];
	        for(int i = -1;i <= 1;i++)
	            for(int j = -1;j <= 1;j++)
	                if(board[center_x+i][center_y+j]!='.')
	                    engaged[(int)(board[center_x+i][center_y+j]-'1')] = true;
	        for(int i = 0;i < 9;i++)
	            if(board[i][y]!='.') engaged[(int)(board[i][y] - '1')] = true;
	        for(int j = 0;j < 9;j++)
	            if(board[x][j]!='.') engaged[(int)(board[x][j] - '1')] = true;
	        for(int i = 0;i < engaged.length;i++)
	            if(!engaged[i]) list.add(i+1);
	        return list;
	    }
	*/
}
