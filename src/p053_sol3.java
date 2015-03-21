/**
 * Divide and conquer approach,
 * For each non end-point node, the max value may come from three cases:
 * 1)only from left part
 * 2) from left part + node + right part
 * 3)only from right part
 * 
 * For left and right part, we simply do recursive
 * For 2), we need to find the leftmost index that makes index->mid is the maximum, similarly, we need to find the rightmost index 
 * that makes mid->index is the maximum
 * 
 * Remark:
 * since we pass the 0 and length-1 to the helper,
 * we must careful about the boundary case, we need to include L and R during calculation
 * Clean Code Handbook has more detailed explanation in DP and divide and conquer approach
 * @author hpPlayer
 * @date Mar 20, 2015 12:10:11 AM
 */
public class p053_sol3 {
    public int maxSubArray(int[] A) {
        return maxSubArrayHelper(A, 0, A.length-1);
    }
    
    public int maxSubArrayHelper(int[] A, int L, int R){
    	if(L > R) return Integer.MIN_VALUE;//reach end, return a value would not affect result
    	int M = L + (R-L)/2;
    	int leftAns = maxSubArrayHelper(A, L, M-1);//answer from left
    	int rightAns = maxSubArrayHelper(A, M+1, R);//answer from right
    	//now we need to consider the case contains M
    	int leftMax = 0;
    	int sum = 0;
    	for(int i = M-1; i >= L; i--){//start from M we want to find the leftmost prefix index i that maximize sum from i to M, and record sum to leftMax
       		sum += A[i];
    		leftMax = Math.max(sum, leftMax);
    	}
    	
    	int rightMax = 0;
    	sum = 0;
    	for(int i = M+1; i <=R; i++){
    		sum += A[i];
    		rightMax = Math.max(sum, rightMax);
    	}
    	return Math.max(leftMax + A[M]+ rightMax, Math.max(leftAns, rightAns));
    }
}
