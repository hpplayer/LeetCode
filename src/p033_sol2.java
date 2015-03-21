/**
 * A better solution using solely binary search
 * Basic idea:
 * From observation, we found any cut in a rotated will produce two kinds of subarray
 * one is sorted and the other one is unsorted.
 * So if we found target in within the sorted range, we just simply do normal binary search in this subarray, otherwise 
 * we have to search the other subarray (our next cut in that subarray will also produce such two subpart), so our binary search
 * will finally help us find the ideal target.
 * Compared with previous solution, this one is more understandable, clear and easy-to-analysis.
 * The behavior here is like we are only doing a normal binary search, but we guide search more specifically to each case(guide way between
 * sorted and unsorted subarray)  
 * 
 * @author hpPlayer
 * @date Mar 20, 2015 10:23:34 PM
 */
public class p033_sol2 {
	 public int search(int[] A, int target) {
	        int start = 0;
	        int end = A.length - 1;
	        while (start <= end){
	            int mid = end + (start - end) /2;
	            if(target == A[mid]){
	                return mid;
	            }
	            //what is A[mid] == A[start]? we dont duplicate here, A[mid] == A[start] means we have reach the leftmost end of this search
	            //since target != A[mid], we have to search right part.
	            if(A[mid] > A[start]){//normal case in left, we can search here
	                if(A[mid] >= target && target >= A[start]){//if it is in this ordered left part
	                    end = mid -1;
	                }else{//target is in unordered part
	                    start = mid +1;
	                }    
	            }else{//left part is unordered, right part ordered. why? cuz we only rotate once, there can only one part not ordered 
	                if(A[mid] <= target && target <= A[end]){
	                    start = mid +1; 
	                }else{
	                    end = mid -1;
	                }
	            }
	        }
	        return -1;
	    }
}
