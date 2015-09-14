/*
Best Time to Buy and Sell Stock II

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times).

 However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/**
 * This is my original AC solution without help
 * As indicated by the tag, we can solve it by greedy algorithm.
 * We only include transactions that making positive profits. 
 * My algorithm is using a variable "currMax" to record the accumulative profits we earn so far, if selling at current price is still making profit
 * we will add it into our current transaction. if not, we will stop even the total profit of transaction is still positive, we will end current
 * transaction, add it into our result and start a new transaction 
 * 
 * Remark:
 * Be careful with the boundary case, the stock profit at last day. Normally we will add transaction to result if we start a new transaction,
 * but our loop will stop here, so we have to add the profit from last transaction manually if the last day still makes profit
 * 
 * Actually, the problem is easier than I think, ref to sol2 for a super easy solution
 * 
 * Sol2 is more recommended, as my sol1 may think the problem in a complicate way, the problem is actually not so hard
 * @author hpPlayer
 * @date Sep 13, 2015 10:04:31 PM
 */
public class p122_sol1 {
    public int maxProfit(int[] prices) {
        if(prices.length < 2) return 0;
        int currMax = 0;
        int result = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] >= prices[i-1]){
                currMax += prices[i] - prices[i-1];
                if(i == prices.length - 1) result += currMax;
            }else{
                result += currMax;
                currMax = 0;
            }
        }
        
        
        return result;
    }
}
