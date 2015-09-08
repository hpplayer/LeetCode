/*
Set Matrix Zeroes 

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/		
		

/**
 * Ok, I did't solve this problem. It requires us to use constant space, and do it in place
 * I firstly thought it would be solved with DFS, in which, each recursion will pass setZero value to next recursion, and next recursion
 * will also pass a setZero value. But I didn't realize when pass the setZero to next recursion, we need to let next recursion which direction
 * it comes from. If setZero is from vertical direction, then visit the horizontal neighbor of current cell should not be affected
 * So I still failed..
 * 
 * The best solution to this problem is more like a brainstorming problem.
 * We can just use the first row and first column to record the information.
 * So, we firstly need to decide whether first row and first column need to be set 0 with 2 extra loops
 * Then in our main loop, we check each cell, if it is 0, then we record the information on first row and first col.
 * This operation will let us set row and column to 0s if current cell is 0
 * After main loop end, we will revisit our matrix with information stored in first row/column, and set 0s accordingly
 * Finally, we just set the first row and column based on information we record in first step, then we are done
 * 
 * We can further optimize the solution.
 * Whether matrix[i][j] needs to be set to 0 is based on updated matrix[0][j] and matrix[i][0] value.
 * However, whether matrix[0][j] needs to be set to 0 is also based on updated matrix[i][0], where now i is = 0.
 * After that we have updated matrix[0][j], so the setting of matrix[i][0] cannot be depend on matrix[0][j], thus we need at least 
 * one extra step to store its raw value. This solution can be found in sol2.
 * 
 * Both sol1 and sol2 use similar idea that make use of col0 and row0
 * Sol1 can be more easily understood, while sol2 make some optimizations to make code cleaner
 * So both sol1 and sol2 are recommended
 * 
 * @author hpPlayer
 * @date Sep 7, 2015 2:20:52 PM
 */
public class p073_sol1 {
	public static void main(String[] args){
		int[] a = {1, 1, 0};
		int[] b = {1, 1, 1};
		int[] c = {1, 1, 1};
		int[][] matrix = {a,b,c};
		
		new p073_sol1().setZeroes(matrix);
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

    public void setZeroes(int[][] matrix) {
        //two booleans decide whether set row0/col0 to 0s
        boolean row0 = false, col0 = false;
        
        //check first column
        for(int i =0; i < matrix.length; i++){
            if(matrix[i][0] == 0){
                col0 = true;
                break;
            }
        }
        
        //check first row
        for(int i = 0; i < matrix[0].length; i++){
            if(matrix[0][i] == 0){
                row0 = true;
                break;
            }
        }
        
        //scan whole matrix, if certain col and row needs to be set 0 because current cell is 0
        //record it in first row/col. We will set corresponding cell in first row/col to 0 as well,
        //so it is safe to change value in first col/row
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        
        //revisit matrix, then set 0s
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        
        //if we need set row0 to 0s
        if(row0){
            for(int i = 0; i < matrix[0].length; i++) matrix[0][i] = 0;
        }
        
        //if we need set col0 to 0s
        if(col0){
            for(int i = 0; i < matrix.length; i++) matrix[i][0] = 0;
        }        
        
    }
}
