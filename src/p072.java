/**
 * This is my original AC code(My first attempt is incorrect(see nested loop, statement that I commented out),
 * since I am not very clear about the mechanism of this algorithm, though it is already very close to final solution. 
 * After a slight change, without others help, my solution is successfully accepted by LeetCode
 * 
 * Basic idea: 
 * Build a matrix, rows are string 1(given string), columns are string 2(target)
 * There are only three operations:
 * deletion: right
 * insertion: bottom
 * replace diagonal 
 * Example:
 *   0 C A T (given string, s1)
 * 0 0 1 2 3 (from empty, C, CA, CAT, how many deletions we need to delete to empty S2)
 * A 1 T1T3
 * B 2 T4T2
 * 	 (from empty S1, how many insertions we need to add to empty, A, AB?)
 * 
 * diagonal from 0,0 to A,C means from C,A we need to do a replace 
 * 
 * T1->T2 means, we add the original values in T1(min efforts to match C and A), and add the effort we need to replace A to B, so 
 * if we get same value (ex.A to A), the value will be the original values in T1, otherwise we need +1 for replacement 
 * T3->T2 means, we add the original values in T3(min efforts to match CA and A), and add the effort we need to insert B into s1(+1 for
 * insertion), there is no consideration about value itself, we only need to add values in s1 
 * T4->T2 means, we add the original values in T4(min efforts to match C and AB), and add the effort we need to delete A in CA in s1
 * (+1 for deletion), there is no consideration about value itself, we only need to delete values in s1 
 * 
 * So for replacement we have two options: +1 or +0
 * For deletion and insertion, we only have +1
 * Obviously, our choice of next step depends on the minimum value of these four cases(magically, it will always be the operation of 
 * +0 for replacement, i.e. if char matched, dp[i-1][j-] will always be the smallest, one possible explanation is that, when S1[i] ==
 * S2[j], we do nothing, it = values of matching S1[i-1] and S2[j-1], if S1[i] != S2[j], then we need to do something, either replace,
 * add or delete to make them match, so we will select the min values that make S1 and S2 match)
 * 
 * Finally, we output the value at dp[m][n], it will be the answer
 * @author hpPlayer
 * @date Mar 18, 2015 5:17:58 PM
 */
public class p072 {
	public static void main(String[] args){
/*
*   z o o
* z 0 1 2
* o 1 0 1<--we know values of zo to z, and want to know the efforts to change from zoo to zo, o == o, so it does not cost any additional effort 
* a 2 1 1
* 	  ^ 
*     o != a, we need do something, either we delete o and do two more steps to get zoa(from left), or we replace o to a (zo => za)
*     and we now have (za and zoa), and we know the value of (z => zo), or we can just add one more element to zo(S1) to form zoa, which 
*     matched zoa(s2)
*/
		System.out.println(minDistance("zoologicoarchaeologist", "zoogeologist"));
	}
	   public static int minDistance(String word1, String word2) {
	        if(word1 == null || word2 == null) return 0;
	        int m = word2.length();
	        int n = word1.length();
	        int[][] dp = new int[m+1][n+1];//1 based 
	        dp[0][0] =0 ;
	        for(int i = 1; i < dp.length; i++){
	            dp[i][0] = dp[i-1][0] +1;
	           // System.out.println(dp[i][0]);
	        }
	        for(int i = 1; i < dp[0].length; i++){
	            dp[0][i] = dp[0][i-1] +1;
	           // System.out.println(dp[0][i]);
	        }
	        for(int i = 1; i < dp.length; i++){
	            for(int j = 1; j < dp[0].length; j++){
	                if(word1.charAt(j-1) == word2.charAt(i-1)){
	                  //  dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
	                	//above statement may get error if dp[i-1][j-1] + 0 is the smallest
	                	dp[i][j] = dp[i-1][j-1];
	                }else{
	                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) +1;
	                }

	            }
	        }
	        for(int i = 0; i < dp.length; i++){
	            for(int j = 0; j < dp[0].length; j++){
	            	System.out.print(dp[i][j] + " ");
	            }
	            System.out.println();
	        } 
	        return dp[m][n];
	    }
}
