/**
 * Almost same with sol2, except replace if-else inside for loop with a while loop
 * 
 * This while loop will swap each legal valid pair
 * This wile loop will only work if nums[i] is a legal value, and it will always work on new values
 * so this while + for loop will still solve the problem in O(n) time with constant space
 * @author hpPlayer
 * @date Aug 5, 2015 2:25:19 PM
 */
public class p041_sol3 {
	public static void main(String[] args) {
		int[] nums = { 3, 4, -1, 1 };
		System.out.println(new p041_sol3().firstMissingPositive(nums));
	}
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        for(int i = 0; i < nums.length; i++){
            while(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1 ]){
            	//System.out.println("lalal");
                //swap nums[i] and nums[nums[i] -1 ], constant space not means in-place, here we can use a temp variable
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
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
