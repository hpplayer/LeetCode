/**
 * The main idea is similar to sol2, but a little trick to made code more understandable
 * The trick is, if current digit is legit(1-9), then we add the ways stored in previous index (i-1)
 * if current digit and previous digit can form a legit code (10 - 26), then we add the ways stored in previous previous index(i-2)
 * so two possible ways for each digit, we check first case, then add second case if it is legit pair
 * 
 * For the boundary case (index 1), if it can pair with index 0, since i-2 < 0, we need manually add the offset to it for case 2
 * 
 * The remaining part is same to the sol2.
 * @author hpPlayer
 * @date Mar 19, 2015 8:04:54 PM
 */
public class p091_sol3 {
    public int numDecodings(String s) {
        if(s.length() == 0 || s == null) return 0;
        int[] dp = new int[s.length()];
        if(s.charAt(0) != '0') dp[0] = 1;
        
        for(int i = 1; i < s.length(); i++){
        	if(s.charAt(i) != '0'){
        		dp[i] = dp[i-1];//use this char as single char, there is only one way to connect with previous bit, so dp[i] = dp[i-1]
        	}else{//single char range from 1 to 9, so when single char == '0', it is invalid
        		dp[i] = 0;
        	}
        	
        	if(canPair(s.substring(i-1, i+1))) 
        		dp[i] += (i > 1? dp[i-2] : 1);//when i - 2 < 0, if second index can pair with first index, we manually add 1
        }
        return dp[s.length()-1];
    }
    
    public boolean canPair(String s){
    	if(s.charAt(0) == 0) return false;
    	int value = Integer.valueOf(s.substring(0, 2));
    	return value >= 10 && value <= 26;
    }
}
