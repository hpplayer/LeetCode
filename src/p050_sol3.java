/**
 * Iterative way, also use bit manipulation to replace "/" and "%"
 * 
 * Update(4/12/15):
 * I don't know why we store the values to result only we have odd m,
 * I searched several iterative approaches and they use the same way, 
 * Let's analyze it:
 * 
 * 2^11
 * m = 11, x = 2, now result *= 2,
 * m>>1 = 11/2 = 5, x*x = 4 now result = 2*4 = 8
 * m>>1 = 5/2 = 2,x*x = 16 now result is not updated and = 8
 * m>>1 = 2/2 = 1, x*x = 256, now result = 256*8 = 2048
 * 
 * 2^9
 * m = 9, x = 2, now result *=2 = 2, 
 * m>>1 = 9/2 = 4, x *= x = 4, now result is not updated and = 2
 * m>>1 = 4/2 = 2, x*= x = 16, now result is not updated and = 2
 * m>>1 = 2/2 = 1, x*= x = 256, now result *= 2 = 2*256 = 512
 * loop ended 
 * 
 * 2^7
 * m = 7, x = 2, now result *=2 = 2, 
 * m>>1 = 7/2 = 3, x *= x = 4, now result *= 4 = 8
 * m>>1 = 1, x*= x = 16, now result *= 8*16 = 128
 * loop ended 
 * 
 * 
 * 2^4
 * m = 4, x = 2, now result is not updated and =1
 * m>>1 = 4/2 =2, x *= x = 4, now result is not updated and =1
 * m>>1 = 2/2 = 1, x*x = 16, now result *= 16 = 16.
 * 
 * 2^5 
 * m = 5, x =2, now result *=2 
 * m>>1 = 5/2 = 2, x *= x = 4, now result is not updated and = 2
 * m>>1 = 2/2 = 1, x *= x = 16, now result *= 2 * 16 = 32
 * 
 * so we can find store values in result when x is odd should be correct
 * Although the mechanism is unclear, but it is correct.
 * 
 * Update:(4/14/15):
 * Why update result only when we have odd power number?
 * Think in this way:
 * 2^11 = 2 * 2^10 = 2 * 4^5 = 2* 4 * 4^4 = 2*4 * 16^2 = 2 * 4 * 256^1. 
 * We can find the result is actually the product of "extra term" we get when power is odd
 * 2 is extra term we get when power = 11
 * 4 is extra term we get when power = 5
 * 256 is extra term we get when power = 1
 * So we should upadte result whenever the power is odd
 * @author hpPlayer
 * @date Apr 10, 2015 11:55:58 PM
 */
public class p050_sol3 {
	public static void main(String[] args){
		System.out.println(pow2(2,7));
		System.out.println(pow2(2,4));
		//System.out.println(7/2);
	}
    public static double pow(double x, int n) {
    	double result = 1;
    	for(int m = Math.abs(n); m > 0; x*=x, m>>=1){//we treat +n and -n both as +n, let m = m/2 x = x*x each loop
    		if((m&1) == 1) result *= x;//check if we need add extra a, if power is odd, this also outputs the result when n == 1
    	}
    	return (n>=0) ? result : 1.0/result;//since we use Math.abs(n) above, if n < 1, we need reverse the result
    }
    
    public static double pow2(double x, int n) {
    	double result = 1;
        if (n < 0){
            n = -n;
            x = 1/x;
        }
        for(int m = n; m > 0; x *=x, m>>=1){
        	//System.out.println(m);
        	
            if((m&1) == 1) result *=x;//magic operation
            //System.out.println(result);
        }
        return result;
    }
}
