/**
 * This algorithm works in the following way:
 * We keep a search range, each search range will not overlap. (we will let new left bound = old right bound + 1)
 * We will only search values in the range for once and find the max value, and the max value will become our new right bound
 * Why we say this is a BFS search?
 * Because, each while loop, we will look at one search range, then we found a new search range that can be reached from this search
 * range.
 * The jump from current search range to next search range is one jump
 * The number of jumps among search range is the solution we want
 * 
 * The running time is O(n) and we don't need extra space, i.e. O(1) space complexity
 * 
 * Remark:
 * Jump() below is exactly the algorithm I described above
 * However, it can be simplified to remove left bound, since our for loop in the while() will automatically move left bound
 * jump2() illustrated the simplified solution
 * 
 * @author hpPlayer
 * @date Sep 2, 2015 4:52:37 PM
 */
public class p045_sol2 {
	
	public static void main(String[] args){
		int nums[] = {1,0};
		System.out.println(new p045_sol2().jump(nums));
	}
    public int jump(int[] nums) {
        int left = 0, right = 0, step = 0;
        while(right < nums.length -1){// if initial right is already in the target tail, we don't have to enter the loop
            int newRight = right + 1;//our new right boundary will be at least one step further
            for(int i = left; i <= right; i++){
            	//Removing following if can still work
            	//If here is just let us stop early, if at some points we can reach tail with one jump
            	//thus we don't need to search nodes behind
                if((i+ nums[i]) >= nums.length -1 )  return step + 1;
                newRight = Math.max(newRight, i+nums[i]);//update newRight to be the rightmost index
            }
            
            left = right + 1;//we move left bound to be the one after old right + 1
            right = newRight;//we move right bound to be the rightmost new index
            step ++;//we have done one jump, let step ++
        }
        return step;
    }
    
    
    public int jump2(int[] nums) {
        int i = 0, right = 0, step = 0;
        while(right < nums.length -1){// if initial right is already in the target tail, we don't have to enter the loop
            int newRight = right + 1;//our new right boundary will be at least one step further
            for(; i <= right; i++){
                //if((i+ nums[i]) >= nums.length -1 )  return step + 1;//we can reach tail with one jump
                newRight = Math.max(newRight, i+nums[i]);//update newRight to be the rightmost index
            }
            
            right = newRight;//we move right bound to be the rightmost new index
            step ++;//we have done one jump, let step ++
        }
        return step;
    }
}
