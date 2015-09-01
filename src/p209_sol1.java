/*
Minimum Size Subarray Sum 

Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ¡Ý s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

/**
 * This is two pointer method, but in my opinion, it is more like the minimum moving window problem
 * We use two pointers to build the window
 * We will move the right edge of the window if the sum in window is smaller than sum.
 * If at some point, after we inserting a new value, the sum in window is larger than or equal the sum
 * then we can move the left edge of window to shrink the window to minimum size
 * Our scan process will end after the right edge exceed the boundary
 * To check the case that the sum of array will smaller than the given sum, we let the initial value of result to be extreme value
 * If after the scan is done, the result is still at this value, then we know this case just happened, so we simply return 0
 * 
 * 
 * Remark:
 * Two cases need to be taken care
 * 1) If at some point, the left edge meets the right edge which means current single value is >= sum, then we can simply stop
 * Since we know 1 is the minimum length of all cases and no need to continue, also if we continue, the left edge will be in front 
 * of right edge, which will cause error
 * 2) In the beginning, if left and right pointers both set to be 0, then our left and right pointer actually now form a window of size 1
 * so we need set the initial window sum to the counter, or, we can let initial right = -1, to skip this setting, see minSubArrayLen2()
 * 3) We just scan the array multiple times, so the running time is O(n)
 * @author hpPlayer
 * @date Sep 1, 2015 12:44:45 AM
 */

public class p209_sol1 {
	public static void main(String[] args){
		int nums[] = {1,2,1,4,1,1};
		System.out.println(new p209_sol1().minSubArrayLen(7, nums));
	}
    public int minSubArrayLen(int s, int[] nums) {
    	if(nums.length == 0) return 0;
        int left = 0, right = 0, sum = nums[0], result = Integer.MAX_VALUE;
        
        //we need care about the case that window size is 1
        //In our algorithm, if sum >= s and left == right, since all values are positive
        //it means our following sum will continously increase and fall into deadlock loop
        //cuz the sum is increasing and we only move left edge
        while (right < nums.length && left <= right){
            if(sum < s){
                right ++;
                if(right < nums.length)sum += nums[right];
            }else{//meet requirement, let's see if the window size can be shrinked
                result = Math.min(result, right - left + 1);
                sum -= nums[left];
                left ++;
            }
        }
        
        return result == Integer.MAX_VALUE? 0 : result;
    }
    
    //use trick to initialize settings
    public int minSubArrayLen2(int s, int[] nums) {
        int left = 0, right = -1, result = Integer.MAX_VALUE, sum = 0;
        while(right < nums.length){
            if(sum < s){
                right ++;
                if(right < nums.length) sum += nums[right]; 
            }else{
                result = Math.min(result, right - left + 1);
                sum -= nums[left];
                left ++;
            }
        }
        
        return result == Integer.MAX_VALUE? 0 : result;
    }
}
