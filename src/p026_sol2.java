/**
 * Only count 
 * @author hpPlayer
 * @date Mar 26, 2015 3:14:08 PM
 */
public class p026_sol2 {
	public static void main(String[] args) {
		int[] arr = { 1,1,2 };
		int size = removeDuplicates(arr);
		System.out.println(size);
	}
    public static int removeDuplicates(int[] A) {
    	int count = 0;
    	for(int i = 1; i < A.length; i++){
    		if(A[i] == A[i -1] ){
    			count ++;
    		}else{
    			A[i-count] = A[i];
    		}
    	}
    	return A.length - count;
    }
}
