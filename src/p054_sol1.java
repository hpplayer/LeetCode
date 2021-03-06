import java.util.ArrayList;
import java.util.List;

/*
Spiral Matrix 


Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/

		
/**
 * This is my original AC solution without help!!
 * So happy, I made it!!!
 * haha, I believe my algorithm is very good.
 * But i will check other's solution tomorrow, stay tuned!
 * 
 * Update:
 * 
 * I checked all solutions post online, recursive, python' short code
 * But they are all no better/not implementable in java, so I will still stick to my solution
 * 
 * The main idea is we use found bounds of the scan
 * when scan from left->right, we will use right bound
 * when scan from top->bottom, we will use bottom bound
 * when scan from right->left, we will use left bound
 * when scan from bottom -> top, we will use top bound
 * Each scan will update the bound to shrink the next scan range
 * So we just need a big while loop which will continue scan the board within ranges
 * The loop will be break when two bounds meet (like right meets left or top meets bottom)
 * 
 * What should be noticed, each while loop will change 4 bounds, but in the middle of while loop,
 * we may already get the while-break condition, so i put for if statements in the middle
 * 
 * My algorithm only scan each cell once, so the total running time should be O(mn)
 * 
 * @author hpPlayer
 * @date Sep 3, 2015 1:14:00 AM
 */

public class p054_sol1 {
	public static void main(String[] args){
		int[] a = {2,3};
		int[][] e = {a};
		/*
		int[] a = {1,2,3};
		int[] b = {4,5,6};
		int[] c = {7,8,9};
		int[][] e = {a,b,c};
		*/
		System.out.println(new p054_sol1().spiralOrder(e));
	}
    public List<Integer> spiralOrder(int[][] matrix) {
    	if(matrix.length == 0) return new ArrayList<Integer>();
        int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1;
        List<Integer> result = new ArrayList<Integer>();
        
        while(true){
            if(top > bottom || right < left) return result;
            for(int i = left; i <= right; i++){
                result.add(matrix[top][i]);
            }
            top ++;
            if(top > bottom) return result;
           // System.out.println("left: " + left + " right: " + right + " top: " + top + " bottom: " + bottom);
            for(int i = top; i <= bottom; i++){
                result.add(matrix[i][right]);
            }
            right --;
            if(right < left) return result;
           // System.out.println("left: " + left + " right: " + right + " top: " + top + " bottom: " + bottom);
            for(int i = right; i >= left; i --){
                result.add(matrix[bottom][i]);
            }
            bottom --;
            if(top > bottom) return result;
            //System.out.println("left: " + left + " right: " + right + " top: " + top + " bottom: " + bottom);
            for(int i = bottom; i >= top; i--){
                result.add(matrix[i][left]);
            }
            left ++;
            
            //System.out.println("left: " + left + " right: " + right + " top: " + top + " bottom: " + bottom);         
        }

    }
}
