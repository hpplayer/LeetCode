/*
Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

/**
 * My original AC solution without help. I use a pointer to flag the place that we can insert new elements. The index is firstly initialized to 0,
 * but nums[0] may be zero or not, so when we need to swap a non-zero element with pointer, we have to check if the pointer does point to a zero.
 * If not, we will simply let pointer move forward and bring it to next possible index. However, if the pointer points to a zero, then we need to
 * swap them, then let pointer move forward to next possible index. If current visiting value is 0, then we do nothing, since we will only swap
 * when we have a pair of zero and non-zero
 * 
 * Remark:
 * Since it is a new question, there may be other fancy solutions but have not posted yet. Stay tuned!
 * @author hpPlayer
 * @date Sep 21, 2015 9:58:28 PM
 */
public class p283_sol1 {
    public void moveZeroes(int[] nums) {
        int index = 0;//place to insert non-zero element, the index of first 0 after non-zero sequence
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                //if found an non-zero element
                if(nums[index] == 0){//if we do have zero that needs to be swapped
                    //if we do need to insert it front
                    nums[index] = nums[i];
                    nums[i] = 0;
                }
                index ++;//move to next index
            }
        }
    }
}
