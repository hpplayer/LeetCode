import java.util.Arrays;
/**
 * This DP algorithm works in this way:
 * When we calculate the area of rectangle, we can do with three variables,
 * left boundary, right boundary and height.
 * Here we use three arrays to store those variables in each column.
 * The values in left and right array are indexes of left and right boundary
 * The values in height array is the cumulative height ended in that point
 * The leftmost index is 0, while the rightmost index is n-1
 * but these are 0-based, when we calculate area we need 1 based, so we can add 1 to right array (let rightmost index becomes n)
 * Of course, we can also initialize the right ary with n-1, then add 1 back during calculation, but it is more convenient to 
 * let right array initialized with n, so we dont need to care about +1 or -1 during calculation (maximalRectangle2() initialized right array
 * with n-1)
 * 
 * We do loop row by row. In each row, we do inner loop in each column.
 * If value in the matrix is '1':
 * height[j] ++, we either keep left[j] original value or update it to moved left boundary value if previous
 * loop encounter '0' and our width shrinks, similarly, we either keep right[j] original value or update it to moved right boundary value if previous
 * loop encounter '0' and our width shrinks,
 * 
 * If value in the matrix is '0':
 * height[j] = 0, we move left boundary rightward by set currLeft to j+1, and reset left[j] = 0
 * reset means this value prevent next loop make use of this left value, and release the limitation
 * If we have consecutive 1 vertically, then we need keep previous left value, so we know the following rows will not have 
 * width larger than previous row. However if we encountered 0, that means previous row will not affect our new rectangles, so we can 
 * release this condition. This is similar to right boundary. We move right boundary leftward by set currRight = j (j is already -1 for n)
 * are reset right[j] = n to release the limitation
 * After we have visited all elements in the row, then we can just calculate the area and update result if needed
 *  
 * Remark:
 * left and right boundary calculation must be separated, otherwise we may not able to update all right[j] or left[j] value when found '0'
 * Since the end of left is the start of right boundary.
 * @author hpPlayer
 * @date Apr 17, 2015 12:27:01 PM
 */

public class p085_sol3 {
	public static void main(String[] args){
		char[][] matrix = {{'0', '1'}};
		//char[][] matrix = {{'1', '0', '1', '1','1'}, {'0', '1','0', '1','0'}
		//,{'1','1','0','1', '1'}, {'0','1','1','1','1'}};
		System.out.println(maximalRectangle(matrix));
	}
	
	public static int maximalRectangle(char[][] matrix) {
		if(matrix.length == 0) return 0;
		int result = 0;
		int m = matrix.length;
		int n = matrix[0].length;
		int[] left = new int[n], right = new int[n], height = new int[n];//left[] and right[] record the index of left and right boundary of current rectangle
		Arrays.fill(right, n);
		for(int i = 0; i < m; i++){
			int curr_left = 0, curr_right = n;
			for(int j = 0; j < n; j++){
				if(matrix[i][j] == '1'){
					height[j] ++;
					//curr_left records the real-time index of left boundary, if we found '0', curr_left will move rightward
					left[j] = Math.max(curr_left, left[j]);
					//curr_right records the real-time index of right boundary, if we found '0', curr_leftright will move leftward	
					
					
				}else{
					height[j] = 0;
					left[j] = 0;
					curr_left = j+1;
			
				}
			}
			for(int j = n -1; j >= 0; j--){
				if(matrix[i][j] == '1'){
					right[j] = Math.min(curr_right, right[j]);
				}else{
					right[j] = n;
					curr_right = j;
				}
			}
			for(int j = 0; j < n; j++){
				result = Math.max(result, (right[j] - left[j]) * height[j]);
			}
		}
		//System.out.println(Arrays.toString(left));
		//System.out.println(Arrays.toString(right));
		//System.out.println(Arrays.toString(height));
		return result;
	}
	public static int maximalRectangle2(char[][] matrix) {
		if(matrix.length == 0) return 0;
		int result = 0;
		int m = matrix.length;
		int n = matrix[0].length;
		int[] left = new int[n], right = new int[n], height = new int[n];//left[] and right[] record the index of left and right boundary of current rectangle
		Arrays.fill(right, n-1);
		for(int i = 0; i < m; i++){
			int curr_left = 0, curr_right = n;
			for(int j = 0; j < n; j++){
				if(matrix[i][j] == '1'){
					height[j] ++;
					//curr_left records the real-time index of left boundary, if we found '0', curr_left will move rightward
					left[j] = Math.max(curr_left, left[j]);
					//curr_right records the real-time index of right boundary, if we found '0', curr_leftright will move leftward	
				}else{
					height[j] = 0;
					left[j] = 0;
					curr_left = j+1;
				}
			}
			for(int j = n -1; j >= 0; j--){
				if(matrix[i][j] == '1'){
					//System.out.println(right[j]);
					right[j] = Math.min(curr_right, right[j]);
				}else{
					right[j] = n-1;
					curr_right = j-1;
				}
			}
			for(int j = 0; j < n; j++){
				result = Math.max(result, (right[j] - left[j] + 1) * height[j]);
			}
		}
		System.out.println(Arrays.toString(left));
		System.out.println(Arrays.toString(right));
		System.out.println(Arrays.toString(height));
		return result;
	}
}
