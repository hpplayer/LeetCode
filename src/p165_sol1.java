/**
 * This problem is acutally hard than it looks like, since we have to deal with many boundary case:
 * like 1 and 1.0 should be equal
 * like 0 and 0.01 should return -1(test case not included in leetcode yet)
 * 
 * The basic idea is to compare digit by digit of input strings
 * We split each string based on ".", then compare from 0 to Math.min(s1.length, s2.length)
 * if reach end, we then check if the ended char in longer string is "0"(assume we only have 0 case, no 00 or 000 case in mid or end), 
 * if yes, return 0, otherwise return result based on which string has longer length. 
 * Of course during the loop, if any string has larger digit, we directly return result
 * 
 * Remark: how to let string split based on "."? split accept regular expr, which means "." stands for single char
 * so we have to use some tricks to avoid this usage. Solution is using "\\.", first \ is used to let second \ not be used 
 * as expr, second \ is used to let . not be used as expr.
 * 
 * I picked another solution which may seems more concise in sol2
 * @author hpPlayer
 * @date Apr 5, 2015 10:48:15 PM
 */
public class p165_sol1 {
	public static void main(String[] args){
		System.out.println("\\");
		System.out.println(compareVersion("0", "0.01"));
		System.out.println(compareVersion("1", "1.0"));
	}
    public static int compareVersion(String version1, String version2) {
    	String[] s = version1.split("\\.");
        //System.out.println(Arrays.toString(s));
        String[] s2 = version2.split("\\.");
        if(version1.equals("0")){
        	for(String str: s2){
        		if(Integer.valueOf(str) > 0) return -1;
        	}
        	return 0;
        }
        
        if(version2.equals("0")){
        	for(String str: s){
        		if(Integer.valueOf(str) > 0) return 1;
        	}
        	return 0;
        }
       // if(s.length > s2.length) return 1;
       // if(s.length < s2.length) return -1;
       // System.out.println(Arrays.toString(s2));
        for(int i = 0; i < Math.max(s.length, s2.length); i++){
        	if(i >= Math.min(s.length, s2.length)){
        		if(s.length > s2.length && s[i].equals("0")|| s2.length > s.length && s2[i].equals("0")){
        			return 0;
        		}
        		return s.length > s2.length? 1 : -1;
        	}
        	if(Integer.valueOf(s[i]) > Integer.valueOf(s2[i])) return 1;
        	if(Integer.valueOf(s[i]) < Integer.valueOf(s2[i])) return -1;
        }
        return 0;
    }
}
