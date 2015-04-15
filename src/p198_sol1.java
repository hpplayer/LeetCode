/*
House Robber 

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.
*/
/**
 * This is my original AC solution without help
 * When I see the problem is asking for the max number,
 * I immediately felt that this is a DP problem.
 * But I got a confusion when I do the problem. Here we can either take one two close element with distance of one element,
 * or we can only pick the element that gives the max number
 * Ex: 2 1 1 2, instead of picking 2, 1 or 1,2 we can actually pick 2,2 to get the max money.
 * So in my DP array, I used the formula: dp[i] = Math.max(dp[i-2] + num[i], dp[i-1]);
 * which means we can either pick two close element (dp[i-2] + num[i]), or we can skip current element, to make the max money dp[i-1]
 * In above example, the dp array would be 2 2 3 4. The second 2 in the array is the one that we chose dp[i-1], i.e. not skip current element 
 * to make sum large.
 * 
 * Since our algorithm always only use previous two values in the array,
 * we can reduce it to a solution without extra array, see sol2
 * @author hpPlayer
 * @date Apr 14, 2015 12:49:35 PM
 */
public class p198_sol1 {
    public int rob(int[] num) {
        if(num == null || num.length == 0) return 0;
         if(num.length ==1) return num[0];
         int[] dp = new int[num.length];
         dp[0] = num[0];
         dp[1] = Math.max(num[0], num[1]);
         for(int i = 2; i < num.length; i++){
            dp[i] = Math.max(dp[i-2] + num[i], dp[i-1]);
            //dp[i] = dp[i-2] + num[i];
         }
         return dp[num.length -1];
         //return Math.max(dp[num.length -2], dp[num.length-1]);
    }
}
