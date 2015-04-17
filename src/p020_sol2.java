/**
 * This is similar to p32 Longest Valid Parentheses
 * each value in dp[i] means the max valid length ended in index i, 
 * for each dp[i], we can get the char in i-dp[i-1] -1, if char there can be paired with current char,
 * then the distance +2, in which distance if dp[i-1].
 * Since by doing that, we may connect two separated pair sequences,
 * we will check i-2 to see if we can connect them together.
 * 
 * Remark:
 * Since dp[0] will not be a valid pair, the default value should be 0.
 * our loop should start from 1.
 * @author hpPlayer
 * @date Apr 16, 2015 4:52:32 PM
 */
public class p020_sol2 {
	public static void main(String[] args){
		String s = "()[]{}";
		System.out.println(isValid(s));
	}
	
	 public static boolean isValid(String s) {
	        int[] dp = new int[s.length()];
	        for(int i = 1; i < s.length(); i++){
	            int j =  i - dp[i-1] -1;//get index before the valid pair sequence
	            if(j < 0) continue;//j should be valid index
	            if((s.charAt(j) == '(' && s.charAt(i) == ')') || (s.charAt(j) == '{' && s.charAt(i) == '}')
	            	|| (s.charAt(j) == '[' && s.charAt(i) == ']')){//new valid pair found
	                dp[i] = dp[i-1] + 2;
	                if((j -1) >= 0) dp[i] += dp[j-1];//we may connect two seperated pair sequences
	            }
	        }
	        return dp[s.length()-1] == s.length();
	    }
	
	
}
