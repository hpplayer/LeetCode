/**
 * This is a very smart bit solution
 * In order to come up with this solution, we need to have two observations:
 * 1) 0 XOR any number = any number itself
 * 2) We know the ideal array has one element more than the real array.
 * Assume now our ideal and real array have same length, that means there is no missing element than, the XOR of two array
 * should return 0 as each element has its counterpart to get 0.
 * 
 * So, now we have a missing element in the real array, if we can XOR all pair of elements in two array, then each element will have its 
 * counterpart to get 0 except the missing element, we also know 0 XOR num = num itself. So this is the idea of this solution.
 * We use i to track the idea array, use num to track the real array.
 * We will start the real array with the first element in nums, while start the ideal array with 0.
 * Since our real array only has n length, while the ideal array should have n + 1 length, that means we will never reach the num n when scanning real array,
 * (it stops when our ideal array reach n-1th index) so we have to manually put num n in the comparison so that we will
 * not miss the comparison of num n. In this solution, we introduce a variable "result",  which inserts the num n into the front. Then we just start compare
 * each element in two arrays, and do it for n times
 * 
 * This is not easy to understand, here are some examples:
 * 
 * real array: [0], ideal array: 0, 1, result: 1
 * result ^= 0 get 1 then 1 ^= 0 get 1 (Notice, we can't reach 1 in idea array, but result 1 help us get that)
 * so 1 appears only once, we will return it
 * 
 * real array: [1], ideal array: 0, 1, result: 1
 * result ^= 1 get 0 then 0 ^= 0 get 0 (Notice, we can't reach 1 in idea array, but result 1 help us get that)
 * so 0 appears only once, we will return it
 * 
 * So this is the beautiful bit manipulation solution 
 * @author hpPlayer
 * @date Aug 24, 2015 2:18:26 PM
 */
public class p268_sol3 {
    public int missingNumber(int[] nums) {
        int result = nums.length; //help get the nth number to the comparsion
        int i = 0;
        for (int num : nums){
        	result ^= num;
        	result ^= i ++;
        }
        
        return result;
    }
}
