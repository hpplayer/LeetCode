import java.util.Arrays;
/* Next Permutation 
 * 
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 ¡ú 1,3,2
3,2,1 ¡ú 1,2,3
1,1,5 ¡ú 1,5,1
 * 
 */

/**
 * This is my original solution, but it is not the correct way to solve this problem
 * I only consider swap digits if I found a large and small digits pair, but I have not considered this algorithm will not cover all cases.
 * For example:
 * It will work on simple case like 1,2,3 -> 1,3,2
 * It will not work on case like 2,3,1 -> 3,1,2
 * 
 * Actually, there is a famous algorithm toward generating permutation in lexicographic order. The name is Narayana Pandita Algorithm
 * See sol2 for more details
 * 
 * @author hpPlayer
 * @date Jul 31, 2015 11:37:22 PM
 */

public class p031_sol1 {
	public static void main(String[] args){
		p031_sol1 inst = new p031_sol1();
		int[] test1 = {1,3,2};
		int[] test1_s = {2,1,3};
		int[] test2 = {1,2,3};
		int[] test2_s = {1,3,2};
		int[] test3 = {1,1,5};
		int[] test3_s = {1,5,1};
		int[] test4 = {2,3,1};
		int[] test4_s = {3,1,2};
		inst.nextPermutation(test1);
		inst.nextPermutation(test2);
		inst.nextPermutation(test3);
		inst.nextPermutation(test4);
		System.out.println(Arrays.toString(test1).equals(Arrays.toString(test1_s)));
		//System.out.println(Arrays.toString(test2));
		//System.out.println(Arrays.toString(test2_s));
		System.out.println(Arrays.toString(test2).equals(Arrays.toString(test2_s)));
		System.out.println(Arrays.toString(test3).equals(Arrays.toString(test3_s)));
		System.out.println(Arrays.toString(test4).equals(Arrays.toString(test4_s)));
	}
	
    public void nextPermutation(int[] nums) {
        if(!swappable(0, nums)){
            Arrays.sort(nums);
        }
    }
    
    public boolean swappable(int i, int[] nums){
        if(i == nums.length - 1) return false;
        if(swappable(i+1, nums)) return true;
        if(nums[i] < nums[i+1]){
            nums[i] += nums[i+1];
            nums[i+1] = nums[i] - nums[i+1];
            nums[i] -= nums[i+1];
            return true;
        }{
            nums[i] += nums[i+1];
            nums[i+1] = nums[i] - nums[i+1];
            nums[i] -= nums[i+1];
            return false;
        }
    }
}
