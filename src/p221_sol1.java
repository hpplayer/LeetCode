/* Maximal Square 

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.

*/

/**
 * This is my original AC solution without help.
 * My basic idea is creating a dp matrix, each cell will save the maximum edge of square that ends at this point
 * We firstly check the value in [y][x], if it is 0 then we simply jump over this cell, if it is 1,
 * then we check [y-1][x-1] to see if we can enlarge a square or just create a new square
 * Since it requires a valid square, I create a isValid() to check if we got 0 in x and y axis of this cell, in other words,
 * whether we have two valid edges of new square.
 * If we are able to enlarge a square, then we compare it will our stored result to see if we found a larger square
 * Finally we output the result * result, since result is just the length of edge
 * 
 * But I forgot some important things:
 * 1) In my old version, I checked x and y axis from 0 to that cell, which is incorrect. Because we may have valid square 
 * that is not start from 0. So I added a parameter "boundary" to indicate where to start
 * 
 * 2) In my old version, I set the cell to 1 once the isValid() return false, since I though false means we will not able 
 * to get a larger square
 * 
 * But even if isValid() return false, we may still have a square with length > 1 that ends at this cell. For example 
 * 
 * 1 1 0
 * 1 1 1
 * 1 1 1 
 * 
 * The last cell will get false when run isValid(), but it still has a square ends there of length 2
 * My solution is adding an extra condition case which can check all valid ranges within boundary to see if we can get a smaller 
 * square that ends in the cell. like in above example, I will check boundary of 1 and 0.
 * 
 * My algorithm uses dp matrix but is not the best way to solve the problem. See sol2 for a more convenient solution
 * @author hpPlayer
 * @date Aug 3, 2015 6:01:22 PM
 */

public class p221_sol1 {
	   public static void main(String[] args){
		   /*
		   char[] a = {'1', '0', '1', '0'};
		   char[] b = {'1', '0', '1', '1'};
		   char[] c = {'1', '0', '1', '1'};
		   char[] d = {'1', '1', '1', '1'};
		   */
		   char[] a = {'0', '0', '0', '1'};
		   char[] b = {'1', '1', '0', '1'};
		   char[] c = {'1', '1', '1', '1'};
		   char[] d = {'0', '1', '1', '1'};
		   char[] f = {'0', '1', '1', '1'};
		   char[][] test = {a,b,c,d, f};
		   System.out.println(new p221_sol1().maximalSquare(test));
	   }
	   public int maximalSquare(char[][] matrix) {
	        if (matrix == null || matrix.length == 0) return 0;
	        int[][] dp = new int[matrix.length][matrix[0].length];
	        int result = 0;
	        for(int i = 0; i < dp.length; i++){
	            if (matrix[i][0] == '0'){
	                dp[i][0] = 0;
	            }else{
	                dp[i][0] = 1;
	                result = Math.max(result, 1);
	            }
	        }
	        
	        for(int i = 0; i < dp[0].length; i++){
	            if (matrix[0][i] == '0'){
	                dp[0][i] = 0;
	            }else{
	                dp[0][i] = 1;
	                result = Math.max(result, 1);
	            }
	        }
	        
	        for(int i = 1; i < dp.length; i++){
	            for(int j = 1; j < dp[0].length; j++){
	                if (matrix[i][j] == '0'){
	                    dp[i][j] = 0;
	                }else{
	                    if(dp[i-1][j-1] != 0 && isValid(matrix, j, i, dp[i-1][j-1])){
	                        dp[i][j] = dp[i-1][j-1] + 1;
	                        result = Math.max(result, dp[i][j]);
	                    }else if(dp[i-1][j-1] == 0){
	                    	dp[i][j] = 1;
	                    }else{
	                    	for(int l = dp[i-1][j-1] -1; l >= 0; l --){
	                    		if(isValid(matrix, j, i, l)){
	                    			dp[i][j] = l + 1;
	                    			break;
	                    		}
	                    	}
	                    }
	                }
	            }
	        }
	        
	        /*
	        for(int i = 0; i < dp.length; i++){
	        	for (int j = 0; j < dp[0].length; j++){
	        		System.out.print(dp[i][j] + " ");
	        	}
	        	System.out.println();
	        }
	        */
	        return result * result;
	        
	    }
	    
	    
	    public boolean isValid(char[][] matrix, int x, int y, int boundary){//x is col num, y is row num
	        for (int i = x - boundary; i < x; i++){
	            if (matrix[y][i] == '0'){
	                return false;
	            }
	        }
	        
	        for(int j = y - boundary; j < y; j++){
	            if (matrix[j][x] == '0'){
	                return false;
	            }
	        }
	        
	        return true;
	    }
}
