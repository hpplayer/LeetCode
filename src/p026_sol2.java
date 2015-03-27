/**
 * This algorithm counts the number of duplicate
 * so the length of new non-duplicate ary must have length of A.length - count
 * When we found a new non duplicate value, we just assign it to the tail of non duplicate array
 * Very smart
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
    			count ++;//count the num of duplicates
    		}else{
    			A[i-count] = A[i];//return to the tail of current non duplicate part
    		}
    	}
    	return A.length - count;
    }
}
