/**
 * concise recursive way, without cache
 * @author hpPlayer
 * @date Apr 10, 2015 11:56:03 PM
 */
public class p050_sol2 {
	public double pow(double x, int n) {
		if(n==0) return 1;
		if (n< 0){
			n = -n;//may cause overflow, if n = Integer.MIN_VALUE 
			x = 1/x;
		}
		return n%2 == 1? x*pow(x*x, n/2) : pow(x*x, n/2);
	}
}
