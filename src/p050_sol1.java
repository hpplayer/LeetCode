import java.util.HashMap;
/*Pow(x, n) 
 * 
Implement pow(x, n).
*/
/**
 * This is a pure math problem
 * Code below is my original code without help
 * To reduce steps exponentially, we can get the idea that 
 * if we got even power then we can have
 * 3^6 = 3^3 * 3^3 = 9^3
 * if we got odd power then we can have
 * 3^5 = 3^2 * 3^2 *3^1 = 9^2&3 
 * if we got negative power then 
 * we simply let power be positive
 * and let result be 1/result
 * 
 * For calculating the result of each level, I used recursive way
 * 
 * I didn't see other better solutions
 * Other code may be more concise or maybe use iterative way, 
 * but the basic idea is same
 * 
 * Remark:
 * A post says we can use bit manipulation to get faster calculation:
 * 5/2 = 5>>1
 * 5%2 == 1 can be: (5&1) == 1
 * 
 * Time complexity by exponential reduction,
 * time complexity has been decreased from O(n) to O(log(n))
 * sol2 shows a more concise recursive way
 * sl3 shows a iterative way
 * @author hpPlayer
 * @date Apr 10, 2015 11:40:47 PM
 */
public class p050_sol1 {
	public static void main(String[] args){
		System.out.println(pow(-1,Integer.MIN_VALUE));
		System.out.println(pow2(5.0,5));
		System.out.println(hs);
	}
    static HashMap<Integer, Double> hs= new HashMap<Integer, Double>(); 
    public static double pow(double x, int n) {
        if(hs.containsKey(n)){
        	System.out.println("im here");
        	return hs.get(n);
        }
        if(n == 0){
           hs.put(0, 1.0);
           return 1;
        } 
        int next = Math.abs(n)>>1;
        double offset =1 ;
        if((Math.abs(n) & 1) == 1){
        	offset = x; 
        }
        if(n < 0){
           hs.put(n, 1.0/(pow(x, Math.abs(next)) * pow(x, Math.abs(next))*offset));
        }else{
            hs.put(n, pow(x, next) * pow(x, next)*offset);
        }
      
        return hs.get(n);
    }
    
    public static double pow2(double x, int n) {  
    	   double result = 1;  
    	   for (int m = Math.abs(n); m > 0; x *= x, m >>= 1) {  
    		   System.out.println(m + " " + x);
    	     if ((m & 1) == 1) result *= x;  
    	   }  
    	   return (n >= 0) ? result : 1.0 / (result);  
    	 }  
}
