/*****
 * Remark: In this solution, the way I got the maximum number before dividend is get the number lager than it then trace back,
 * it may not good since it will make our divisor > Integer.MAX_VALUE and force us to use long type to store type, if we have more 
 * time, we can use a better way does not need to get the number larger than dividend
 * 
 * @author hpPlayer
 *
 */
public class p029 {
	/*
	 * use bit shift. << means * 2, >> means /2, << AbsDivisor multiple times
	 * (assume n times) to get the maximum number before AbsDivident record the
	 * count, since it means AbsDivisor * 2^n then AbsDivident = AbsDivident - AbsDivisor *
	 * 2^n, repeat above steps until we got AbsDivident = 0 finally add up AbsDivisor * 2^n1
	 *  + AbsDivisor * 2^n2
	 *  
	 *  A simple example in decimal:
	 *  22 = 2 * 10 + 2 * 1
	 *  Firstly we get 10, since 2 *10 is the largest number before 22, 
	 *  Then 22 - 20 = 2, and we get 1, since 2 * 1 =22 -20 =2;
	 */
	public static void main(String[] args){
	int a = divide(-2147483648, 2);
		System.out.println("MIN_VALUE: " + Integer.MIN_VALUE + " a: " + a);
		//System.out.println("MIN_VALUE: " + Integer.MIN_VALUE + " b: " + Integer.MAX_VALUE/1);
	}
	
	public static int divide(int dividend, int divisor){
		long result = 0, count = 1;//need use long result, since if the input is MAX_VALUE, then sum will > MAX_VALUE
		boolean neg = (dividend< 0 ^ divisor < 0);//store the pos/neg of original input
		if(divisor == 0) return Integer.MAX_VALUE;
		if(divisor == Integer.MIN_VALUE){
			if(dividend == Integer.MIN_VALUE){
				return 1;
			}
			return (int) result;//no int can be larger than abs(Integer.Min_VALUE);
		}
		if(dividend == Integer.MIN_VALUE){
			if(divisor == -1){
				return Integer.MAX_VALUE;//we cant return - Integer.MIN_VALUE, cuz it is out of boundary
			}else if (divisor == 1){
				return Integer.MIN_VALUE;
			}
			//other case, we need reduce the value of abs(dividend)
			dividend = dividend + Math.abs(divisor);//the abs value becomes smaller
			result ++;//our calculation is based on abs(value), we add a +value to make it smaller, so we need add the reducing part back
		}
		//all boundary case handled, now we can deal with normal case

		long AbsDividend = Math.abs(dividend);
		long AbsDivisor = Math.abs(divisor);// we need long here since we may get a number > Integer.MAX_VALUE when AbsDivisor << =1

		while(AbsDividend>=AbsDivisor){// we need add == case, since the AbsDividend may = AbsDivisor, without == case, we will return 0 result
			count <<=1;
			AbsDivisor <<= 1;
			if(AbsDivisor > AbsDividend){// > case
					AbsDivisor >>= 1;//get one digit back
					count >>= 1; //get one digit back
					AbsDividend -= AbsDivisor;
					result += count; //add count (2^a + 2^b+...)
					AbsDivisor =  Math.abs(divisor); //reset AbsDivisor
					count = 1;//reset count
				if(AbsDivisor == AbsDividend){// == case, from above > case output or may comes from original== input 
					result += count;
					break;
				}
			}
		}

		return (int) (neg? -result : result);
	}
}
