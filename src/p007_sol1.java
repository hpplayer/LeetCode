/*
 * 
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

 */
/**
 * My original solution without help.
 * I use String type as the base, so it is not hard to solve this problem
 * I have thought about the range problem, but neglected the case like 1230, 1000, etc.
 * For pure math solution, please see other solutions.
 * @author hpPlayer
 * @date Jul 17, 2015 6:46:42 PM
 */
public class p007_sol1 {
	public static void main(String[] args){
		System.out.println(reverse(-2147483648));
	}
    public static int reverse(int x) {
    	if (x == Integer.MIN_VALUE){
    		return 0;
    	}
    	boolean pos = x < 0? false : true;
        String temp = Math.abs(x) + "";
        String result = "";
        for(int i = temp.length() - 1; i >=0; i--){
            result += temp.charAt(i);
        }
       // System.out.println(temp);
        //System.out.println(result);
        double temp2 = Double.valueOf(result);      		
        if (temp2 > Integer.MAX_VALUE) return 0;
        return pos? Integer.valueOf(result) : -Integer.valueOf(result);  
    }
}
