/**
 * This is the official solution towards this problem
 * Basically, it uses a trick to compare the first and last digits of the input int.
 * The trick is getting the length of int first. How? increase the variable div until x/div < 10, then we can guarantee we have same 
 * length of div as the length of input
 * 
 * The next step is trying to get the first and last digits of the input. How? for last digit, it's easy. Just use %10
 * For first digit, we need the help of variable div. How? x/div will give us the first digit
 * 
 * Then, if first digit = last digit, we need to chop the first and last digit and do the same thing to the remaining digits.
 * How to chop the first and last digit? Chopping first digit can be done by x%div while chopping last digit can be done by x/10
 * After chopping digits, don't forget to adjust the div as well (div /= 100).
 * 
 * This approach is pure math, and very powerful!
 * @author hpPlayer
 * @date Jul 21, 2015 1:47:14 PM
 */
public class p009_sol2 {
	public static void main(String[] args){
		System.out.println(new p009_sol2().isPalindrome(2147483647));
	}
	
    public boolean isPalindrome(int x) {
    	if (x < 0) return false;
        int div = 1;
        
        while(x/div >= 10){//div is increased like 10->100->1000, when x/div >= 10, it means x and div have same number of digits
        	div *= 10;
        }
        
        while( x != 0){
        	int l = x/div;//l is the leftmost digit in x
        	int r = x%10; //r is t he rightmost digit in x
        	if(l != r) return false;
        	
        	x = (x%div)/10;//x%div will get second-last digits, then by "/10", we will remove last digit
        	div /= 100;//after chop two digits, div need shrink by 100
        }
        
        return true;
    }
}
