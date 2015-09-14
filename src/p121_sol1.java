/*
Best Time to Buy and Sell Stock


Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.

*/

/**
 * This is my original AC solution without help.
 * My idea is simple, record the min price of past days, and compare every day later and return the max difference.
 * But we can think this problem in a more DP way, please ref to sol2
 * 
 * Sol1 is more easy understanding
 * Sol2 is a DP way with more mathematical knowledge, which can gives us a deeper understanding
 * 
 * So both are recommended
 * @author hpPlayer
 * @date Sep 13, 2015 9:00:02 PM
 */
public class p121_sol1 {
    public int maxProfit(int[] prices) {
        int result = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < prices.length; i++){
            min = Math.min(min, prices[i]);
            result = Math.max(result, prices[i] - min);
        }
        return result;
    }
}
