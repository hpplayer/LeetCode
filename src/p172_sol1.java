/**
 * This problem is a pure math problem and mainly based on observation
 * My idea here is not fully correct.
 * I thought each 10 integers will provides 2 0's
 * but I forget the case that some numbers will provide multiple 5s
 * Since we have bunch of 2s in n! from even numbers, those numbers can provide extra 5s
 * 
 * I rewrite my code and correct the error, but it still not looks good.
 * Ref to sol2, sol3, sol4 for best solutions
 * 
 * 
 * Sol1 is my code, though I have updated it to pass the test cases but the flow is not clear
 * Sol2 is a recursive solution with short code, but may not easy to understand
 * Sol3 is the iterative implementation of sol2.
 * Sol4 is another interative solution but with a more understandable way
 * 
 * Both sol2 and sol3 use same idea and changing input n
 * Sol4 is the straighforward way.
 * 
 * Anyway sol2-sol4 are all recommended, so we can understand this problem better
 * @author hpPlayer
 * @date Sep 6, 2015 11:51:24 PM
 */
public class p172_sol1 {
	public static void main(String[] args){
		System.out.println(trailingZeroes(1808548329));//should output 7
	}
	//failed because integers end with 2,5 or 1, 0 may generate a number end with more than 1 zero if it is large enough
    public static int trailingZeroes(int n) {
        if (n < 5) return 0;
        if (n < 10) return 1;
        
        int offset = 0;
        if(n%10 < 5){
        	offset = 0;
        }else{
        	offset = 1;
        }
        
        int count = 0;
        int temp = 5;
        while (temp < n ){
        	if(temp > Integer.MAX_VALUE/5){
        		count += n/temp-1;
        		break;
        	}
        	temp *= 5;
        	count += n/temp;
        }
        //System.out.println(count);
        return n/10 * 2 + offset + count;
    }
    
    public static int trailingZeroes2(int n) {
        if (n < 5) return 0;
        if (n < 10) return 1;
        
        int offset = 0;
        if(n%10 < 5){
        	offset = 0;
        }else{
        	offset = 1;
        }
        
        int pow5 = (int) Math.pow(n, 0.2);
        
        return n/10 * 2 + offset + pow5;
    }
}
