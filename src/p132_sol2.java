/**
 * A follow up problem of P131.
 * We could use two DP to solve this problem.
 * First DP is to create the matrix that we used to solve p131
 * Second DP is to find the Min cut in the string. Similar to the previous Longest Valid Parentheses problem (p32), we need build
 * DP backward, since we dont know how to build base case if start from the begin.
 * The second DP's idea is that:
 * we build a array of s.length(), each position records how many cuts we need to participate palindrome from current index to the end
 * Obviously, the last position should be 1 (include the left cut), the last second position may be 1 or 2, depends on whether the last 
 * two are palindrome(include the left cut)...  
 * we look from tail to head, for each index, we check all palindrome behind it to see if those palindrome can help minimize cuts,
 * Finally we simply output array[0] -1 (-1 means we dont include the left cut of array[0])
 * 
 * Remark:
 * The worst case is we need to separate bit one by one (ex. abc : a, b, c).
 * When we are looking for palindrome after index i, the palindrome may not may not help us, for example aabba
 * lets look at index 0, if we separate a with abba, then we only needs 1 cut, and we store it into the array, 
 * but if we look the next inner loop that we separate aa with bba, then we need 3 cuts, hence we needs use Math.min to determine 
 * if the palindrome can help minimize cuts
 * @author hpPlayer
 * @date Mar 19, 2015 12:56:30 AM
 */
public class p132_sol2 {
	public static void main(String[] args){
		//it is possible, we separate a substring one by one costs less than find palindrome among them 
		p132_sol2  test = new p132_sol2();
		String s = "adabdcaebdcebdcacaaaadbbcadabcbeabaadcbcaaddebdbddcbdacdbbaedbdaaecabdceddccbdeeddccdaabbabbdedaaabcdadbdabeacbeadbaddcbaacdbabcccbaceedbcccedbeecbccaecadccbdbdccbcbaacccbddcccbaedbacdbcaccdcaadcbaebebcceabbdcdeaabdbabadeaaaaedbdbcebcbddebccacacddebecabccbbdcbecbaeedcdacdcbdbebbacddddaabaedabbaaabaddcdaadcccdeebcabacdadbaacdccbeceddeebbbdbaaaaabaeecccaebdeabddacbedededebdebabdbcbdcbadbeeceecdcdbbdcbdbeeebcdcabdeeacabdeaedebbcaacdadaecbccbededceceabdcabdeabbcdecdedadcaebaababeedcaacdbdacbccdbcece";
		//String s = "aabba";
		//String s = "abba";
		boolean[][] table = test.buildMatrix(s);
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[0].length; j++){
				//System.out.print(table[i][j] + "         ");
			}
			//System.out.println();
		}
		System.out.println(test.minCut(s));
	}
	 public int minCut(String s) {
	        boolean[][] table = buildMatrix(s);
	        int[] dp = new int[s.length()];//array records the min steps we need to partition palindrome until the end 
	        for(int i = s.length() -1; i >= 0; i--){//we start update the dp[] from tail to head
	            dp[i] = s.length() - i;//how many palindromes behind i, if there is no palindrome, then we need those cuts(two char one cut)
	            for(int j = i; j <s.length(); j++){//we check if some palindromes after dp[i] can help minimize steps 
	                if(table[i][j]){
	                	//System.out.println(i);
	                      dp[i] = Math.min(dp[i], (j == s.length()-1? 1 : 1 + dp[j+1]));//last index only have 1 cut(include left cut)
	                     // dp[i] = dp[j+1] + 1; wrong, we may have the case that palindrome does not help
	                }
	            }
	        }
	        return dp[0] - 1; //we substract the left cut of first node(we dont have element in the left of first node)
	    }
	    
	    public boolean[][] buildMatrix(String s){
	        boolean[][] table = new boolean[s.length()][s.length()];
	        for(int i = 0; i < s.length(); i++){
	            table[i][i] = true;
	            
	            //even 
	            int left = i-1;
	            int right = i;
	            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
	                table[left--][right++] = true;
	                
	            //odd
	            left = i-1;
	            right = i+1;
	            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
	                table[left--][right++] = true;
	        }
	        return table;
	    }
}
