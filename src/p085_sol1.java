/*
 * Maximal Rectangle 
 * 
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */

/**
 * This algorithm is not hard to understand, but it is hard to come up with, and it is a beautiful approach
 * 
 * This algorithm goes in this way:
 * We build a dp matrix, each element records the length of consecutive 1's stop here in the same row
 * for example:
 * input
 * 1 1 0 1 1
 * 1 1 1 1 1 
 * then, the dp matrix:
 * 1 2 0 1 2
 * 1 2 3 4 5
 * 
 * Then we calculate the rectangle area of each element in dp matrix,
 * we just need to check all elements(width) above in the same col and all elements below in the same col
 * By doing this way, we are getting the height of rectangle as taller as possible
 * Once we found the element's width is smaller than current width, we know this way comes to the end
 * 
 * Remark:
 * 1) we must check the height start from input row to 0 or from input row to end of matrix, so we know the height will be consecutive otherwise 
 * If we only check one direction, it is very possible the width we pick currently is too big or too small, so height counting will stop
 * in the mid, even we may have a mid width that cover those small or large width. 
 * By check two direction, it is like we are able to pick the mid width so it can extend both direction.
 * 
 * 2) we will break the loop once the width become smaller
 * 3) Don't forget boundary case, like input matrix is "[]" (handled by if(matrix.length == 0) return 0;)
 * Time complexity = O(m*n) * O(m)
 * 
 * The approach that uses algorithm in p84 is in sol2
 * The fastest approach is in sol3
 * @author hpPlayer
 * @date Apr 16, 2015 3:57:46 PM
 */
public class p085_sol1 {
	public static void main(String[] args){
		char[][] matrix = {{'1', '0', '1', '0','0'}, {'1', '0','1', '1','1'}
		,{'1','1','1','1', '1'}, {'1','0','0','1','0'}};
		System.out.println(maximalRectangle(matrix));
	}
	
	  public static int maximalRectangle(char[][] matrix) {
	        if(matrix.length == 0) return 0;//!!!!!!!dont forget boundary case
	        int m = matrix.length, n = matrix[0].length;
	        int[][] dp = new int[m][n];
	        for(int i = 0; i < m*n; i++){
	        	//System.out.println("im here");
	            int r = i/n;
	            int c = i%n;
	            if(matrix[r][c] == '0'){
	            	//System.out.println("im here");
	                dp[r][c] = 0;
	            }else{//has '1'
	                if(c == 0){
	                	//System.out.println("im here");
	                    dp[r][c] = matrix[r][c] - '0';
	                   // System.out.println(dp[r][c]);
	                }else{
	                    dp[r][c] = dp[r][c-1] + 1;
	                }
	            }
	        }
	        //print new matrix
	        for(int i = 0; i < dp[0].length; i++){
	        	for(int j = 0; j < dp.length; j++){
	        		System.out.print(dp[j][i] + " ");
	        	}
	        	System.out.println();
	        }
	        int result = 0;
	        for(int i = matrix.length-1; i >= 0; i--){
	            for(int j = 0; j < matrix[0].length; j++){
	            	//System.out.println(getArea(dp, i, j));
	                result = Math.max(result, getArea(dp, i, j));
	            }
	        }
	        return result;
	    }
	    
	    public static int getArea(int[][] dp, int row, int col){
	        int height = 0;
	        for(int i = row; i >= 0; i--){
	            if(dp[i][col] >= dp[row][col]){
	                height ++;
	            }else{
	                break;
	            }
	        }
	        int j = 0;
	       for(int i = row+1; i <dp.length; i++){//we need start from row+1, since if we found width < row+1, we will immediately break the loop
	        //j++;
	        //for(int i = dp.length-1; i > row; i--){
	            if(dp[i][col] >= dp[row][col]){
	                height ++;
	            }else{
	                break;
	            }
	        }
	        //System.out.println(j);
	        return height * dp[row][col];
	    }
}
