/*
House Robber II 

Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery
so that he will not get too much attention. This time, all houses at this place are arranged in a circle.
That means the first house is the neighbor of the last one.
Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.
*/

/**
 * This is my original AC solution
 * Basically, I use the same idea in p198 House Robber. But now I create 4 dp arrays
 * Since now we have a circle in the street. We have 4 ways to go:
 * Start from head->...->tail
 * Start from head->tail->...
 * Start from tail->..->head
 * Start from tail->head->...
 * 
 * Since head and tail are connected, we have only nums.length-1 houses that are under consideration (1 house less due to this connection)
 * 
 * I firstly create 4 loops to mimic these 4 ways, got accepted,
 * Then I combine 4 loops into one loop to make it shorter
 * This approach may not look so nice and a little bit space consuming.
 * But it is my solution. I am proud of it!
 * 
 * A simpler and less space-consuming solution can be found in sol2
 * 
 * Remark:
 * Sol1 has a good time complexity not good space complexity(Actually, it can be optimized to replace array with variables) 
 * Sol2 has a good space complexity not good time complexity (cannot be futher optimized)
 * @author hpPlayer
 * @date Aug 6, 2015 10:09:28 PM
 */

public class p213_sol1 {
	public static void main(String[] args){
		int[] a = {1,1,3,6,7,10,7,1,8,5,9,1,4,4,3};//expect: 41
		int[] b = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};//expect: 16
		int[] c = {2,1,1,2};//expect 3
		int[] d = {1,1,1,2};//expect 3
		System.out.println(new p213_sol1().rob(b));
	}
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1 ) return nums[0];
        if (nums.length == 2 ) return Math.max(nums[0], nums[1]);
        
        int[] dp = new int[nums.length-1];//except the last 
        int[] dp2 = new int[nums.length-1];//reverse, except the first
        int[] dp3 = new int[nums.length-1];
        int[] dp4 = new int[nums.length-1];
        
        dp[0] = nums[0];
        dp2[0] = nums[nums.length - 1];
        dp3[0] = nums[0];
        dp4[0] = nums[nums.length - 1];
        		
        dp[1] = Math.max(nums[0], nums[1]);
        dp2[1] = Math.max(nums[nums.length -1], nums[nums.length - 2]);
        dp3[1] = Math.max(nums[0], nums[nums.length-1]); 
        dp4[1] = Math.max(nums[nums.length -1], nums[0]); 
        		
        for(int i = 2; i < nums.length -1; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
            dp2[i] = Math.max(dp2[i-2] + nums[nums.length - i -1], dp2[i-1]);
            dp3[i] = Math.max(dp3[i-2] + nums[nums.length - i], dp3[i-1]);
            dp4[i] = Math.max(dp4[i-2] + nums[i-1], dp4[i-1]);
        }
        
        /*
        for(int i = nums.length - 3; i > 0; i--){
        	int j = nums.length  - i -1;
        	dp2[j] = Math.max(dp2[j-2] + nums[i], dp2[j]);  
        }
        
        for(int i = nums.length - 2; i > 1; i-- ){
        	int j = nums.length - i;
        	dp3[j] = Math.max(dp3[j] + nums[i], dp3[j]);
        }
        /*
        for(int i = 1; i < nums.length -2; i++){
        	dp4[i+1] = Math.max(dp4[i-1] + nums[i], dp4[i]);
        }
       */
        //System.out.println(Arrays.toString(dp));
       // System.out.println(Arrays.toString(dp2));
        
        int max1 = Math.max(dp[dp.length -1 ], dp2[dp.length-1]);
        int max2 = Math.max(dp3[dp.length -1 ], dp4[dp.length-1]);
        return Math.max(max1, max2);
        
    }
}
