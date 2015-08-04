/**
 * Actually this problems can be solved in a more simple way.
 * Since we need to build a square, it means we need to consider the value stored in three directions (left, top and left-top)
 * Additionally, we need also consider the value in current cell.
 * If current cell has value 0, that means we will no way to build square around this cell
 * If current cells has value 1, that means we may build a square around this cell, but we need to check three directions.
 * How comes the dp solution. we will let dp[current cell] = Min(left, top, left-top) + 1
 * Examples:
 * 1) 
 * 0 1 
 * 1 1 <-dp value = 0 + 1 = 1
 * 
 * 1 1
 * 1 1 <- dp value = 1 + 1 = 2
 * 
 * 0 1 1
 * 1 1 1 <-dp value = 1 + 1 = 2 
 * 1 1 1 <-dp value = 1 + 1 = 2
 * 
 * So, the simplest way is to build a dp matrix to compute dp value for each cell, then let result = max one
 * A better way is to build a two-rows matrix to compute dp value for each cell, since we only needs two-rows value
 * The best way is to use one-row matrix to compute dp value for each cell, but we need one more variable to store the left-top value
 * before it changed  (we only use one row, left-top value will be replaced by left value, so we need a temp variable to store that)
 * 
 * The solution below is my version of best way. It only uses one row matrix.
 * I do spend a lot of time on boundary condition (when j = 0)
 * 
 * sol3 lists a solution get avoid of boundary condition by creating a n + 1 one-row matrix
 * @author hpPlayer
 * @date Aug 3, 2015 11:18:21 PM
 */
public class p221_sol2 {
	public static void main(String[] args){
		
		 char[] a = {'1', '0'};
		 char[] b = {'0', '1'};
		 char[] c = {'0', '1'};
		 char[] d = {'0', '1'};
		 char[] e = {'1', '1'};
		 char[] f = {'0', '0'};
		 char[] g = {'0', '1'};
		/*
		char[] a = {'1', '0'};
		char[] b = {'1', '0'};
		char[][] matrix = {a,b};
		*/
		/*
		char[] a =  {'0', '0', '1', '0'};
		char[] b =  {'1', '1', '1', '1'};
		char[] c =  {'1', '1', '1', '1'};
		char[] d =  {'1', '1', '1', '0'};
		char[] e =  {'1', '1', '0', '0'};
		char[] f =  {'1', '1', '1', '1'};
		char[] g =  {'1', '1', '1', '0'};
		*/
		char[][] matrix = {a,b,c,d,e,f,g};
		
		System.out.println(new p221_sol2().maximalSquare(matrix));
	}
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length <= 0) return 0; //boundary case
        
        int[] dp = new int[matrix[0].length];
        int result = 0;
        int pre = 0;//store left-top value before it is replaced by left value
        for(int i = 0; i < matrix.length; i++){
        	//if (i == 0) dp[i] = matrix[i][0] - '0'; // not necessary since we will also update dp[0] when comes to j 
        	for(int j = 0; j < matrix[0].length; j++){
        		if (j == 0){
        			//remember always store values to pre before it changes, it costs me a lot of time to figure out this!!!!!!!!
        			pre = dp[j];//no prev value, set pre to its dp[j] or dp[0]
            		dp[j] = matrix[i][0] - '0';//since dp[0] is the boundary case, it can only be 0 or 1 based on its value!!!!!!!
            		result = Math.max(dp[j], result);//in case we only have 1 column 			
        		}else{
        			int temp = dp[j];
        			if (matrix[i][j] == '1'){
        				dp[j] = Math.min(pre, Math.min(dp[j], dp[j-1])) + 1;
        				//System.out.print(dp[j]);
        				result = Math.max(dp[j], result);
        			}else{
        				dp[j] = 0;//set to 0 since there is no way build square around cell with value 0
        			}
        			pre = temp;
        		}	
        	}
        	//System.out.println();
        }
        return result * result;
    }
}
