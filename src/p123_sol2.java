/**
 * I have been struggled with this solution for a long time, until I saw a post with clear name of variable, then I found this solution
 * is not actually so hard to understand.
 * Like the tag of this problem, we need to solve the problem in a DP way.
 * For this specific problem, the Maximum profit we can make for the input array can be from two ways: 1) one transaction 2) two transactions
 * We also know the profit is calculated as Sell price - Buy price, so we need at least four variables to calculate the profit made 
 * from 1) and 2). To make more profit, we want the buy price the lower the better, and the sell price the higher the better. Does it
 * mean we can use Math.min(buy, price[i]) to get the lowest price? Probably no, because we want our profit to be maximized, for twoBuy
 * which include the buy-and-sell for first onyBuy and the buy price for second buy, we want its first part (buy-and-sell) to be large
 * while the second part (second buy) to be small. You see, if we use Math.min(), it will become very inconvenient. So instead, we use
 * a trick there, we let buy price stored as its negative value, then we can simply use Math.max(first part - second part) to get the
 * maximum first part while the minimum of second part.
 * 
 * We say this problem is very dp, because the second buy and sell is based on the value from second buy, which is based on first 
 * buy and sell, which is based on first buy.
 * 
 * Remark:
 * For the return statement, we can simplified it to return twoBuyTwoSell, since twoBuyTwoSell should be the maximum profit we can make
 * from choosing 1) or 2). Thus if 1) oneBuyOneSell can give the maximum profit, our 2)twoBuyTwoSell will actually be this value. But
 * I still keep it here to make my code more understandable
 * @author hpPlayer
 * @date Sep 14, 2015 1:38:32 PM
 */
public class p123_sol2 {
    public int maxProfit(int[] prices) {
        int oneBuy = Integer.MIN_VALUE;
        int oneBuyOneSell = 0;
        int twoBuy = Integer.MIN_VALUE;
        int twoBuyTwoSell = 0;
        for(int i = 0; i < prices.length; i++){
            oneBuy = Math.max(oneBuy, -prices[i]);//we set prices to negative, so the calculation of profit will be convenient
            oneBuyOneSell = Math.max(oneBuyOneSell, prices[i] + oneBuy);
            twoBuy = Math.max(twoBuy, oneBuyOneSell - prices[i]);//we can buy the second only after first is sold
            twoBuyTwoSell = Math.max(twoBuyTwoSell, twoBuy + prices[i]);
        }
        
        return Math.max(oneBuyOneSell, twoBuyTwoSell);
    }
}
