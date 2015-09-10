/**
 * This solution use only one char, it thinks in the reverse one:
 * We firstly fill the expected[]
 * then in S if we found a char in T, we let expected[c] --. As long as expected[c] > 0, it means we still need more c to compose T
 * if expected[c] < 0, it means we have extra c in current window
 * At some points, if we have found a window contains all chars required, we then begin shrink the window, we remove one char from left
 * and add it back to the expected[], which means we let expected[c] ++. At some points, if the expected[c] > 0, it means now 
 * we need more c in window, and current window does not have all chars in T, so we stop shrinking.
 * This is the basic idea.
 * 
 * @author hpPlayer
 * @date Sep 10, 2015 5:04:53 PM
 */
public class p076_sol2 {
	public static void main(String[] args){
		String s = "abc";//"ADOBECODEBANC";////"cabwefgewcwaefgcf";
		String t = "c";//"ABC";////"cae";//
		
		System.out.println(new p076_sol2().minWindow(s, t));
	}
	
    public String minWindow(String s, String t) {
        int[] expected = new int[256];
        for(int i = 0; i < t.length(); i++){
            expected[t.charAt(i)] ++;
        }
        
        int count = 0;
        int minWin = Integer.MAX_VALUE;
        int minleft = -1;
        
        for(int left =0, right = 0; right < s.length(); right++){
            //we will decrease the value no matter it is in t or not
            expected[s.charAt(right)] --;
            
            //if its value is >= 0 after --, then it must in t
            if(expected[s.charAt(right)] >= 0) count ++;
            
            while(count == t.length()){
                if(right - left + 1 < minWin){
                    minleft = left;
                    minWin = right - left + 1;
                }
                
                //we pop up the char at left index, it can be treated as now we need a char
                expected[s.charAt(left)] ++;
                
                //if now we really need it as its value > 0, then count --
                if(expected[s.charAt(left)] > 0) count --;
                left ++;
            }
        }
        
        if(minleft == -1) return "";
        return s.substring(minleft, minleft+minWin);
    }
}
