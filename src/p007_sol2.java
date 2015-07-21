/**
 * This is my math version of the solution
 * This approach is very similar to official solution
 * i.e. use / and % operators.
 * My version uses double type to catch overflow/underflow, and return 0 if it outfit the max/min value of integer
 * 
 * Because / and % operators keep +/- sign, we dont need to consider about them in the calculation. 
 * @author hpPlayer
 * @date Jul 20, 2015 5:41:02 PM
 */
public class p007_sol2 {
	public static void main(String[] args){
		System.out.println(Integer.MAX_VALUE);
		System.out.println(new p007_sol2().reverse(-10));
	}
    public int reverse(int x) {
    	/*
        if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE){
        	return 0;
        }
        */
    	double temp = 0;
    	
    	//still has digits left
        while(x/10 != 0){
        	temp = temp * 10 + x%10;
        	x = x/10;
        }
        temp = temp * 10 + x%10;
        /*
         * above code can also be combined and written as:
         * 
            while(x != 0){
        		temp = temp * 10 + x%10;
        		x = x/10;
        	}
         */
        if (temp >= Integer.MAX_VALUE || temp <= Integer.MIN_VALUE) return 0;
        return (int)(temp);
    }
}
