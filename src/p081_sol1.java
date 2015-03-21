/**
 * due to allowing duplicate, the worst case becomes O(n)
 * The main idea is to previous question (p33), but here we have an additional case:
 * if A[mid] == A[start], we may have two cases:
 * 1) mid and start is the same index, 
 * 2) we have duplicate values in different index
 * How we handle this?
 * We want to avoid duplicate, because duplicates does not contribute to our results(example: 1, 1, 3 and 1, 3, the first 1 harms our 
 * original binary search) how to avoid those duplicates? one trick here is simply skip them.
 * We skip one duplicate each time, and it will not affect the result. This is like we will shrink our search to the range that only 
 * have non-duplicate elements. For case 1, this is same, since we know current mid/start is not our target, and we have reached the 
 * leftmost endpoint of our binary search, we either do not have target in our array or our array's length <= 2 (ex. 3, 2 looking for 2),
 * either case start ++ will help us exit the loop or help us find the target
 * 
 * So, next time, when see similar problems that has duplicate, we can simply skip those duplicates and let our binary search focus 
 * on subarray that does not have duplicates. 
 * 
 * Remark:
 * let p33 do this way will also work. see leetcode, I treat A[start] = A[mid] as a special case and let start ++,
 * it also pass the case tests. 
 * @author hpPlayer
 * @date Mar 20, 2015 11:19:43 PM
 */
public class p081_sol1 {
	public static void main(String[] args){
		int[] A = {1,1,1, 2, 2, 2, 3, 1, 1 ,1};
		System.out.println(new p081_sol1().search(A, 3));
	}
    public boolean search(int[] A, int target) {
    		int start = 0;
	        int end = A.length - 1;
	        while (start <= end){
	            int mid = end + (start - end) /2;
	            if(target == A[mid]){
	                return true;
	            }        
	            if(A[mid] > A[start]){//normal case in left, we can search here
	                if(A[mid] >= target && target >= A[start]){//if it is in this ordered left part
	                    end = mid -1;
	                }else{//target is in unordered part
	                    start = mid +1;
	                }    
	            }else if (A[mid] < A[start]){//left part is unordered, right part ordered. why? cuz we only rotate once, there can only one part not ordered 
	                if(A[mid] <= target && target <= A[end]){
	                    start = mid +1; 
	                }else{
	                    end = mid -1;
	                }
	            }else{//extra case, if we have duplicates or we reach the leftmost endpoint of binary case. Either way start ++ will help
	            	start ++;
	            }
	        }
	        return false;
 }
}
