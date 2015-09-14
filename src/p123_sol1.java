import java.util.Arrays;
/**
 * Still not very clear about this problem, need to be updated tomorrow
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
        if(prices.length < 2) return 0;
        int len = prices.length;
        int[] diff = new int[len];
        int curr = 0;
        int result = 0;
        for(int i = 1; i < prices.length; i++){
        	curr = Math.max(0, curr + prices[i] - prices[i-1]);
        	diff[i] = Math.max(diff[i-1], curr);//Math.max(diff[i-1] + prices[i] - prices[i-1], curr);
        	
        }
       
        
        curr = 0;
       // System.out.println(len);
        for(int i = len -2; i >= 0; i--){
        	curr = Math.max(0, curr + prices[i+1] - prices[i]);
        	result = Math.max(result, diff[i] + curr);
        }
        //System.out.println(Arrays.toString(diff2));
        return result;
    }
}
