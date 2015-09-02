/**
 * This solution is very similar to sol3
 * We search in a range, then update the new search range based on search result of current range
 * "rightBound" is marked as the right bound of new search range which behaves like right in sol2
 * "pointer" is the pointer that scan values in range, which behaves like the "int i" in for loop of sol2
 * 
 * The jump from current search range to next search range is one step
 * Since our algorithm will count each search range as one step, so for boundary case like [0] or [1], we 
 * need to be more careful about it. Her we detect the length of nums, if it is 0, then just return 0
 * 
 * @author hpPlayer
 * @date Sep 2, 2015 5:33:35 PM
 */
public class p045_sol3 {
    public int jump(int[] nums) {
        if(nums.length == 1) return 0;
        //oldRight is used to mark the search range
        //we will search all values in the range and found the rightmost index that we can reach
        //then let this index become the mark of new search range
        int rightBound = 0, pointer = 0, step = 0;
        
        for(int i = 0; i < nums.length; i++){
        	pointer = Math.max(pointer, nums[i] + i);//update rightmost bound
            //to skip following scans if current right already reach the tail
            if(pointer >= nums.length - 1){
                return step + 1;
            }
            //if the scanning bar has reached the oldRight, which means we have done search in current range
            //It means we need start a new jump from this range to next range
            //the right bound of next range is the rightmost index we can reach from current range
            //we just mark the right bound by the rightmost index
            if(i == rightBound){
                rightBound = pointer;
                step ++;
            }
        }
        
        return step;
    }
}
