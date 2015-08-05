import java.util.Arrays;

/*
First Missing Positive

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

/**
 * This is my original AC solution without help
 * It uses O(nlogn) time and constant space, so it does not meet the requirement, but it is an AC solution anyway
 * The idea here is simple, I uses a variable P to track the change of positive number and a variable nonP to track the change of 
 * negative number. If we found nums[i] = nums[i-1] just let p --. If we found p is not consistent with nums[i], we just return p, and 
 * it will be the smallest positive number that we miss
 * More fantastic solutions come tomorrow
 * 
 * @author hpPlayer
 * @date Aug 5, 2015 12:45:27 AM
 */
public class p041_sol1 {
	public static void main(String[] args) {
		int[] nums = { 3 };
		System.out.println(new p041_sol1().firstMissingPositive(nums));
	}

	public int firstMissingPositive(int[] nums) {
		if (nums == null || nums.length < 1)
			return 1;
		Arrays.sort(nums);
		int nonP = 0;
		int p = 1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= 0) {
				nonP++;
				continue;
			} else {
				if (i > 0 && nums[i] == nums[i - 1]) {
					p--;
				}
				if (nums[i] != p) {
					return p;
				}
				p++;
			}
		}

		//System.out.println(nonP);
		if (nonP >= 0) {
			return nums[nums.length - 1] + 1;
		} else {
			return 1;
		}

	}
}
