/**
 * Iterative way, also use bit manipulation to replace "/" and "%"
 * @author hpPlayer
 * @date Apr 10, 2015 11:55:58 PM
 */
public class p050_sol3 {
	public static void main(String[] args){
		System.out.println(pow(3, -1));
	}
    public static double pow(double x, int n) {
    	double result = 1;
    	for(int m = Math.abs(n); m > 0; x*=x, m>>=1){//we treat +n and -n both as +n, let m = m/2 x = x*x each loop
    		if((m&1) == 1) result *= x;//check if we need add extra a, if power is odd
    	}
    	return (n>=0) ? result : 1.0/result;//since we use Math.abs(n) above, if n < 1, we need reverse the result
    }
}
