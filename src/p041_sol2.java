import java.util.Arrays;
/**
 * This is a very very clever approach towards this problem. It is also O(n) and constant space approach
 * It uses the help from index
 * we rearrange the values in array so that the value in each cell is index + 1
 * During the arrangement, we will check if value in each cell is i + 1, if not:
 * 1)If we found the value is illegal like <= 0 (not positive), > nums.length (it is larger than our expected range)
 * or nums[i] == nums[nums[i] - 1 ] ( we got duplicates)
 * we simply skip it
 * 2)If it is legal, then we swap nums[i] and nums[nums[i] - 1]] (nums[nums[i]-1] should store value nums[i]-1 + 1, which is nums[i])
 * After swap, we let loop recheck current value by using i --; 
 * 
 * After we do this check-and-swap do whole array, we rescan the array, the first value that is match with index + 1, is our result
 * If whole result is ok, then we return length + 1, since our last value in array should be length
 * 
 * For example
 * [3, 4, -1, 1]
 * 
 * i = 0,
 * nums[0] = 3 is not 0 + 1, and 3 is legal value, so we swap nums[0] with nums[nums[0] - 1], i.e. swap nums[0] with nums[2]
 * now it becomes [-1, 4, 3, 1], since we have done a swap, we let i --
 * 
 * i = 0,
 * nums[0] = -1 is not 0 + 1, and -1 is illegal value, so we just let loop continue
 * 
 * i = 1,
 * nums[1] = 4 is not 1 + 1 and 4 is legal value, so we swap num[1] with nums[nums[1] -1], i.e. swap nums[1] with nums[3]
 * now it becomes[-1, 1, 3, 4], since we have done a swap, we let i --
 * 
 * i = 1,
 * nums[1] =  1 is not 1 + 1 and 1 is legal value, so we swap nums[1] with nums[nums[1] -1], i.e. swap nums[1] with nums[0]
 * now it becomes [1, -1, 3, 4], since we have done a swap, we let i--
 * 
 * i = 1,
 * nums[1] = -1 is not 0 + 1, and -1 is illegal value, so we just let loop continue
 * 
 * i = 2,
 * nums[2] = 3 is 2 + 1, loop continue
 * 
 * i = 3,
 * nums[3] = 4 is 3 + 1, loop continue
 * 
 * loop finish
 * 
 * 
 * Rescan array, we found when i = 1, the value in it is match with i + 1, so we return i + 1, which is 2
 * 
 * Remark:
 * 
 * When we do the swap, since we will change the value in nums[i] but we also need change value in nums[nums[i]-1],
 * we have to save the value in nums[i], then replace nums[i]-1 in nums[nums[i]-1] with this stored value, otherwise 
 * our nums[i] will change. Also the problem only requires constant space not in-place, so use this extra temp value 
 * is allowed
 * 
 * sol3 uses the same way but uses a while loop inside for loop to do every legal swap (make code shorter)
 * @author hpPlayer
 * @date Aug 5, 2015 1:36:26 PM
 */

public class p041_sol2 {
	public static void main(String[] args){
		int[] nums = {2, 1};
		System.out.println(new p041_sol2().firstMissingPositive(nums));
	}
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        for(int i = 0; i < nums.length; i++){
        	//System.out.println(Arrays.toString(nums));
            if(i + 1 != nums[i]){
                if(nums[i] <= 0 || nums[i] > nums.length || nums[i] == nums[nums[i] - 1 ]){
                    continue;
                }else{
                    //swap nums[i] and nums[nums[i] -1 ], constant space not means in-place, here we can use a temp variable
                    int temp = nums[i];
                    nums[i] = nums[nums[i] - 1];
                    nums[temp - 1] = temp;
                    i--;//let i stay still and check again
                }
            }
        }
        
        
        for(int i = 0; i < nums.length; i++){
            if (i + 1 != nums[i]){
                return i+1;
            }
        }
        //since nums[nums.length -1]'s value should be nums.length, if we reach here, then we just need to simply return nums.length + 1
        return nums.length + 1;
    }
}
