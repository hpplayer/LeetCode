/**
 * My AC solution.. Test cases for this problem is not so strict, 
 * Do naive O(n) search can also pass the test 
 * When I was doing this problem, I was also thinking of search in the sorted part, 
 * But I lost some details, so my algorithm should be similar to O(n), and not so understandable as other's answer.
 * More detailed and better solution please ref to sol2
 * @author hpPlayer
 * @date Mar 20, 2015 9:47:00 PM
 */
public class p033_sol1 {
	  public static void main(String[] args){
		  int ary[] = {3,1};
		  System.out.println(new p033_sol1().search(ary, 1));
	  }
	
	  public int search(int[] A, int target) {
	       int start = 0;
	       int end = A.length - 1;
	       return search(start, end, A, target );
	    }
	  public int search(int left, int right, int[] A, int target){
	        if(left > right) return -1;
	        int result = Integer.MIN_VALUE;
	        int mid  = (left+right)/2;
	        
	        if (A[mid] == target) return mid;
	       result = Math.max(search(left, mid-1, A, target), result);
	        if(result != -1) return result;
	       result = Math.max(search(mid+1, right, A, target), result);
	        
	        return result;
	  }
	    public int search2(int left, int right, int[] A, int target){
	        if(left > right) return -1;
	        int result = Integer.MIN_VALUE;
	        int mid  = (left+right)/2;
	        
	        if (A[mid] == target) return mid;
	        if(A[mid] > A[left] && A[mid] > target){
	        	//System.out.println("im here");
	            result = Math.max(search(left, mid-1, A, target), result);
	        }
	        
	        if(A[mid] <= A[left]){
	        	//System.out.println("im here");
	            result = Math.max(search(left, mid-1, A, target), result);
	        }
	        
	        if(A[mid] < A[right] && A[mid] < target){
	        	//System.out.println("im here");
	            result = Math.max(search(mid+1, right, A, target), result);
	        }
	        
	        if(A[mid] >= A[left]){
	        	//System.out.println("im here");
	            result = Math.max(search(mid+1, right, A, target), result);
	        }
	        
	        return result;
	    }
}
