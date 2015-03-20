/**
 * This problem can either use recursion or dp ways to solve it.
 * But recursion is too time-consuming, so we have to use dp.
 * I didnt figure out the dp way, but the solution is similar to previous DP in String problems.
 * We build a matrix of m*n. (m: T.length, n: S.length), the value in the matrix  means how many distinct subsequences of 
 * current T in current S. 
 * 
 * For example, S: abcb, T:b 
 * Our matrix:
 * 	 0 a b c b
 * 0 1 1 1 1 1 <-empty target string, each string that is non-empty will have 1 empty substring (wo would not have empty S string, so set (0,0) =1 would be fine)
 * b 0 0 1 1 2 <-in index 2, we have only 1 b in S, so we have one way to from T, in index 4, we have 2 b in S, we have two ways to form T
 * 	 ^ <-empty source string, we can do nothing, return 0 (except for empty string T, here we treat it has empty S has a empty substring that match empty T)
 * 
 * So the basic idea is when updating values in this matrix,
 * if char(i-1) in S = char(j-1) in T(-1 since matrix is 1 base), we have two ways:
 * 1) using the char dp[i][j] = dp[i-1][j-1] we use current char in S to match current char in T, current value is the ways of prev chars in T and prev chars in S
 * 2) skipping the char dp[i][j] = dp[i][j-1] we skip current char in S and try to take prev char in S to match current char in T, current value is the ways we match prev char in T and cur char in S
 * if char(i-1) in S != char(j-1) in T(-1 since matrix is 1 base), we have one way:
 * skipping the char dp[i][j] = dp[i][j-1] we skip current char, current value is the ways we match prev char in T and cur char in S
 * 
 * Finally, we output the matrix[m][n], and it will be the result
 * @author hpPlayer
 * @date Mar 19, 2015 4:28:04 PM
 */
public class p115_sol2 {
	public static void main(String[] args) {
		p115_sol2 test = new p115_sol2();
		System.out.println(test.numDistinct("b", ""));
	}

	public int numDistinct(String S, String T) {
		if(T== null || T == null || S.length() == 0 || S.length() < T.length()) return 0;
		int m = T.length();
		int n = S.length();
		//(i, j) in dp[] means how many substrings of current 0-i of S in 0-j substring of T 
		int[][] dp = new int[m+1][n+1];//create a matrix that index based on 1, since we include 0 case to S and T
		for(int i = 0; i < dp[0].length;i++){//first row, T is empty, each string has only 1 empty substring 
			dp[0][i] = 1;
		}
		//start update values in matrix
		for(int i = 1; i < dp.length; i++){
			for(int j = 1; j < dp[0].length; j++){
				if(S.charAt(j-1) == T.charAt(i-1)){//matrix is 1 based, so we need index-1 to get char in string
					dp[i][j] = dp[i-1][j-1] + dp[i][j-1];//we either use previous char or current char in S to match T
					//example: T: C S: CC, we can either use previous c match T: c-c, 0-c or use current c match: c-0, c-c  
				}else{
					dp[i][j] = dp[i][j-1];//we have to use previous char in S to match T
					//example: T: C S: CA, we have to use previous c match T T:c-c, 0-A
				}
			}
		}
		return dp[m][n];
	}
}
