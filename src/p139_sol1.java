import java.util.HashSet;
import java.util.Set;
/**
 * Basic idea:
 * DP approach, using a boolean array to record whether the substring(0, i) can be found in the set,
 * so dp[i+1] will be true iff dp[j] is true and string.substring(j,i) is in the Dict.

 * 
 * Remark:
 * we need an extra index to help us find if substring(0, j) is in the Dict:
 * dp[i+1] = dp[0] + string.substring(0, i+1)
 * so, we add an extra index in array (dp[0]), and let it be true.
 * 
 * The idea is simple but hard to come to mind in the first sight.
 * @author hpPlayer
 * @date Mar 22, 2015 7:38:59 PM
 */
public class p139_sol1 {
	public static void main(String[] args){
		String s = "c";
		Set<String> hs = new HashSet<String>();
		hs.add("abc");
		System.out.println("abc".contains(""));
		System.out.println(new p139_sol1().wordBreak(s, hs));
	}
	
	public boolean wordBreak(String s, Set<String> dict) {
		boolean[] dp = new boolean[s.length()+1];//why length + 1? since we need a helper index
		dp[0] = true; //this is the helper index, it can help us check the substring start with first char in String s
		for(int i = 1; i < dp.length; i++){//update the array
			for(int j = 0; j < i; j++){
				if(dp[j] && dict.contains(s.substring(j, i))){//think about the dogs and dog example, j = 0, dp[0] = true & dogs.contains(dog)
					dp[i] = true;
					break;//no need search other substring
				}
			}
		}
		return dp[s.length()];
	}
}
