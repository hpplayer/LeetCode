/**
 * The problem asks us to calculate the maximum profits we can make when considering all transactions, and the transaction is consecutive.
 * So the problem can be understood as calculate the sum of all positive (more exactly, non-negative) profits we can make from input array.
 * Example:
 * 1) a <= b <= c, then the total non negative profit is c - a = b -a + c - b
 * 2) a <= b >= c, then the total non negative profit is b -a (c-b is negative now, don't add it, we don't have a-c/c-a since the problem states
 * that no two transactions are overlapped, also b >= c, so the max positive profit we can make from buying at a, must be selling at b) 
 * @author hpPlayer
 * @date Sep 13, 2015 10:23:51 PM
 */
public class p122_sol2 {
    public int maxProfit(int[] prices) {
        if(prices.length < 2) return 0;
        int result = 0;
        for(int i = 1; i < prices.length; i++){
            int profit = prices[i] - prices[i-1];
            if( profit> 0) result += profit;
        }
        
        return result;
    }
}
