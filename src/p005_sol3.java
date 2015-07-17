/**
 * DP solution (using matrix)
 * Accepted solution but not so fast yet easy to understand
 * 
 * we build a DP matrix, index(x,y) means the substring from i to j
 * if it is palindrome, then value(x,y) should be true, otherwise it should be false
 * we update the matrix based on DP approach.
 * if matrix[i+1][j-1] is true && charAt(i) == charAt(j)
 * then we update matrix[i][j] to be true.
 * 
 * First, we need update the boundary case, which in our case should be the centers.
 * we update the Diagonal value to be true, since matrix[i][i] means single char
 * we update each two consecutive chars that are same to be true. we need this update, since we may have centers rooted at 2 chars
 * 
 * Then, we start update our matrix.
 * the outer loop is the length of palindrome, it should start from 2 to s.length()
 * the inner loop is the start of palindrome, it can start from anywhere to s.length() - len of palindrome +1
 * the end point of palindrome is just start of palindrome +  len of palindrome - 1
 * if we found the start and end point are same char && matrix[start+1][end-1] is already a palindrome
 * then we update matrix[start][end] to be true
 * 
 * Since we have several assumptions here like max length of s is 1000 and we have unique solution,
 * we can safely use a single string "result" or single int to record the len and start of palindrome
 * 
 * finally, we just return the longest palindrome as solution.
 * 
 * details can be found below
 * 
 * Time complexity: O(n^2) (need fill all blanks in n*n matrix)
 * Space complexity: O(n^2) (totally n*n blanks in matrix)
 * @author hpPlayer
 * @date Jul 7, 2015 5:36:02 PM
 */
public class p005_sol3 {
	public static void main(String[] args){
		String a = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String b = "bb";
		System.out.println(new p005_sol3().longestPalindrome(b));
	}
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[1000][1000];//create a dp table 1000 * 1000 (1000 is the max len of string s)
        int maxLen = 1; //result length
        int start = 0; //result start index
        //String result = null;
        //the following two for loops are actually help us define the center of palindrome. The center can be either single char or double char
        //single char is palindrome
        for (int i = 0; i < s.length(); i++){
        	dp[i][i] = true;
        }
        
        //if we miss this for loop, then palindrome centered at "AA" format will be judged as false
        for(int i = 0; i < s.length() -1; i++){
        	if (s.charAt(i) == s.charAt(i+1)){
        		dp[i][i+1] = true;
        		start = i;//since there are only unique longest palindrome, we are safely to assume only one start position
        		maxLen = 2;
        		//result = s.substring(i, i+2);
        	}
        }
        
        for(int len = 2; len <= s.length(); len++){//test different length, we have considered len 1-2 cases, so start from 3 here
        	for (int i = 0; i < s.length() - len + 1; i++){//test different start point
        		int j = i + len -1;// here -1 is because index count start from i, like i = 1, len = 3, we should return 3, but 1 + 3 = 4, so we need -1 to get 3
        		if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]){//if char matched and previous case matched as well
        			dp[i][j] = true;//safely update
        			start = i;//because our loop is based on the length of palindrome and we have unique solution, it is safely to replace previous start if we found new one
        			maxLen = len;
        			//result = s.substring(i,j+1);
        		}
        	}
        }
        //System.out.println(result);
        return s.substring(start, start + maxLen);// we should have -1 but substring() excludes the end index, so we need +1 to balance 
    }
}
