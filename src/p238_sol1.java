import java.util.Arrays;
import java.util.HashMap;
/*
Product of Array Except Self 

Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
*/

/**
 * This is a brainstorming like problem. The best solution does not use math, recursion, dp and other common techniques.
 * sol1 is my own solution which uses recursion. It supposed to work well. In each recursion, we can get value from previous part
 * and we use recursion to get value from next part. However it will report stackoverflow problem when running on large dataset.
 * I tried to use a hashMap to reduce redundant calculation but later I found the stackoverflow problem actually does not come from
 * repeat calculation but instead coming from the depth of recursion. So this solution will not work on large dataSet
 * 
 * I searched Internet and found actually there is only one short but efficient way to solve this problem, see sol2 for this 
 * beautiful solution.
 * @author hpPlayer
 * @date Aug 9, 2015 11:19:22 PM
 */
public class p238_sol1 {
	public static void main(String[] args){
		int[] nums = {1,2,3,4	};
		int[] result = new p238_sol1().productExceptSelf(nums);
		System.out.println(Arrays.toString(result));
	}
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        DFS(0, nums, 1, result, new HashMap<Integer, Integer>());
        return result;
    }
    
    public int DFS(int index, int[] nums, int prev, int[] result, HashMap<Integer, Integer> hs){
    	if(hs.get(index) != null) return hs.get(index);
        if(index == nums.length-1){
            result[index] = prev;
            return nums[index];
        }
        int next = 1;
        if(hs.get(index+1) != null){
        	System.out.println("im here");
        	next = hs.get(index+1);
        }else{
            next = DFS(index + 1, nums, prev*nums[index], result, hs);
        }
        result[index] = prev * next;
        hs.put(index, result[index]);
        return next*nums[index];
    } 
}
