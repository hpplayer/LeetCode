/*
Find Peak Element

A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ¡Ù num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -¡Þ.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Note:
Your solution should be in logarithmic complexity.
*/

/**
 * This is my original AC solution without help.
 * My intuitive solution is to scan the whole array and return the index of max value.
 * The time complexity is thus O(n)
 * but the problem requires a O(logN) solution
 * 
 * Remark:
 * Now I understand why the problem is so confusing and can be solved with binary search.
 * There may be several peaks in the array. We don't care if some peaks are high or some peaks are low. We just need to return one of
 * those peaks. Thus binary search can come to place. Please ref to sol2 with binary search
 * 
 * sol1 is my solution which is written before I fully understand this problem, also it takes O(n), so not recommended
 * sol2 is a very smart iterative binary search solution, it takes O(logN)
 * sol3 is the recursive binary search solution which is similar to sol2
 * sol4 is another linear solution, which search the peak by find the first descending part, it is very good and smart observation
 * 
 * sol2, sol3, sol4 are recommended
 * 
 * 
 * @author hpPlayer
 * @date Sep 15, 2015 5:44:23 PM
 */
public class p162_sol1 {
    public int findPeakElement(int[] nums) {
        int max = Integer.MIN_VALUE;
        int result = 0;
        for(int i = 0; i <  nums.length; i++){
            if(nums[i] >= max){
                max = nums[i];
                result = i;
            }     
        }
        
        return result;
    }
}
