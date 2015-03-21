/**
 * we use column-wise binary search
 * This algorithm should be fastest algorithm, it is a combination of divide and conquer + binary search
 * During the recursion, 
 * we firstly find the mid column of each matrix
 * then do search, if the target is in this column, return true,
 * otherwise we exit the search loop when find the first row that > target, lets say this node is N2, and its above node 
 * that is smaller than target, we call it N1
 * By doing this we divide the matrix into 4 parts, top left, top right, bot left, bot right
 * For these small matrix, the bot right node should be the largest node, while the top left node should be the smallest node
 * So, we can eliminate the top left matrix, since N1 is the largest node in this matrix but it is smaller than our target
 * we can also eliminate the bot right matrix since N2 is the smallest node in this matrix but it is larger than our target
 * so if we cannot find the target in current column, we can just search top right and bot left matrix, and do similar search
 * to those matrix
 * 
 * This algorithm is fastest and costs mlog(n) time, log n for search the mid col, m for search node N2
 * 
 * Remark:
 * If we can use binary search to search the node n2, then the time complexity will be reduced to logm * logn
 * This algorithm can be seen in searchMid2, which is almost same to searchMid except using binary search in each column
 * @author hpPlayer
 * @date Mar 20, 2015 4:02:18 PM
 */
public class p074_sol4 {
	public static void main(String[] args){
		int[] a = {1};
		int[] b = {3};
		int[][] c = {a};
		
		p074_sol4 test = new p074_sol4();
		System.out.println(test.searchMatrix(c, 1));
		
	}
 	public boolean searchMatrix(int[][] matrix, int target) {
 		return searchMid(matrix, target, 0, matrix.length-1, 0, matrix[0].length-1);
 	}
	public boolean searchMid(int[][] matrix, int target, int up, int down, int left, int right){
		if(up > down || left > right) return false;
		int midcol = (left + right)/2;
		int row = up;
		while(row <= down && matrix[row][midcol] <= target){
			if(matrix[row][midcol] == target){
			
				return true;//we found the match
			}
			row ++;//if current row in mid column is not we want, continue search
		}
		//target not found in current col, and we get the first row that is larger than target
		return searchMid(matrix, target, up, row-1, midcol+1, right) ||searchMid(matrix, target, row, down, left, midcol-1);
	}
	
	public boolean searchMid2(int[][] matrix, int target, int up, int down, int left, int right){
		if(up > down || left > right) return false;
		int midcol = (left + right)/2;
		Integer row = searchIncol(matrix, target, midcol, up, down);
		if(row == null) return true;
		//target not found in current col, and we get the first row that is larger than target
		return searchMid(matrix, target, up, row-1, midcol+1, right) ||searchMid(matrix, target, row, down, left, midcol-1);
	}
	public Integer searchIncol(int[][] matrix, int target, int col, int up, int down){
		while(up <= down){
			int mid = (up + down) /2;
			if(matrix[mid][col] == target){
				return null;
			}else if (matrix[mid][col] < target){
				up = mid +1;
			}else{
				down = mid -1;
			}
		}
		return up;//if not found, the stop case is up pass the target and become the next node that is larger than target
	}
}
