import java.util.Arrays;
/* Valid Sudoku 
 * 
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */

/**
 * This is my original AC solution.
 * I used the same idea from p37_sol3's isValid()
 * I check each element, and run isValid() on each of them.
 * isValid() will check the same row, same col, and block of current element.
 * If we found same value with different col or row number then return false else return true
 * 
 * Sol2 uses the similar idea but uses the hashset to record the number
 * @author hpPlayer
 * @date Aug 3, 2015 1:17:21 PM
 */

public class p036_sol1 {
	static char[] a= {'.', '.', '4', '.', '.', '.','6','3','.'};
	static char[] b= {'.', '.', '.', '.', '.', '.','.','.','.'};
	static char[] c= {'5', '.', '.', '.', '.', '.','.','.','9'};
	
	static char[] d= {'.', '.', '.', '5', '6', '.','.','.','.'};
	static char[] e= {'4', '.', '3', '.', '.', '.','.','.','1'};
	static char[] f= {'.', '.', '.', '7', '.', '.','.','.','.'};
	
	
	static char[] g= {'.', '.', '.', '5', '.', '.','.','.','.'};
	static char[] h= {'.', '.', '.', '.', '.', '.','.','.','.'};
	static char[] i= {'.', '.', '.', '.', '.', '.','.','.','.'};
	
	static char[][] board = {a,b,c,d,e,f,g,h,i};
	
	public static void main(String[] args){
		System.out.println(isValidSudoku(board));
		for(int i = 0; i < board.length; i++){
			System.out.println(Arrays.toString(board[i]));
		}

	}
    public static boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < board.length; i++){
        	for(int j = 0; j < board[0].length; j++){
        		if(board[i][j] != '.' && !isValid(board, i, j, board[i][j])){
        			return false;
        		}
        	}
        }
        return true;
    }
    
    public static  boolean isValid(char[][] board, int row, int col, int val){
        int rowMid = row / 3 * 3 + 1;
        int colMid = col / 3 * 3 + 1;
        
        //check box
        for(int i = - 1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if(board[rowMid + i][ colMid + j] == val && (rowMid + i != row && colMid + j !=col)){
                    return false;
                }
            }
        }
        
        //check row and column
        for(int i = 0; i < board.length; i++){
        	//System.out.println("val1: " + board[row][i] + " val2: " + board[i][col] + " value: " + board[row][col]);
            //check colS
            if(board[row][i] == val && i != col){
                return false;
            }
            
            //check rowS
            if(board[i][col] == val && i != row){
                return false;
            }
        }
        
        
        return true;
    }
}
