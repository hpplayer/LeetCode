/**
 * DP algorithm...3D 
 * In order to know substring of length k of s1 and s2 is scramble, we need to know if there exists a substring pair that is scramble
 * can form length k. Like sol1, we tried all possible substring within length k to check if we have such pair(such pair can have 
 * normal order of string s1 and s2, or swapped order of string s1 and s2) details plz see code below:
 * 
 * boundary case is that the k= 0, then we directly compare s1.charAt(i) and s2.charAt(j)...
 * Then we need fill the table.
 * Finally. return dp[0][0][len -1],which means comparing the whole string of s1 and s2
 * Remark:
 * since s1.length must equal to s2.length, and k can also be 0, so we will have matrix that has same length for s1, s2 and k.
 * p here is the pointer that help us check each substring of string of length k, if found one just break this loop and set the matrix
 * value to true
 * i and j can start anywhere, k is the length from i/j to k th away, so we must have i + k < len && j + k < len
 * 
 * This problem with DP solution is kind of complicated and hard to think.
 * So in my opinion, sol1 with optimized recursion solution is better 
 * @author hpPlayer
 * @date Mar 28, 2015 12:53:51 AM
 */
public class p087_sol2 {
	public boolean isScramble(String s1, String s2){
		int len = s1.length();
		if(len != s2.length()) return false;
		if(s1.endsWith(s2)) return true;
		
		boolean[][][] dp = new boolean[len][len][len];
		for(int i = 0; i < len; i++){
			for(int j = 0; j < len; j++){
				dp[i][j][0] = s1.charAt(i) == s2.charAt(j);
			}
		}
		//k: string length from i or j, so i + k must < len
		/* The meaning of this four nested loops is:
		 * we check each substring of length k in s1 and s2 to see if it is scramble, say it is stored in v1
		 * Next time when we are checking some other substring that use v1, we also check the the remaining part of that 
		 * substring (the other part in normal order or reverse order), if both true, then fill v2 with true
		 * i and j can start anywhere before len - k (cuz we are checking substring s1 and s2 of length k), and obviously, we have k ways 
		 * to split those substrings in k ways, that is there are at most k ways to scramble them.
		 */
		for(int k = 1; k <len; k++){//k = 0 col has been filled
			for(int i = 0; i < len - k; i ++){
				for(int j = 0; j < len - k; j++){
					for(int p = 0; p < k; p++){//split into 0 to p and p+1 to k
						/* we have 0--p, p+1--k
						 * i:(p):- (k-p):-----
						 * j:(p):- (k-p):-----
						 * normal order: first part: compare i + p and j+ p and second part: compare j + p + 1 + (k-p-1) and i+p+1 + (k-p-1)<--possible increment from p+1 to k
						 * i:(p):- (k-p):-----k
						 * j:(k-p):----- (p):-k
						 * swapped order: first part: compare i + p and j+ k-p + p and second part: i + p + 1 + (k-p-1) and j + (k-p-1) <-- possible increment from 0 to k-p-1 
						 */
						if((dp[i][j][p] && dp[i+p+1][j+p+1][k-p-1]) || (dp[i][j+k-p][p]&&dp[i+p+1][j][k-p-1])){//normal order and swapped order
							dp[i][j][k] = true;
							break;
						}
					}
				}
			}
		}
		return dp[0][0][len - 1];
	}
}
