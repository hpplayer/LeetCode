import java.util.Arrays;
/**
 * The in-place algorithm works in following way:
 * We observe that the in-place swap can be done by 
 * reverse(top-down) the matrix then transposing the matrix:
 * input:
 * a b
 * c d 
 * reverse (top-down):
 * c d
 * a b
 * transposing:
 * c a
 * d b
 * this is exactly the matrix that rotated 90 degree clockwise
 * 
 * How we find such observation? I don't know, it just works
 * 
 * Below is the code does the things above
 * I use the in-place technique to reverse and transpose matrix
 * In-place swap requires the input two values are not in same cell
 * Here I use two parameters a and b to reverse the matrix to avoid we swap the single row
 * If you think it a and b are not allow not use
 * rotate2() is another version of in-place swap, it is guaranteed the loop will stop before we begin swap in the single row
 * 
 * The running time should be O(n^2)
 * 
 * Remark:
 *     top 			 		   left
 * left   right    =>  bottom		 top
 *    bottom				  right
 *    
 *    in reverse, we just swap top and bottom
 *    in transpose, we swap new top(actually bottom) with left, new bottom(actually top) with right 
 *    
 * Which is exactly the same thing that we rotate the matrix
 * @author hpPlayer
 * @date Sep 2, 2015 10:58:16 PM
 */

public class p048_sol2 {
	public static void main(String[] args) {
		int a[] = {1,2,3,4};
		int b[] = {5,6,7,8};
		int c[] = {9, 10, 11, 12};
		int d[] = {13,14,15,16};
		int e[][] = {a,b, c, d};
		 new p048_sol2().rotate(e);
		for(int[] l : e){
			System.out.println(Arrays.toString(l));
		}
	}
	
    public void rotate(int[][] matrix) {
        int a = 0, b = matrix.length - 1, n = matrix.length;
        //reverse matrix, use a and b to avoid reverse single row (odd number of rows)
        while (a < b){
            for(int i = 0; i < n; i++){
                matrix[a][i] += matrix[b][i];
                matrix[b][i] = matrix[a][i] - matrix[b][i];
                matrix[a][i] -= matrix[b][i];
            }
            a++;
            b--;
        }

		
        for(int i = 0; i < n; i++){
            //[i][i] is diagonal , j = i + 1, then [i][j] is the cell after it, the [j][i] is the cell just below
        	//if we swap them, then we are doing transpose
        	//We can also replace it by 
        	//for(int j = 0; j < i; j++){
        	//which has same result
            for(int j = i+1; j < n; j++){
                matrix[i][j] += matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] -= matrix[j][i];
            }
        }
        
    }
    
    public void rotate2(int[][] matrix) {
        int a = 0, b = matrix.length - 1, n = matrix.length;
        //reverse matrix, use a and b to avoid reverse single row (odd number of rows)
        //while (a < b){
        for(int j = 0; j < n/2; j++){
            for(int i = 0; i < n; i++){
                matrix[j][i] += matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[j][i] - matrix[n-1-j][i];
                matrix[j][i] -= matrix[n-1-j][i];
            }
        }
           //a++;
           // b--;
       // }
        
        for(int i = 0; i < n; i++){
            //i is diagnoal, j = i + 1 is the cell after it
            //for(int j = i+1; j < n; j++){
            for(int j = 0; j < i; j++){
                matrix[i][j] += matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] -= matrix[j][i];
            }
        }
        
    }
}
