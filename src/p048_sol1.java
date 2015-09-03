import java.util.Arrays;
/*
Rotate Image 

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?

*/
		
/**
 * This is my original AC solution without help
 * Here I used a backup array, that can help rotate the matrix
 * If the matrix has several layers, each layer the range of matrix will shrink by 1, and 
 * there is totally floor(n/2) layers.
 * 
 * What should be noticed is that. When we are rotating right edge and left edge, we should start with the bottom one
 * In other words, after the rotation, the first value in new edge is actually the last edge in left/right edge
 * 
 * Sol2 shows a very clear and easy-understand solution (visit each cell twice)
 * Sol3 uses similar basic idea but with different implementation (visit each cell one time)
 * 
 * But for clarity, I think sol2 is more clear.
 * So sol2 is more recommended
 * 
 * @author hpPlayer
 * @date Sep 2, 2015 9:19:21 PM
 */
public class p048_sol1 {
	public static void main(String[] args) {
		int a[] = {1,2,3,4};
		int b[] = {5,6,7,8};
		int c[] = {9, 10, 11, 12};
		int d[] = {13,14,15,16};
		int e[][] = {a,b, c, d};
		 new p048_sol1().rotate(e);
		for(int[] l : e){
			System.out.println(Arrays.toString(l));
		}
	}

 	public void rotate(int[][] matrix) {
		if (matrix.length != matrix[0].length)
			return;
		int n = matrix.length;
		int[][] matrix2 = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix2[i][j] = matrix[i][j];
			}
		}

        for(int i = 0; i < n/2; i++){
                for(int l = i; l < n-i; l++){
                    matrix[i][l] = matrix2[n - 1 -l][i];
                    matrix[l][n-i-1] = matrix2[i][l];
                    matrix[n-1 - i][l] = matrix2[n-1-l][n-1 -i];
                    matrix[l][i] = matrix2[n-1 -i][l];
                }
            }

	}
}
