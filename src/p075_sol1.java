/*
Sort Colors 

Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
*/	

/**
 * Ok, my algorithm is the rather straight forward two-pass algorithm
 * I firstly scan the array and record the number of occurrences of 0 and 1
 * Then in my second scan of the array, I will first assign 0s based on number of occurrences of 0
 * Then begin assign 1s  based on number of occurrences of 1, lastly, assign remaining cells to 2
 * No special technique.
 * 
 * Time complexity: O(2n)
 * 
 * Sol2 list a better one-pass solution, which use two pointers.
 * 
 * Remark:
 * Actually, this problem is very similar to "Dutch national flag" problem, which can be found in attached wiki pdf 
 * 
 * @author hpPlayer
 * @date Sep 7, 2015 3:44:38 PM
 */
public class p075_sol1 {
    public void sortColors(int[] nums) {
        int zeros = 0;
        int ones = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                zeros ++;
            }else if (nums[i] == 1){
                ones ++;
            }
        }
        
        for(int i = 0; i < nums.length; i++){
            if(zeros > 0){
                nums[i] = 0;
                zeros--;
            }else{
                if(ones > 0){
                    nums[i] = 1;
                    ones--;
                }else{
                    nums[i] = 2;
                }
            }
        }
    }
}
