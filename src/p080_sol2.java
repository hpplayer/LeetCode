/**
 * This algorithm works in following way:
 * It still uses two pointer approach
 * We check the value of last second and compared with current value, if they are same meaning current value is the 3rd repeated value 
 * we need skip and continue our loop.Otherwise, if it is not, then we can simply add it to our virtual array
 * 
 * Remark:
 * Because we need check index -1 value in our array, which may empty in start, we have to force our virtual array contains initial two 
 * values, so the main loop start with 3rd bit in array
 * if having question of what values should be return, index ? index +1 ? index -1?
 * A good suggestion is to draw example and find out what values need to be return with real example
 * (ex. [1,1,2], in the initial case, we have 1,1 and index =2, then we include 2 into virtual array, index = 3, which is same as
 * the length)
 * @author hpPlayer
 * @date Mar 26, 2015 6:19:54 PM
 */
public class p080_sol2 {
	public static void main(String[] args){
		int[] A = {1,1,2};
		
		System.out.println(removeDuplicates(A));
	}
    public static int removeDuplicates(int[] A) {
            if(A.length <=2 ) return A.length;
            int index = 2;//points to the last bit in the index, we set inital value to 1, so we would be ok with the inital two indexes
            for(int i = 2; i < A.length; i++){
                if(A[index-2] == A[i]) continue;
                //we need insert it into our virtual array
                A[index] = A[i];
                index++;
            }
            //think about the initial case that we only have two values, but index =1, so index is 1 less than our size
            return index;
    }
}
