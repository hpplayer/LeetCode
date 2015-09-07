/**
 * This is another implementation.
 * It still uses col0 and row0 to store information. But instead of we use two variables to record row0 and col0, here we use one 
 * variable to store the values in col0. For row0, we will treat it as other cells in matrix. But after we updated all values in 
 * row0, we loss the information that we need to update col0, thus we need an extra step to record the information on col0.
 * We observe if any of cell in col0 contains 0, then whole col0 will be set to 0. Thus we can use a boolean variable to track 0s
 * in col0;
 * 
 * To avoid the cleanup of row0 early and 
 * destroy our stored information, we will visit row0 and set 0s on row0 in last loop.
 * So the logic is like:
 * we firstly visit whole matrix from top to bottom, and set row0 and col0 accordingly. We also record the raw information of col0.
 * Then we visit the matrix backwards from bottom to top, and set cell value accordingly.
 *  
 * @author hpPlayer
 * @date Sep 7, 2015 2:57:51 PM
 */
public class p073_sol2 {
	public static void main(String[] args){
		int[] a = {1, 1, 0};
		int[] b = {1, 1, 1};
		int[] c = {1, 1, 1};
		int[][] matrix = {a,b,c};
		
		new p073_sol2().setZeroes(matrix);
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
    public void setZeroes(int[][] matrix) {
        boolean col0 = false;
        for(int i = 0; i < matrix.length; i++){
            //if any cell in col is 0, then we will set all cells in col0 to 0
            if(matrix[i][0] == 0) col0 = true;
            //first col is used to record information, so we only need to scan from col1 to col_end
            for(int j = 1; j <matrix[0].length; j++){
                //if any cell is 0, then we will record this information in col0 and row0
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        //set 0s 
        for(int i = matrix.length - 1; i >= 0; i--){
            //we use first col to store 0s information from col1 to col_end, so we can't change col0 values based
            //on values in col0 (0 in col0 does not necessary mean, col actually has 0)
            for(int j = matrix[0].length -1; j >= 1; j--){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
            //if col0 needs to be zero, then when we visit each row, we will set the first 0 to 0
            if(col0) matrix[i][0] = 0;
        }
    }
}
