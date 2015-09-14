/*
Best Time to Buy and Sell Stock IV

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/**
 * Half of this solution is my original code. My algorithm works perfect for most cases, but will not pass large test case for example k is 
 * 10000000, which will gives LTE or Runtime error(exceed heap size). Based on our solution to Best Time to Buy and Sell Stock III (p123), we 
 * can simplify the problem to a one pass solution, where we calculate the second buy and sell based on first buy and sell. But it means we have
 * to create a table that has height of k, thus it will give runtime error, because a table has length of 10000000 is too large. Then our task
 * becomes optimize the solution. It seems we cannot simply use variables to record the buy and buy-sell prices since it will remove 
 * our record from previous buy/sell record at transaction j, so we still need to create a table that record information at each number
 * of transactions. So what is the optimization? well, actually it is not so hard. How large can k be? At least it will not larger than
 * the length of prices, but is that all? No, one transaction is composed of one sell and one buy. So k transactions mean the length 
 * of prices should be at least 2k length. So if k is larger than len(prices)/2, we treat the redundant k as useless transactions.
 * For those k larger than len(prices)/2, we simply treat it as Best Time to Buy and Sell Stock II (p122), where we can take as many
 * as transactions we like to gain the max profit. In that problem, we solve it by sum up all profits that larger > 0. Hence, we can 
 * deal with those large k with similar solution, which will let us create a table with appropriate size and get accepted
 * 
 * Let me explain again the idea of solving this problem.
 * This is a one pass algorithm, in each index i, we will update all transactions. 
 * Each transaction has two status: buy and sell
 * The next transction's buy value will based on current transaction's sell value since we have to sell stocks before buy it again
 * To make our calculation more convenient, we will treat each buy value or price[i] as negative, so that we can always use Math.max
 * to get the max profit. Like I said, each buy at index i is (current price at i + sell price at i-1), we want current price to be low
 * while sell price at i-1 to be high, thus the trend is not same. So, we can simply let current price be negative, then we want our 
 * negative current price to be high, which then will have same trend with sell price at i-1. Then everything become convenient.
 * 
 * In our code below, we firstly check if k is > len(prices)/2, if it is, then treat it as Best Time to Buy and Sell Stock II (p122)
 * If not, we create a dp table, where each row is doing i transactions, each row has two columns, the first column is max profit when buying
 * while its second column is max profit when selling. Then we will have a inner loop to update the table when visit each cell of prices.
 * 
 * Finally we can just return the value from last cell. The reason is that if we have any number of transactions can make the max profits
 * then all its following rows (transactions) will keep use this value. So the last cell of table will hold the global max profit of
 * all transactions 
 * 
 * I believe this is the best DP solution to this problem, no need to look up other solutions
 * @author hpPlayer
 * @date Sep 14, 2015 3:55:00 PM
 */
public class p188_sol1 {

	public static void main(String[] args) {
		int[] prices = { 2, 6, 8, 7, 8, 7, 9, 4, 1, 2, 4, 5, 8 };
		System.out.println(new p188_sol1().maxProfit(3, prices));
	}

	public int maxProfit(int k, int[] prices) {
		if (k > prices.length / 2) {
			int sum = 0;
			for (int i = 1; i < prices.length; i++) {
				if (prices[i] - prices[i - 1] > 0) {
					sum += prices[i] - prices[i - 1];
				}
			}
			return sum;
		}
		int[][] dp = new int[k][2];// first col is buy second col2 is sell
		if (dp.length < 1) return 0;

		for (int i = 0; i < dp.length; i++) dp[i][0] = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; i++) {
			dp[0][0] = Math.max(dp[0][0], -prices[i]);
			dp[0][1] = Math.max(dp[0][1], dp[0][0] + prices[i]);
			for (int j = 1; j < dp.length; j++) {
				dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] - prices[i]);
				dp[j][1] = Math.max(dp[j][1], dp[j][0] + prices[i]);
			}
		}

		return dp[dp.length - 1][1];
	}

}
