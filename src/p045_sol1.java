/*
Jump Game II

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
*/

/**
 * This is my solution will cause LTE error
 * Why?
 * In my solution, I will scan all cells within the range indicated by the value in the cell
 * then i will jump to that cell and start next scan.
 * 
 * Since we will scan the subarray in each scan, if initial values are very large, then we will scan a lot of same subarrays.
 * Like:
 * [6,5,4,3,2,1, 1,1,1,1,1]
 * For index 0, we will scan 6 cells, then found index 1 is largest
 * then we will scan index 1, we will scan 5 cells, which already been scanned in last step
 * then found index 2 is largest... 
 * 
 * Actually, when we found 5 is largest, we should directly jump to index 0 + 1 + 5, 1 is the index of largest cell, 5 is the value in that cell
 * 
 * Besides, we can't always take the largest cell and use full of the value, ex:
 * [5, 4, 1, 3, 0, 0, 0]
 * if we use index=1, and take 4 steps away
 * then we will reach index=5, which is actually 0
 * Our correct way is take index1, but use half of it to reach index[4], which gives 3,
 * then we use 3 there. So, this problem is actually not easy as it may looks like
 * 
 * In sol2 and sol3, we both search the array range by range to avoid repeat scan
 * Sol2 provides a BFS solution with is very decent
 * Sol3 uses a similar idea but different implementation
 * Both sol2 and sol3 are O(n) time complexity algorithm and O(1) space complexity
 * Sol2 is more understandable, so sol2 is more recommended
 * 
 * @author hpPlayer
 * @date Sep 2, 2015 2:50:36 PM
 */
public class p045_sol1 {
	public static void main(String[] args){
		int nums[] = {1,2,0,1};
		System.out.println(new p045_sol1().jump(nums));
	}
    public int jump(int[] nums) {
        int step = 0, index = 0;
        while(index < nums.length - 1){
        	int max = 0;
        	int i =1;
        	int move = 1;
        	for(; i <= nums[index] && (index + i) < nums.length; i++){
        		if((index + i) >= nums.length - 1) return step + 1;
        		 max = Math.max(max, nums[index + i]);
        		 if(nums[index + i] == max){
        			 move = i;
        		 }
        	}
        	//System.out.println(max);
        	//System.out.println(move);
        	//System.out.println(max + i);
            index += max + move;//1 for next index 
            step += 2;
        }
        return step;
    }
}
