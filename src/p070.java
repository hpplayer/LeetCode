/**
 * Similar to Fib number, so we have three approach to it
 * 1) recursion, see climbStairs2(), return LTE error
 * 2) dp with O(n) space complexity, O(n) time complexity, see climbStairs3()
 * 3) dp with O(1) space complexity, O(n) time complexity, see climbStairs3()
 * @author hpPlayer
 * @date Apr 5, 2015 11:39:12 PM
 */
public class p070 {
	public static void main(String[] args){
		int a = 44;
		System.out.println(climbStairs(a));
		System.out.println(climbStairs2(a));
	}
    public static int climbStairs(int n) {
        if(n == 1) return 1;
        if(n== 2) return 2;
        int a = 1, b = 2;//a: first step, b: second step
        for(int i = 2; i < n; i++){
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
    
    public static int climbStairs2(int n) {
        if(n== 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;
        return climbStairs(n-1) + climbStairs(n-2); 
    }
    
    public int climbStairs3(int n) {
        if(n==1) return 1;//for n=1, we only have 1 index
        int[] dp = new int[n];
        dp[0] = 1;//n == 1;
        dp[1] = 2;//n == 2
        for(int i = 2; i < n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }
}

