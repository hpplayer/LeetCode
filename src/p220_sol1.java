import java.util.TreeSet;
/*
Contains Duplicate III 

Given an array of integers, find out whether there are two distinct indices i and j in the array such that
the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/
/**
 * This is an AC solution that may not be good to use in real interview, since all tricky we do is just using the built-in 
 * TreeSet in the java and we skip the hard part
 * 
 * There are two key ideas in this solution
 * 1) we use TreeSet(implemented with binary tree) to store values which will speed the search time
 * The binary tree structure here is very important since it will reduce the search/remove/add time to log(k),
 * the total running time should be nlog(k)
 * 
 * 2) Don't worry about the duplicates, because the problem asks us to find the pair that has at most t difference
 * so if we got duplicates:
 * i. if they are in the range k, then we should find them and return true
 * ii. if they are not in the range k, then we are free to remove any value in the set 
 * 
 * Remark:
 * we may get overflow problem if nums[i] + t > Integer.MAX_VALUE
 * so her we convert all int type to long type with cast
 * 
 * sol2 uses a math way called bucketing
 * sol3 is the python version of sol2
 * 
 * @author hpPlayer
 * @date Aug 7, 2015 11:32:03 PM
 */

public class p220_sol1 {
	
	public static void main(String[] args){
		int[] nums = {1,1,3,};
		int k = 1;
		int t = 1;
		System.out.println(new p220_sol1().containsNearbyAlmostDuplicate(nums, k, t));
	}
	
	
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		TreeSet<Long> set = new TreeSet<Long>();
		for(int i = 0; i < nums.length; i++){
			if (i > k){
				set.remove((long) nums[i-k-1]);
			}
			
			if(set.floor((long) nums[i]) != null && set.floor((long) nums[i]) + t >= nums[i] ||
			set.ceiling((long) nums[i]) != null && set.ceiling((long) nums[i]) -t <= nums[i]){
				return true;
			}
			
			set.add((long) nums[i]);
		}
		return false;	
	}
}
