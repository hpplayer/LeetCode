/*
Add Binary 

Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100"
*/

/**
 * This is my original AC solution without help
 * my idea is that:
 * The binary each bit can only contains at most value 0 or 1
 * Thus, if after we update the value, the current value is > 2, then we will have overflow
 * Now in a normal summation, we will have 3 parameters:
 * int from a, int from b and carry from overflow
 * if their sum is < 2, then we just set current bit value in result to be the sum, and we set carry to 0
 * if their sum is >= 2, then we will set current bit value to be sum - 2, and set carry to 1
 * I say normal means we assume two strings both have values in front
 * 
 * However if at some points, we reach the end of one string, then we have to do the same thing to the longer string.
 * We may still have carry from previous part, and thus may cause overflow. Like a: 11111111 b: 11
 * 
 * Finally, after we have visited both strings, we may still have carry, in certain case, we have to add an additional 1 in result
 * 
 * 
 * Sol1 is my intuitive solution
 * If there is some optimizations, then it may include combine three while loops into one loop
 * and change the way that I get carry and result, and use StringBuilder to build string
 * 
 * Sol2 applies those optimizations so the code is shorter and more beautiful, thus Sol2 is recommended!
 * 
 * @author hpPlayer
 * @date Sep 3, 2015 11:04:01 PM
 */
public class p067_sol1 {
	public static void main(String[] args){
		String a = "101111";
		String b = "10";
		System.out.println(new p067_sol1().addBinary(a, b));
	}
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        String result = "";
        while(i >= 0 && j >= 0){
            int i1 = a.charAt(i) - '0';
            int i2 = b.charAt(j) - '0';
            if(i1 + i2 + carry <= 1){
                result = i1 + i2 + carry + result;
                carry = 0;
            }else{
                result = i1 + i2 + carry - 2 + result;
                carry = 1;
            }
            i--;
            j--;
        }
        
        while (i >= 0){
            int c = a.charAt(i) - '0';
            if(c + carry < 2){
                result = c + carry + result;
                carry = 0;
            }else{
                result = c + carry - 2 + result;
                carry = 1;
            }
            i--;
        }
        
        while (j >= 0){
            int c = b.charAt(j) - '0';
            if(c + carry < 2){
                result = c + carry + result;
                carry = 0;
            }else{
                result = c + carry - 2 + result;
                carry = 1;
            }
            j--;
        }       
        
        if(carry != 0) result = 1 + result;
        return result;
        
    }
}
