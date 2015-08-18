/**
 * My iterative solution, similar idea
 * if the final remainder is not among 2,3,5, we will return false, otherwise return true;
 * @author hpPlayer
 * @date Aug 18, 2015 2:51:08 PM
 */
public class p263_sol2 {
	   public boolean isUgly(int num) {
	        if (num <=0) return false;
	        if (num == 1) return true;
	        while(true){
	            if(num == 2 || num == 3 || num == 5) return true;
	            if(num%2 == 0){
	                num /= 2;
	            }
	            else if(num%3 == 0){
	                num /= 3;
	            }
	            else if(num%5 == 0){
	                num /= 5;
	            }else{
	                return false; 
	            }
	        }
	    }
}
