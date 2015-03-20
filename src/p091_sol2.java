import java.util.Arrays;
/**
 * DP way, I got the basic idea that is if current digit and previous digit can be a valid code, then we have two ways 
 * 1) take both, so our combination will be the dp[i-2]
 * 2) only take current one, so our combination will be the dp[i-1]
 * So our final value will be 1) + 2) 
 * 
 * But if current code is not valid code, then we set dp[i] = 0 or by do thing due to the default value in the int[] is 0
 * The trick here is how to handle different 0 cases like 01, 00, 110 all those should return 0. 
 * A convenient way is to use a isValid() to check the validity.
 * For the boundary case like 11,
 * we can add a dummy node in the head, if we found second 1 (index : 1 ) can form a valid code with first 1 (index : 0 ) 
 * then its value will be 1) + 2), 1):1 2):1, so the result will be 2
 * 
 * A more easy-to-understand way is sol3, please ref to sol3
 * Anyway, this problem is 1-D dp problem and not hard, but the boundary case and special case "0" is really challenging and 
 * needs more attention
 * @author hpPlayer
 * @date Mar 19, 2015 7:39:50 PM
 */

public class p091_sol2 {
	public static void main(String[] args) {
		String a = "1168963884134125126536966946586868418993819971673459188478711546479621135253876271366851168524933185";
		String b = "9371597631128776948387197132267188677349946742344217846154932859125134924241649584251978418763151253";
		
		p091_sol2 test = new p091_sol2();
		System.out.println(test.numDecodings("11"));
	}

	    public int numDecodings2(String s) {
	        int n = s.length();
	        if (n==0) return 0;
	        int[] dp = new int[n+1];
	        dp[0] = 1;
	        if (isValid(s.substring(0,1))) dp[1] = 1;
	        else dp[1] = 0;
	        for(int i=2; i<=n;i++){
	            if (isValid(s.substring(i-1,i)))
	                dp[i] = dp[i-1];
	            if (isValid(s.substring(i-2,i)))
	                dp[i] += dp[i-2];
	        }
	        return dp[n];
	    }
	    

    public int numDecodings(String s) {
    	if(s == null || s.length() == 0) return 0;
		int[] dp = new int[s.length()];//1 based index
		//dp[0] = 1;
      	if(s.charAt(0) != '0'){
    		dp[0] = 1;
    	}
        for(int i = 1; i < s.length(); i++){ 
        	if(i == 1){
        	   	if(isValid(s.substring(i, i+1))){
            		dp[i] = dp[i-1];
            	}
            	if(isValid(s.substring(i-1, i+1))){
            		dp[i] += 1;
            	}
        	}else{
             	if(isValid(s.substring(i, i+1))){
            		dp[i] = dp[i-1];
            	}
            	if(isValid(s.substring(i-1, i+1))){
            		dp[i] += dp[i-2];
            	}
        	}
   
        		
        }
        System.out.println(Arrays.toString(dp));
        return dp[s.length()-1];
    }
    
    public boolean isValid(String s){
    	if(s.charAt(0) == '0') return false;
    	return Integer.valueOf(s) >=1 && Integer.valueOf(s) <= 26;
    }
}
