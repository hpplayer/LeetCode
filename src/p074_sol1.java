/*
Search a 2D Matrix 

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
*/		
		
/**
 * My AC solution..without help
 * The basic idea is:
 * 1: locate the row. we need to find the row that row[0] smaller than target while next row row[0] bigger than the target 
 * we use a flag to indicate if such row exist, if not return false;
 * 2. do a normal binary search on that row, also use a flag, if found return true otherwise return false.
 * 
 * Since we do two separate binary search, so the code may look lengthy.
 * We have a better solution with only one binary search, please ref to sol2
 * @author hpPlayer
 * @date Mar 20, 2015 2:38:07 PM
 */
public class p074_sol1 {
	public static void main(String[] args){
		int[] a = {1};
		int[] b = {3};
		int[][] c = {a, b};
		
		p074_sol1 test = new p074_sol1();
		System.out.println(test.searchMatrix(c, 3));
		
	}
	 public boolean searchMatrix(int[][] matrix, int target) {
	        int m = matrix.length;
	        int n = matrix[0].length;
	        //search which row start 
	        int left = 0, right = m, mid = 0, i = 0;
	        boolean flag = false;
	        if(target >= matrix[m-1][0]) flag = true;
	        for(i = 0; i < m-1; i++){
	        	if(matrix[i][0] <= target){
	        		if(matrix[i+1][0] > target){
	        			flag = true;
	        			break;
	        		}
	        	}
	        }
	        if(!flag) return false;//not found row
	        System.out.println(i);
	        int row =i;//found row
	        flag = false;
	        left = 0;
	        right = n-1;
	        while(left <= right){
	            mid = (left + right)/2;
	            if(target > matrix[row][mid]){
	                left = mid+1;
	            } else if(target < matrix[row][mid]){
	                right = mid -1;
	            }else{
	                flag = true;
	                break;
	            }
	        }
	          if(!flag) return false;//not found row
	          return true;
	    }
}
