/**
 * My AC solution..without help
 * Not hard, use binary search 
 * if found, just return index
 * if not found, return start. why?
 * because at the end of loop, the start should be the index that just larger > target,
 * for example: 
 * A = 1, 3
 * target = 2,
 * after binary search, start index should at the index of 3, 
 * thus, we just need to return index of 3, which is 1 and it will be our answer
 * @author hpPlayer
 * @date Mar 20, 2015 5:42:33 PM
 */
public class p035_sol1 {
	public static void main(String[] args){
		int[] A = {1};
		int b =2;
		System.out.println(new p035_sol1().searchInsert(A, b));
	}
	   public int searchInsert(int[] A, int target) {

	        int end = A.length-1;
	        int start = 0, mid = 0;
	        while(start <= end){
	            mid = (start + end) /2;
	            if(A[mid] == target){
	                return mid;
	            }else if (A[mid] < target){
	                start = mid +1;
	            }else{
	                end = mid -1;
	            }
	        }

	        return start; 
	    }
}
