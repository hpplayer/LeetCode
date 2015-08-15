/**
 * This is solution with bit manipulation
 * The idea is simple, each int is 32 bit long, so we will get each bit during the loop
 * Each loop, we will set the value at one bit, how? we scan whole array and count the total 0s and 1s
 * Since we assume there exists a majority with >n/2 occurrences, there must be a winner for 1 and 0
 * if we have more 0 in this bit, then remain result as it is, else we set bit at result to 1
 * So the total running time is O(32n) which is also O(n)
 *  
 * @author hpPlayer
 * @date Aug 14, 2015 11:25:14 PM
 */
public class p169_sol2 {
    public int majorityElement(int[] nums) {
        int result = 0;
        for(int i = 0; i < 32; i++){
            //counter total 0/1 in current bit
            int ones = 0, zeros = 0;
            for(int j = 0; j < nums.length; j++){
                //if current bit at current element is one
                if((nums[j] & (1<<i)) != 0){
                    ones ++;
                }else{
                    zeros ++;
                }
            }
            
            //we only have two cases, 0 or 1, not possible 0 = 1, since we assume we must have one element is majority with > n/2 occurences
            if(ones > zeros){
                //set one at this bit
                result |= (1<<i);
            }
        }
        
        return result;
    }
}
