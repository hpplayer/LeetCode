import java.util.Arrays;
/**
 * This algorithm is awesome! It purely uses the binary search
 * Basic idea:
 * In binary search, we have two pointer start and end. In the end of normal binary search, if we could not find target, 
 * the start pointer points to the value that just behind the target, and the end points points to the value that just in front of 
 * the target(check binarySearch class in test package for running result)
 * 
 * 
 * This problem contains duplicate target values, so we need force those two pointers move, 
 * after several iterations, we will reach the leftmost and rightmost target value, after one more iteration,
 * our start pointer will points to the next index of rightmost target value while our end pointer will points 
 * to the prev index of leftmost target value. 
 * 
 * Because we need iteratively search the left and right part of target value, we should have two 
 * Separate search() or at least two loop to search the first and next pointers.
 * One is used to find the leftmost target value and start pointer while another one is used to find the rightmost target value and start pointer
 * @author hpPlayer
 * @date Mar 20, 2015 6:44:39 PM
 */

public class p034_sol2 {
	public static void main(String[] args){
		int[] A = {};
		int target = 1;
		System.out.println(Arrays.toString(new p034_sol2().searchRange(A, target)));
	}
    public int[] searchRange(int[] A, int target) {
    
        int left = searchLeft(A, target);
    	System.out.println(left);
        int right = searchRight(A, target);
    	System.out.println(right);
        int[] result = new int[2];
        if(left +1 ==  right ){//this means the N2 is just behind N1, we dont have element between, which means target is not in the array
        	result[0] = result[1] =  -1;
        }else{
        	result[0] = left +1;
        	result[1] = right -1;
        }
    	return result;
    }
    
    //big means A[mid] > target, we need look left part
    public int searchLeft(int[] A, int target){
    	int start = 0;
    	int end = A.length -1;
    	while( start <= end){//we will force end become the index of the last element that is smaller than target, we call it N1
    		int mid = (start + end) /2;
    		//we treat target as the bigger case, and we will return end
    		if(A[mid] < target){//small case behave normal 
    			start = mid +1;//we do normal search on the right
    		}else{//big case and equal case both treat as big case
    			end = mid -1;//left search includes equal case
    		}
    	}
    	return end;
    }
    //small means A[mid] < target, we need look right part
    public int searchRight(int[] A, int target){
    	int start = 0;
    	int end = A.length -1;
    	while( start <= end){//we will force end become the index of the first element that is larger than target, we call it N2
    		int mid = (start + end) /2;
    		//we treat target as the small case, and we will return start
    		if(A[mid] > target){//big case behave normal 
    			end = mid -1;//we do normal search on left
    		}else{//small case and equal case both treat as big case
    			start = mid +1;//right search includes equal case
    		}
    	}
    	return start;
    }
    
}
