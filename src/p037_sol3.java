import java.util.Arrays;
/**
 * This solution is more understandable, in which I combined several solution's good part together
 * when add a value to the board, we need check two things 
 * 1) if the value can be used regarding previous value, we need use a function
 * isValid() function to check if same row, same column, or same block has duplicate value there. row and column are easy to check 
 * for the small 3*3 block, we can check in following way: get current mid point by row/3 * 3 + 1 and col/3 * 3 + 1 (row/3 or col/3 
 * let us know how many 3 units u have before, and multiple 3 get the inital points of that block, then +1, we get the mid ). After getting 
 * mid, the small block is easy to check, we can check the block by nested loop from -1 to 1  
 * 2) if next DFS is valid, after inserting current value, we need check the status in next layer. Our DFS will return false, if we 
 * have tried all possible values for this node, and none works, if next DFS return false, then we know current value is inappropriate
 * so need try next value.
 * 
 * Remark:
 * our main loop is from 0 - 80, which is the total size of the board, but how can we get the column and row of current elemnt?
 * Easy: total size / i is the rows/columns that you have visited, total size % i is the column/row where you are in this row/column
 * If our main loop can go through all 81 cases without leaving empty, then we know our DFS is done, we can just return the result
 * 
 * This solution may not the best, since each DFS need check all previous elements in the board, but it is very understandable, so 
 * that's why I think this is the best solution
 * 
 * 
 * @author hpPlayer
 * @date Mar 14, 2015 12:23:35 AM
 */

public class p037_sol3 {
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
    public static void solveSudoku(char[][] board) {
        DFS(board);
    }
    public static boolean DFS(char[][] board){
    	int col = 0, row = 0, i = 0;
    	int total = board[0].length * board.length;
        for(i = 0; i < total; i++){
           col = i/board[0].length;
           row = i%board.length;
            if(board[row][col] == '.')
            	break;//found empty element, begin handle it
        }
        
        if (i == total){//if we can finish this loop without break, then all values should be correct
        	//System.out.println("im here");
        	return true;
        }
        for(int m = 1; m < 10; m++){//begin try different values 
        	if(isValid(board, row, col, (char)(m+'0'))){//if this value can be used
        		board[row][col] = (char)(m+'0');
        		if(DFS(board)){//if this value can be used and following DFS can also finish
        			return true;//then we found this value
        		}
        		board[row][col] = '.';//else, this value can't be used, we need try next value
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
    
}
