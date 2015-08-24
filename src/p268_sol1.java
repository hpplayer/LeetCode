/**
 * This is my solution. Just one step away from the AC solution!
 * I used to think use bit manipulation to get the expected sum of array and minus the real sum of array, then we will get the solution
 * But I found it is not easy to mimic binary sum here, So instead I just use decimal summation.
 * But I made a mistake here that I incorrectly summing the expect sum by using for loop from 0 to nums.length (exclusively), then, this is like
 * we are summing from 0 to n-1, so I always got error
 * 
 * In other words, the expected sum actually include n + 1 elements from 0 to n, so our first loop should loop n +1 times
 * 
 * Thus the correction is simply change < to <=, then we are done
 * If the sum1 = 0 + 1 + 2, sum2 = 1+2, then we know sum1 - sum2 = 0, 0 is the missing value
 * if the sum1 = 0 + 1 + 2, sum2 = 0 + 2, then we know sum1 - sum2 = 1, 1 is the missing value
 * 
 * Sol2 uses a math technique to calculate the sum. This technique eliminates the need for first for loop, so should be faster
 * Sol3 is the bit manipulation solution
 * Sol4 is the python version of sol1
 * Sol5 is the python version of sol2
 * Sol6 is the python version of sol3
 * 
 * Time complexity of sol1,2,3 are all O(n)
 * Space complexity of sol1,2,3 are all O(1)
 * 
 * Remark:
 * Since this problem is just posted, bit manipulation solution will be updated soon!
 * 
 * Update:
 * Bit manipulation solution is updated as sol3
 * 
 * Both the mathematic and bit manipulating solution are very appealing solutions, both are recommended!
 * 
 * @author hpPlayer
 * @date Aug 23, 2015 5:14:20 PM
 */
public class p268_sol1 {
    public int missingNumber(int[] nums) {
        int sum = 0, sum2 = 0;
        //What I missed is simply i <= nums.length (I used to put i < nums.length, since nums.length = n, my incorrect version will always get one n-1 less)
        for(int i = 0; i <= nums.length; i++){
            sum += i;
        }
        
        for(int i = 0; i < nums.length; i++){
            sum2 += nums[i];
        }
        
        return sum - sum2;
    }
}
