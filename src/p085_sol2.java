import java.util.Stack;

/**
 * This approach applies the idea in p84, now we treat each row as a horizontal line in p84
 * we firstly get the height(height of 1) of each x-axis, store them in a matrix
 * then applies the algorithm from p84 to calculate the maximal rectangle for each row 
 * 
 * each value in matrix means the height 1 in the same column above it 
 * @author hpPlayer
 * @date Apr 17, 2015 11:08:53 AM
 */
public class p085_sol2 {
	public static void main(String[] args){
		char[][] matrix = {{'1', '0', '1', '1','1'}, {'0', '1','0', '1','0'}
		,{'1','1','0','1', '1'}, {'0','1','1','1','1'}};
		System.out.println(maximalRectangle(matrix));
	}
	public static int maximalRectangle(char[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int[][] hisMatrix = new int[m][n];//matrix store bar height
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(matrix[i][j] == '1'){
					hisMatrix[i][j] = 1;
					for(int z = i-1; z >= 0; z--){
						if(matrix[z][j] == '1'){
							hisMatrix[i][j]++;//or hisMatrix[i][j] = hisMatrix[i-1][j] +1, use DP
						}else{
							break;//don't forget break the loop, if we found non consecutive 1 in the bar
						}
					}
				}
			}
		}
		
	     for(int i = 0; i < matrix[0].length; i++){
	        	for(int j = 0; j < matrix.length; j++){
	        		System.out.print(matrix[j][i] + " ");
	        	}
	        	System.out.println();
	     }
	     System.out.println();
	     for(int i = 0; i < hisMatrix[0].length; i++){
	        	for(int j = 0; j < hisMatrix.length; j++){
	        		System.out.print(hisMatrix[j][i] + " ");
	        	}
	        	System.out.println();
	     }
		int result = 0;
		for(int i =0; i < m; i++){
			result = Math.max(result, maxRectangle(hisMatrix[i]));
		}
		return result;
	}
	
	public static int maxRectangle(int[] matrix){
		Stack<Integer> stack = new Stack<Integer>();
		int result = 0;
		for(int i = 0; i <= matrix.length; i++){
			int newBar = i == matrix.length? 0 : matrix[i]; //handle right boundary case in incoming bar
			if(stack.isEmpty() || newBar >= matrix[stack.peek()]){
				stack.push(i);
			}else{
				int index = stack.pop();//pop top, get height of current maximal rectangle
				result = Math.max(result, matrix[index] * (stack.isEmpty()? i : i - stack.peek()-1));//handle left boundary case in prev top bar
				i--;//keep i stay
			}
		}
		return result;
	}
}
