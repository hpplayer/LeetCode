/**
 * Another simple approach,
 * instead of using second pointer searching backward, we let both two pointers search forward
 * one always points to the curr Index of array(give), one always points to the curr index of our final output array(virtual)
 * If count valid value, then add it to the virtual array, otherwise skip
 * This approach does not care if curr valid value already exists in virtual array, if our array only contains valid values,then 
 * it will set the value all the time, which is waste time. While my algorithm set the value only it is necessary, so maybe more efficient
 * But, this approach is more concise and simple, and no need to check precondition.(like null? or length = 0)
 * 
 * @author hpPlayer
 * @date Mar 26, 2015 1:00:31 AM
 */
public class p027_sol2 {
	public int removeElement(int[] A, int elem) {
		int index = 0;
		 for(int i = 0; i < A.length; i++ ){
			 if(A[i] != elem){
				 A[index] = A[i];
				 i++;
			 }
		 }
		 return index;
	}
}
