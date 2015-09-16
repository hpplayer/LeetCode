/*

Single Number III

Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/

/**
 * I have no idea about this problem, after I checked the solution, I found the solution is really brilliant.
 * First, we still use a marker to xor all numbers in array. The marker should initially set to 0, since a value xor 0 is still itself,
 * thus we will have a clean single num1 xor clean single num2. Otherwise, if we use marker ~0, then we have will have the reverse value of 
 * num xor clean single num2, which is incorrect. Anyway, we firstly XOR all elements, and we will get a value which is the result of 
 * num1 xor num2, we call it "diff"
 * 
 * Then we use a trick to get the last bit in diff that is 1. Well, we do need to have at least one 1 in the "diff" since num1 and num2 are
 * two different numbers. Ok, now we can use this new marker to split the original array. One part of the array will have 1 in this bit, and thus
 * contains the num1/num2 which has 1 in this bit. Another one will have 0 in this bit, and thus contains the num1/num2 has 0 in this bit. Since
 * Since our new marker is like 00000..000010000.0000, so the & result will be 0 as long as we do not have 1 in that bit. Thus, by & new marker,
 * we can easily split the array into two parts. Ok, now let's clarify the technique to build the new marker. Here we use: diff &= -diff.
 * -diff = ~diff + 1. We know ~diff is the reverse of diff like ~1111....1111 (32 1s) = 0, if we plus 1, then we will make the bit of last 1
 * to be 1 (which has been converted to 0 by ~diff), then the & operation will help us get this 1, and our new marker will like 0000....0010...00
 * 
 * Like I discussed above, diff &= -diff, will give us a new marker and we use it to split array to two parts. Then we just XOR numbers in two 
 * parts separately, then finally we get our two numbers. How smart it is!
 * 
 * We only use constant space and O(2n) time
 * @author hpPlayer
 * @date Sep 16, 2015 12:15:56 AM
 */
public class p260_sol1 {
    public int[] singleNumber(int[] nums) {
        int diff = 0;// to make xor num1 be num1 itself, so our final diff will be num1^num2
        for(int num : nums){
            diff ^= num;
        }
        
        //-diff is ~diff + 1, which will recover the rightmost 1 from 0 to 1, then by &diff, we will get ..00100..
        diff &= -diff;
        
        int[] result = new int[2];
        for(int num : nums){
            //since our new marker is ...0000010000.., we will get 0 as long as we don't have 1 in that bit
            if((num&diff) == 0){
                result[0] ^= num;//xor one part of array
            }else{
                result[1] ^= num;//xor another part of array
            }
        }
        
        return result;
    }
}
