/**
 * This method treat the matrix as a single array and do binary search, and looks good at first glance
 * but it may cause several problems: 1) m*n may overflow 2) it damages the spatial locality (caching)
 * Time complexity: log(m*n)
 * @author hpPlayer
 * @date Mar 20, 2015 3:16:31 PM
 */
public class p074_sol2 {
	public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;
		int start = 0;
		int end = m * n -1;//this is so smart!!!, now we can treat the matrix as a single array!
		while(start <= end){
			int mid = (end + start)/2;
			//why index/ n is the row ? cuz we now treat the matrix as a single array which composed of several parts,
			//each part is one row, so if we want get the row number, we need to /the length of each row, and get the group number
			int row = mid/n;
			//why index%n, if we are get in mid of two groups, the remainder should be the index in the first group, when remainder 
			//decreased to 0, we know we have reached the second group
			int col = mid%n;
			if(target == matrix[row][col]) return true;
			else if(target > matrix[row][col]) start = mid+1;
			else end = mid -1;
		}
		return false;
	}
}
