/*
Maximum Product Subarray 

Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/
/**
 * 
 * @author hpPlayer
 * @date Apr 15, 2015 12:07:41 AM
 */
public class p152_sol1 {
	public int maxProduct(int[] A){
		 if(A == null || A.length == 0) return Integer.MIN_VALUE;
		 int globalMax = A[0], max = A[0], min = A[0];
		 for(int i = 1; i < A.length; i++){
			 int mx = max, mn = min;//we need backup max and min here, since we will update and use it inside the loop
			 max = Math.max(Math.max(mx*A[i], A[i]), mn*A[i]);
			 min = Math.min(Math.min(mn*A[i], A[i]), mx*A[i]);
			 globalMax = Math.max(max, globalMax);
		 }
		 return globalMax;
	}
}
