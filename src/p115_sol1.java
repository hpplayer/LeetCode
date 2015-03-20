import java.util.HashMap;
/**
 * Recursion approach with help of HashMap, still too slow
 * DP approach see sol2
 * 
 * @author hpPlayer
 * @date Mar 19, 2015 3:05:10 PM
 */

public class p115_sol1 {
	public static void main(String[] args){
		p115_sol1 test = new p115_sol1();
		System.out.println(test.numDistinct("aabdbaabeeadcbbdedacbbeecbabebaeeecaeabaedadcbdbcdaabebdadbbaeabdadeaabbabbecebbebcaddaacccebeaeedababedeacdeaaaeeaecbe", "bddabdcae"));
	}
    public int numDistinct(String S, String T) {
    	return numDistinct(S, T, new HashMap<String, Integer>());
    }
    
    public int numDistinct(String S, String T, HashMap<String, Integer> hs ) {
    	if(hs.containsKey(S)) return hs.get(S);
        if(S == null || T == null || S.length() == 0 || T.length() == 0) return 0;
        if(S.charAt(0) != T.charAt(0)){
        	int result = numDistinct(S.substring(1), T);
        	hs.put(S, result);
        	return result;
        }else{
        	int result = numDistinct(S.substring(1), T) + numDistinct(S.substring(1), T.substring(1));
        	hs.put(S, result);
        	return result;
        }
    }
    
	/*wrong answer...I thought the question is asking how many distinct substrings of T in S
    public int numDistinct(String S, String T) {
    	if(S == null || T == null || S.length() == 0 || T.length() == 0) return 0;
        int count = 0; 
        //boolean[][] matrix = new boolean[T.length()][T.length()];
        for(int i = 0; i < T.length(); i++){
            for(int j = i; j < T.length(); j++){
                if(S.contains(T.substring(i, j+1))){
                	System.out.println(T.substring(i, j+1));
                    count++;
                }
            }
        }
        return count;
    }
    */
}
