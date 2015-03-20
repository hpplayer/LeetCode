import java.util.HashMap;
/**
 * Recursive way.. the main idea is right, but it does not handle the special case properly
 * i.e. 0 case, if we have 150 then it can not be parsed
 * @author hpPlayer
 * @date Mar 19, 2015 7:35:43 PM
 */
public class p091_sol1 {
	public static void main(String[] args) {
		String a = "1168963884134125126536966946586868418993819971673459188478711546479621135253876271366851168524933185";
		String b = "9371597631128776948387197132267188677349946742344217846154932859125134924241649584251978418763151253";
		String c = "150";
		p091_sol1 test = new p091_sol1();
		System.out.println(test.numDecodings(c));
	}
	
	public int numDecodings(String s) {
		if(s.length() == 0) return 1;
		int count = 0;
		if (s.length() >= 2 && isValid(s.substring(0, 2))) {
			count += numDecodings(s.substring(1)) + numDecodings(s.substring(2));// use one or two digits
		} else {
			count += numDecodings(s.substring(1));
		}
		return count;
	}
	
	public int numDecodings2(String s, HashMap<String, Integer> hs) {
		if(s.length() == 0) return 1;
		if(hs.containsKey(s)) return hs.get(s);
		int count = 0;
		if (s.length() >= 2 && Integer.valueOf(s.substring(0, 2)) <= 26) {
			count += numDecodings(s.substring(1)) + numDecodings(s.substring(2));// use one or two digits
		} else {
			count += numDecodings(s.substring(1));
		}
		hs.put(s, count);
		return count;
	}
	
    public boolean isValid(String s){
    	if(s.charAt(0) == '0') return false;
    	return Integer.valueOf(s) >=1 && Integer.valueOf(s) <= 26;
    }
}
