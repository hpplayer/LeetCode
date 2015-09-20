/*
Two Sum II - Input array is sorted

Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

/**
 * If we still use HashMap to solve this problem, then our running time is O(n), but we have not used the information of ascending order
 * If we use binary search, then the running time will be O(nlogn), which is even slower
 * 
 * So the correct solution is to use two pointers.
 * Initially, we let start pointer points to the smallest number in array while we let the end pointer points to the largest number in array
 * if the sum is target, then we return two indexes
 * if the sum is smaller than target, that means we need a larger number, which could only be achieved by moving start pointer
 * if the sum is larger than target, that means we need a smaller number, which could only be achieved by moving end pointer
 * 
 * Thus after we done the O(n) search, we are guaranteed to find the pair of numbers
 * This solution only uses O(n) time and O(1) space, so it is the best way to solve this problem
 * 
 * @author hpPlayer
 * @date Sep 19, 2015 11:25:06 PM
 */
public class p167_sol1 {
    public int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length -1;
        //while we still have two numbers left
        while(start < end){
            if(numbers[start] + numbers[end] == target){
                return new int[]{start + 1, end + 1};
            }else if(numbers[start] + numbers[end] < target){
                start ++;//sum too small, increase start
            }else{
                end --;
            }
        }
        
        return null;//couldn't find one
    }
}
