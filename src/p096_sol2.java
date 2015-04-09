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
 * 		= 2 + 1 + 2 = 5;
 * dp[4] = dp[0] * dp[3], 1 as root, how many ways to construct tree with 3 nodes? dp[3]
 * 		 + dp[1] * dp[2], 2 as root, how many ways to construct tree with 1 and 2 nodes? dp[1]*dp[2] = 2
 *       + dp[2] * dp[1], 3 as root, how many ways to construct tree with 2 and 1 nodes? dp[2]*d[1] = 2
 *       + dp[3] * dp[0], 4 as root, how many ways to construct tree with 3 nodes? dp[3]
 *       =5+5+2+2 = 14
 * ...so fill the dp array and return the last index
 * @author hpPlayer
 * @date Apr 8, 2015 1:06:54 PM
 */
public class p096_sol2 {
	public static void main(String[] args){
		System.out.println( numTrees(4));
	}
	 public static int numTrees(int n) {
		 int[] dp = new int[n+1];
		 dp[0] = 1;
		 dp[1] = 1;
		 for(int i = 2; i < dp.length; i++){//fill the array
			 for(int j = 0; j < i; j++){//j is the root of min-subtree
				 //dp[j] is left, 1 as the root, i-j-1 is nodes for right tree, so dp[i-j-1] is right
				 dp[i] += dp[j] * dp[i-j-1];//j and i-j-1 can be observed from example above
			 }
		 }
		 return dp[n];
	 }
}
