import java.util.Arrays;
/*
Spiral Matrix II 

Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/		
		

/**
 * This is my original AC solution without help
 * It is very similar to Spiral Matrix (P054)
 * We use same technique (4 bounds) to visit the matrix, but here instead of reading cells, we just put an increasing constant k to the cell
 * 
 * We will visit the matrix layer by layer
 * The basic idea is using 4 parameters to indicate the range of layer
 * When we done scan on a layer, the four bounds will all shrink by 1, so next visit of layer will within range
 * 
 * Here, I put while condition to be left <= right && top <= bottom,
 * Because I think we still need to fill the matrix when left==right && top == bottom (the middle point)
 * When visiting the last layer, i.e. the middle point, after the first for loop, we will fill the corresponding value into the new matrix
 * after that we will get top > bottom, so we will not enter the second for loop. But we have right -- after the second the loop, so 
 * we will thus not enter the third for loop, we will have bottom --, plus previously top ++, so we will also not enter the fourth for loop(
 * now the top bound should be two units larger than bottom bound, so are the left and right bounds)
 * 
 * Remark:
 * Be careful about the bounds:
 * when visit left -> right, the condition is right > left
 * when visit top -> bottom, the condition is bottom > top
 * when visit right -> left, the condition is left < right
 * when visit bottom -> top, the condition is top < bottom
 *
 * I have made several errors in mixing above conditions, so be careful!
 * @author hpPlayer
 * @date Sep 3, 2015 1:17:09 PM
 */

public class p059_sol1 {
	public static void main(String[] args){
		for(int[] l : new p059_sol1().generateMatrix(6)){
			System.out.println(Arrays.toString(l));
		}
	}
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int left = 0, right = n-1, top = 0, bottom = n-1, k = 1;
        while(left <= right && top <= bottom){
        	//System.out.println("left: " + left + " right: " + right + " top: " + top + " bottom: " + bottom);
            for(int i = left; i <= right; i++){
                result[top][i] = k++;
            }
            top ++;
            
            //if(top > bottom) return result;
            //System.out.println("left: " + left + " right: " + right + " top: " + top + " bottom: " + bottom);
            for(int i = top; i <= bottom; i++){
                result[i][right] = k++;
            }
            right --;
            
            //if(left > right) return result;
           // System.out.println("left: " + left + " right: " + right + " top: " + top + " bottom: " + bottom);
            for(int i = right; i >= left; i--){
                result[bottom][i] = k++;
            }
            bottom --;
            //if(bottom < top) return result;
           // System.out.println("left: " + left + " right: " + right + " top: " + top + " bottom: " + bottom);
            //System.out.println(k);
            for(int i = bottom; i >= top; i--){
                result[i][left] = k++;
            }
            left ++;
        }
        //System.out.println(left);
       // System.out.println(right);
        return result;
    }
}
