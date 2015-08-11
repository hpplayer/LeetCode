/*
Search a 2D Matrix II 

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/

/**
 * This is my original AC solution without help
 * This is a follow up problem of p074. I remember in that problem, we can use divide-and-conquer technique to break the 
 * matrix into four parts, then discard two parts and continue search in rest two parts. In regard of search, we can use 
 * binary search to make the search time from O(n) to O(logn)
 * 
 * The algorithm goes in this way:
 * 1) we locate the mid column of current search area
 * 2) use binary search in that column to find if the target is in that column, if it is then return null as the marker
 * otherwise return the first row's value that is larger than target
 * 3) since the return row contains the value larger than target value, then the searching area should be left bottom(including
 * return row) and top right (excluding return row and start from the row above it). 
 * 4) return true if any of these two searches return null, i.e. found the target
 * 5) Do same procedure to these two searches as 1) ->4)
 * 6) if our search has exceeded the matrix size that means we could not find target in those search.
 * 7) return result
 * 
 * So I write my code by looking up code in p074, submit and get AC
 * 
 * Remark:
 * Actually, I found the sol3(stepwise) and sol4(divide-and-conquer) of p074 can both be directly used in this problem, no
 * change is needed.
 * 
 * sol2 lists the stepwise problem. Official solution is also given to this problem, see PDF for more information
 * 
 * Regarding the speed, sol2 stepwise algorithm runs in O(m+n) time and sol1 costs mlog(n) time
 * But in the given test case, the difference of running time is not significant
 * Considering the simplicity, sol2 is better, but sol1 should also be pay attention to
 * 
 * 
 * sol3 is the Python version of sol1
 * sol4 is the Python version of sol2
 * 
 * for some reasons, sol4 runs much faster than sol3
 * 
 * @author hpPlayer
 * @date Aug 10, 2015 9:54:34 PM
 */
public class p240_sol1 {
	public static void main(String[] args){
		int[] a = {1,3,5};
		//int[] b = {3};
		int[][] c = {a};
		
		p240_sol1 test = new p240_sol1();
		System.out.println(test.searchMatrix(c, 1));
		
	}
    public boolean searchMatrix(int[][] matrix, int target) {
        return searchMid(matrix, target, 0, matrix.length-1, 0, matrix[0].length-1);
    }
    
    public boolean searchMid(int[][] matrix, int target, int up, int down, int left, int right){
    	//recursion ending signal that we exceed matrix size
        if (up > down ||left > right) return false;
        int midcol = (left + right)/2;
        Integer row = binarySearch(matrix, up, down, midcol, target);
        if(row == null) return true;
        //I firstly search left part by adding 1 to row, but get error when there is one line and the solution is on the left
        //that means we may still get result on the left part even if current mid value is larger than target
        //bottom left part, needs including current row, top right part can skipping current row
        //for cols, since we could not find target in binarySearch(), we are safely move leftward and rightward in next two searches

        return searchMid(matrix, target, row, down, left, midcol-1) || searchMid(matrix, target, up, row -1, midcol+1, right);
    }
    
    public Integer binarySearch(int[][] matrix, int up, int down, int col, int target){
        while (up <= down){
            int mid = (up - down)/2 + down;
            if(matrix[mid][col] == target){
                return null;
            }else if (matrix[mid][col] > target){
                down = mid - 1;
            }else{
                up = mid + 1;
            }
        }
        //return the row that center value is larger than target value
        return up;
    }
}
