/*Roman to Integer 
 * 
 * 
Given a Roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
 *
 */


/**
 * My own solution, although I got some help from Internet
 * The basic idea is:
 * 1) get the biggest Roman key (it may be single or double digits)
 * 2) subtract this key from original string and add it to the result
 * 3) repeat 1) and 2) until string length becomes zero
 * 
 * This solution is similar to p012, but the official solution is using another observation. 
 * Please see sol2
 * Remark:
 * when compare strings, we must use str1.equals(str2), otherwise we may cause inequality of two same strings
 * @author hpPlayer
 * @date Jul 23, 2015 1:48:19 PM
 */


public class p013_sol1 {
	public static void main(String[] args){
		System.out.println(new p013_sol1().romanToInt("XI"));
	}
    public int romanToInt(String s) {
        String[] keys = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int result = 0;
        while(s.length() > 0){
        	String two = "";
        	if(s.length() >= 2){
        		two = s.substring(0, 2);
        	}
            String one = s.substring(0, 1);
           // System.out.println(s.substring(0,1));
            for(int i = 0; i < keys.length; i++){
                if (keys[i].equals(two)){
                    result += values[i];
                    s = s.substring(2);
                    break;
                }else if (keys[i].equals(one)){
                 //  System.out.println("im here");
                   result += values[i];
                   s = s.substring(1);
                   break;
                }
            }
        }
        
        return result;
    }
}
