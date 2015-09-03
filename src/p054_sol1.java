import java.util.ArrayList;
import java.util.List;
/**
 * This is my original AC solution without help!!
 * So happy, I made it!!!
 * haha, I believe my algorithm is very good.
 * But i will check other's solution tomorrow, stay tuned!
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
