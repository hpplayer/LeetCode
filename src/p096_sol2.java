/**
 * DP solution is not easy to understand:
 * dp[0] = 1, we have one way to construct tree with 0 node
 * dp[1] = 1, we have one way to construct tree with 1 node
 * dp[2] = 2, we have two ways to construct tree with 2 nodes
 * 		 = dp[0] * dp[1], 1 as root, how many ways to construct tree with 1 node? dp[1]
 * 		 + dp[1] * dp[0], 2 as root, how many ways to construct tree with 1 node? dp[1]	
 * dp[3] = dp[0] * dp[2], 1 as root, how many ways to construct tree with 2 nodes? dp[2]
 * 		 + dp[1] * dp[1], 2 as root, left tree has 1 node, right tree has 1 node, dp[1] * dp[1]
 * 		 + dp[2] * dp[0], 3 as root, how many ways to construct tree with 2 nodes? dp[2]
 * 		= 3;
 * ...so fill the dp array and return the last index
 * @author hpPlayer
 * @date Apr 8, 2015 1:06:54 PM
 */
public class p096_sol2 {
	public static void main(String[] args){
		System.out.println( numTrees(3));
	}
	 public static int numTrees(int n) {
		 int[] dp = new int[n+1];
		 dp[0] = 1;
		 dp[1] = 1;
		 for(int i = 2; i < dp.length; i++){//fill the array
			 for(int j = 0; j < i; j++){//j is the root of min-subtree
				 dp[i] += dp[j] * dp[i-j-1];//j and i-j-1 can be observed from example above
			 }
		 }
		 return dp[n];
	 }
}
