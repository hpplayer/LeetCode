/**
 * stepwise algorithm We start from top right, lets call it node n, all nodes in
 * the same row must be < n, while all nodes in the same column must be > n if
 * we found target < n, then we can eliminate this column, else if we found
 * target > n, then we can eliminate this row. so each step we can eliminate one
 * row or one column. Time complexity: we reach the bottom left node, which
 * means we traverse m height and n length, so it is O(m+n)
 * 
 * @author hpPlayer
 * @date Mar 20, 2015 3:39:45 PM
 */
public class p074_sol3 {
	public boolean searchMatrix(int[][] matrix, int target) {
		int row = 0;//start from top row 
		int col = matrix[0].length -1 ; //start from last column
		while(row < matrix.length && col >= 0){//boundary case is reaching the bottom left
			if(matrix[row][col] > target){//current node too large, step left and eliminate one col
				col--;
			}else if(matrix[row][col] < target){//current node too small, step down and eliminate one row
				row++;
			}else{//found match
				return true;
			}
		}
		return false;
	}
}
