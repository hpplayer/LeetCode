import java.util.HashSet;

/**
 * This solution uses the similar idea as detect cycle in linkedlist
 * We use a fast pointer and a slow pointer, the faster pointer moves two time faster than slow pointer
 * If we have a cycle in the sequence, then our two pointers will finally meet
 * When they meet, we just check the value when they meet, if it is 1, then return happy number
 * 
 * Again, here we assume that we know the unhappy number will finally fall into a loop, which is proven by exhaustive search as described on
 * wiki
 * 
 * 
 * Let me try to repeat the findings in wiki
 * Firstly, assume current number is n, it has m digits
 * So, the maximum happy sum for m digits will be 9^2 * m
 * 
 * We found any n larger than 1000 (four digits, m = 4) will have that
 * n >= 10^(m-1) Notice: 10^(m-1) is the smallest n of m digits
 * and we also found that 10^(m-1) > 81 m for four digits.
 * So, we have n >= 81m, in other words, calculate the happy number of n will decrease n after m > 4
 * 
 * Similarly, when m == 3, the largest happy number is 81*3 = 243
 * So any n larger than 243, will finally converge to a number below 243.
 * Findings below are from exhaustive search:
 * Still when m == 3, for the range [100, 243], 199 produces the largest happy number, which is 163
 * Still when m == 3, for the range [100, 163], 159 produces the largest happy number, which is 107
 * Still when m== 3, for the range [100, 107], 107 produces the largest happy number, which is 50
 * Finally our number n converges to a number smaller than 50
 * In other words, any number larger than 100 (inclusive) will get strictly smaller during the calculation.
 * 
 * Then quote from WIKI:
 * an exhaustive search then shows that every number in the interval [1,99] either is happy or goes to 4, 16, 37, 58, 89, 145, 42, 20, 4...
 * 
 * So, there must be a loop in unhappy number.
 * 
 * @author hpPlayer
 * @date Sep 1, 2015 9:11:50 PM
 */
public class p202_sol2 {
	   public boolean isHappy(int n) {
	        int slow = n, fast = n;
	        do{
	          slow = helper(slow);
	          fast = helper(helper(fast));//moves two times faster than slow
	        }while(slow != fast);
	        
	        return slow == 1;//of course we can also return fast == 1, as now slow == fast
	    }

	    
	    public int helper(int n){
	        int sum = 0;
	        while(n > 0){
	            sum += (int) Math.pow(n%10, 2);
	            n /= 10;
	        }
	        
	        return sum;
	    }
}
