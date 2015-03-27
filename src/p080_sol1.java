/**
 * My AC solution without help
 * 
 * Basically, when we read the array, we use two pointers, one points to the tail of current virtual array, one points to the current
 * position in real array, when we found duplicates, we move the real pointer forward, until the last index of these types of duplicates 
 * if count == 0 means we don't have duplicate, count >=1 means we have duplicates, no matter how many duplicates it has, we treat
 * them as only two duplicates and add to our virtual array. Since our real array pointer should be in the last index of duplicates
 * so, we can simply let it move forward and let our loop continue;
 * 
 * Remark:
 * When we are done with different cases, remember to set the counter to 0
 * 
 * This algorithm is designed by myself
 * To use another approach that is similar to p26, please ref to sol2
 * @author hpPlayer
 * @date Mar 26, 2015 6:19:58 PM
 */
public class p080_sol1 {
	public static void main(String[] args){
		int[] A = {1,1,1};
		System.out.println(removeDuplicates(A));
	}
	 public static int removeDuplicates(int[] A) {
	        if(A.length <=2) return A.length;
	        int index = 0, count = 0, i = 0;//i: real index, index: virtual index, count: num of duplicates 
	        while(i < A.length){
	        	while(i < A.length-1 && A[i+1] == A[i]){
	        		count ++;
	        		i++;
	        	}
	        	if(count == 0){
	        		A[index] = A[i];
	        		index++;
	        		count = 0;
	        	}
	        	if(count >= 1){
	           		A[index] = A[i];
	        		A[index+1] = A[i];
	        		index +=2;
	        		count = 0;
	        	}
	        	i++;
	        	
	        }
	        return index;	
	    }
	 
}
