import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * Another approach towards this problem using hash.
 * Basic idea is simple: check row, check col, then check the block current element located
 * The difficult part is how to do all check in 9*9 steps.
 * Sol1 uses three separate loops to check cols, rows and blocks. For blocks, it firstly locate the center points, then check elements around.
 * Here we use another trick do the same thing.
 * 
 * We observe that we totally have 9 cols and 9 rows in each Sudoku, if we play with math then we can get:
 * 	 	  0 1 2 3 4 5 6 7 8
 * '/3'   0 0 0 1 1 1 2 2 2 //slow move 
 * '%3'   0 1 2 0 1 2 0 1 2 //fast move
 * '/3*3' 0 0 0 3 3 3 6 6 6 //slow move
 * '%3*3' 0 3 6 0 3 6 0 3 6 //fast move
 * 
 * '/3' and '/3*3' can be used to adjust the index of slow move axis, while '%3' and '%3*3' can be used to adjust the index of fast move 
 * axis. 
 * In our case, slow move axis is row, fast move axis is column,
 * 
 * So we use 'i/3*3' to locate the initial row of each block, use 'i%3*3' to locate the initial column of each block
 * Then use 'j/3' to adjust the row increment, and use 'j%3' to adjust col increment
 * 
 * Actually, i can be treated as the each 3 * 3 block number, we totally have 9 blocks in our Sudoku, so when we loop i from 0 to 9
 * we are actually checking each block
 * 
 * for example, when i = 5 (i.e. row = 5 and block number = 5), we do j = 0->8 to on i = 5,
 * the fifth block should start from row = 3, col = 6, end at row = 5, col = 8
 * then we can get:
 * row: 5/3*3 + 0/3 = 3, 5/3*3 + 1/3 = 3, 5/3*3 + 2/3 = 3, (all 3+0)
 * col: 5%3*3 + 0%3 = 6, 5%3*3 + 1%3 = 7, 5%3*3 + 2%3 = 8, (6+0, 6+1, 6+2)
 * 
 * row: 5/3*3 + 3/3 = 4, 5/3*3 + 4/3 = 4, 5/3*3 + 5/3 = 4, (all 3+1)
 * col: 5%3*3 + 3%3 = 6, 5%3*3 + 4%3 = 7, 5%3*3 + 5%3 = 8, (6+0, 6+1, 6+2)
 * 
 * row: 5/3*3 + 6/3 = 5, 5/3*3 + 7/3 = 5, 5/3*3 + 8/3 = 5, (all 3+2)
 * col: 5%3*3 + 6%3 = 6, 5%3*3 + 7%3 = 7, 5%3*3 + 8%3 = 8, (6+0, 6+1, 6+2) 
 * 
 * This algorithm only do 9*9 loops in total and takes 3 hashset to store element
 * @author hpPlayer
 * @date Aug 3, 2015 2:32:51 PM
 */

public class p036_sol2 {
	static char[] a= {'.', '8', '7', '6', '5', '4','3','2','1'};
	static char[] b= {'2', '.', '.', '.', '.', '.','.','.','.'};
	static char[] c= {'3', '.', '.', '.', '.', '.','.','.','.'};
	
	static char[] d= {'4', '.', '.', '.', '.', '.','.','.','.'};
	static char[] e= {'5', '.', '.', '.', '.', '.','.','.','.'};
	static char[] f= {'6', '.', '.', '.', '.', '.','.','.','.'};
	
	
	static char[] g= {'7', '.', '.', '.', '.', '.','.','.','.'};
	static char[] h= {'8', '.', '.', '.', '.', '.','.','.','.'};
	static char[] i= {'9', '.', '.', '.', '.', '.','.','.','.'};
	
	static char[][] board = {a,b,c,d,e,f,g,h,i};
	
	public static void main(String[] args){
		System.out.println(isValidSudoku(board));
		for(int i = 0; i < board.length; i++){
			System.out.println(Arrays.toString(board[i]));
		}

	}
    public static boolean isValidSudoku(char[][] board) {
        Set<Character> rows = new HashSet<Character>();//store values in same column on different rows
        Set<Character> cols = new HashSet<Character>();//store values in same row on different columns
        Set<Character> block = new HashSet<Character>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
            	//System.out.println(rows);
            	//System.out.println(board[i][j]);
                //check different cols on same row
                if(board[i][j] != '.' && !cols.add(board[i][j])){
                    return false;
                }
                
                //check different rows on same col
                if(board[j][i] != '.' && !rows.add(board[j][i])){
                    return false;
                }
                
                int RowIndex = i/3*3;
                int ColIndex = i%3*3;
                
                //j/3 =>0,1,2 while j%3 =>0,1,2
                if(board[RowIndex + j/3][ColIndex + j%3] != '.' && !block.add(board[RowIndex + j/3][ColIndex + j%3])){
                            return false;
                }
                
            }
            block.clear();            
            rows.clear();
            cols.clear();
        }
        
        return true;
        
    }
}
