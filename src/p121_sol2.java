/**
 * This is a very DP way to solve the problem
 * We are accumulating the profit from each day, so our idea should be accumulative as well, do not jump days.
 * 
 * If each following day has a higher value, then we are very happy and increasing our accumulative profit accordingly.
 * 
 * If day i has a high price, which makes selling stock at day i give a negative profit, but the overall profit is still positive, then we still
 * take day i's profit into the accumulation.
 * 
 * However, if day i has a very high price, which makes selling stock at day i will give negative overall profit, or day i may has a little bit
 * high value, but it still makes our current accumulation (overall profit) become negative, then our accumulation for current period will end, 
 * and we need start a new accumulation for next period start at day i. At the same time, we will compare our accumulative profit with the result
 * and update result if necessary.
 * 
 * Example
 * price: 5, 7, 6, 8 
 * day:   1, 2, 3, 4
 * the profit at selling stock at day 2, gives accumulative profit 2
 * the profit at selling stock at day 3, gives accumulative profit 1
 * the profit at selling stock at day 4, gives accumulative profit 3
 * So the global max profit is profit 3, which is correct
 * 
 * Remark:
 * 1) This is like we are continuously selling and buying stocks, and accumulative the profit from each transaction
 * 2) We won't make negative profit, the min profit we make is 0, as we can choose to not start this series of transaction
 * 3) Since the profits are accumulative, we guarantee the accumulative profit end at index i is the maximum profit we can make ending at index i
 * 4) This algorithm is known as Kadane's Algorithm, where Kadane's Algorithm is aiming to find the max sum of subarray, and now we are finding
 * the max difference of subarray 
 * 
 * @author hpPlayer
 * @date Sep 13, 2015 9:23:53 PM
 */
public class p121_sol2 {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1) return 0;
        int max_end_here = 0;
        int max_so_far = 0;
        for(int i = 1; i < prices.length; i++){
            //we are calculating the profit from buying price at i-1 and selling price at i
            //if this transaction is gaining money then, price[i] should larger than price[i-1]
            //if it is larger than 0, then we will add it to our accumulative profit earn so far
            //like we buy stock at some point, and now we are calculating the profit from each following day
            max_end_here = Math.max(0, max_end_here + prices[i] - prices[i-1]);
            max_so_far = Math.max(max_so_far, max_end_here);
            
        }
        
        return max_so_far;
    }
}
