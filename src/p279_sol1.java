/*
Perfect Squares

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
*/

/**
 * I didn't solve this problem which makes me very upset
 * Here is the DP solution. As most dp problem, we need create a dp table.
 * The value in dp table means the least perfect squares to get the number
 * for example dp[1] = 1 means we need 2 least perfect squares to get num 1
 * for example dp[12] = 3 means we need 3 least perfect squares to get num 12
 * Yes, the index of cell is the number we assigned to that cell
 * 
 * The dp solution works in this way: since our dp table is consecutive, we will know the num of least square number for any number < i
 * Thus, we will generate a new series of perfect squares and let num i - those perfect square one by one, then we search the numbers 
 * of least square number we need to get the the remainder. Like for input 4, we will generate 1*1, 2*2, then we query the remainder
 * 4- 1 =3 and 4-4 = 0, we found 4-4 give smaller numbers of least square number, thus we update dp[4] by dp[0] + 1, which gives 1
 * 
 * Finally we just need to return the last value in the dp array and it is related with input n, thus the number in that cell will be
 * our solution
 * 
 * @author hpPlayer
 * @date Sep 16, 2015 7:55:20 PM
 */
public class p279_sol1 {
	public static void main(String[] args){
		System.out.println(new p279_sol1().numSquares(48));
	}
	
    public int numSquares(int n) {
        //we need a dp arary with len n+1, why? since we want include num 0 into this array, so we can 
        //deal with case like visiting 4, 9 and getting remainder 0
        int[] dp = new int[n+1];
        
        for(int i = 1; i < dp.length; i++){
            dp[i] = Integer.MAX_VALUE;//set the inital value = int.max, acutally for all natural numbers, the dp value will not exceed 4
            //get this number
            for(int j = 1; j*j <= i; j++){
                //we query the previous value from dp[i-j*j], then add 1 for j*j 
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        
        //the final block is num n
        return dp[n];
        
    }

}
