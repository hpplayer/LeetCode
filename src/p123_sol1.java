/*
Best Time to Buy and Sell Stock III

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/**
 * Still not very clear about this problem, need to be updated tomorrow
 * 
 * Update:
 * This a two pass DP solution. Well it is optimized, each pass we only consider doing one transaction. So we have to do two pass to get the 
 * max profit we can make from two transactions.
 * The logic is as below:
 * For each index i, we can use it to split our stocks into two part. We can then calculate the maximum profit individually for this two
 * parts. The problem Best Time to Buy and Sell Stock (p121) lists the way we can use to calculate the maximum profit for only one pass
 * that is we calculate the profit at index i, by price[i] - min_price_before_i. Similarly, in the first pass, we use this method to
 * calculate the max profit we can make for each index i. But after one end pass, we only get the profits we can make from one transaction
 * Then our algorithm will let us revisit the array backward to get the profit we can make after each index i. In backward visit, we need
 * to modify our calculation a little bit, the equation is max_price_after_i - price[i]. In forward visit, we treat each index i as the
 * sell price, but now we treat each index i as buy price.
 * 
 * After knowing this logic, things become clear. When we revisit the input array backward, we can get the profit we made after index i
 * then we sum it up with values we get from forward visit, which is the profit we can make before index i. The maximum sum will 
 * be our global maximum profit we can make for two transactions. Also, if single transaction can be global maximum, then our algorithm
 * will also record this.
 * 
 * Remark:
 * As I discussed above, we use two passes and each pass is only considering one transaction, which is not efficient
 * See sol2 for a better and more DP solution.
 * 
 * Both sol1 and sol2 are good solutions, but sol2 is more recommended as it is one pass algorithm, also the idea of sol2 can be 
 * generalized to k transactions (p188)
 * @author hpPlayer
 * @date Sep 14, 2015 1:15:23 AM
 */

public class p123_sol1 {
	public static void main(String[] args){
		//int[] prices = {1,2,3};
		//int[] prices = {6,1,3,2,4,7};
		//int[] prices = {1,2,4,2,5,7,2,4,9,0};
		int[] prices = {2,1,2,0,1};
		System.out.println(new p123_sol1().maxProfit(prices));
	}
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len < 2) return 0;
        int oneBuyOneSell[] = new int[len];//keep the maximum profit we can make 
        
        int minBuy = prices[0];//we must buy at index 0
        for(int i = 1; i < len; i++){
            oneBuyOneSell[i] = Math.max(oneBuyOneSell[i-1], prices[i] - minBuy);
            minBuy = Math.min(minBuy, prices[i]);
        }
        
        int maxSell = prices[len-1];//we must sell at index len -1
        int TwoBuyTwoSell = 0;
        for(int i = len - 2; i >= 0; i--){
            TwoBuyTwoSell = Math.max(TwoBuyTwoSell, oneBuyOneSell[i] + maxSell - prices[i]);
            maxSell = Math.max(maxSell, prices[i]);
        }
        
        return TwoBuyTwoSell;
    }
}
