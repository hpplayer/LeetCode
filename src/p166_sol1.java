import java.util.HashMap;

/*
Fraction to Recurring Decimal

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
*/	
		
/**
 * This problem requires some knowledge of math, and will contain a lot of extreme case
 * Firstly, how to detect recurring Decimal?
 * As long as we found a repeat remainder, we will have recurring decimal
 * This suggest us to create a visited hashset to record all visited repeat remainder
 * 
 * Here comes the corner case 1, one remainder may map to more than one digit.
 * Example: 1/99, where we get: 0.010101, if calculate on paper, you will find remainder 1 map to quotient 01
 * So we really need a HashMap that can tell us the range of quotient each remainder can be mapped to
 * In our HashMap, our key is the remainder and the value is the length of result before adding the key mapped new quotient
 * In above example, 1 in our map will have: (1, 2), which tells the result has length 2 before we inserting the corresponding 
 * quotient of remainder 1 into the result. So in future, if we found a repeated remainder, we simply insert a "(" in this place
 * and inserting the new quotient which lead us to get the duplicate remainder, then append ")". 
 * In this example, 
 * quotient:  0. -> 0.0 -> 0.01
 * remainder:  1 -> 10  -> 1
 * So we know each remainder 1 will have a repeated quotient of 01, so before we start calculating next round of new remainder 1, we just stop
 * there
 * 
 * Besides, we also have several corner cases that needs to be considered:
 * 1) the negative case, in which we may have two negative input and result would be positive, or one negative input thus result would be positive
 * for convenience, we can record the negative sign first, then convert both to positives, then calculate
 * 2) the numerator would be 0, which we can immediately return "0"
 * 3) the input numerator may be Integer.MIN_VALUE, then the conversation from neg to pos will cause overflow. So we have to cast both
 * inputs to long
 *
 * Last, for convenience, we can calculate the integer parts first, then decide whether to add "." and start decimal part or just
 * return the result
 * 
 * There may be many kinds of different implementation, but the main idea should be same
 * 
 * Sol1 is a decent solution
 * Sol2 is another decent solution with same idea but different implementation
 * 
 * Both are recommended
 * @author hpPlayer
 * @date Sep 9, 2015 10:11:48 PM
 */

public class p166_sol1 {
	public static void main(String[] args){
		//System.out.println("AAA");
		System.out.println(new p166_sol1().fractionToDecimal(-2147483648, 1));
	}
	   public String fractionToDecimal(int numerator, int denominator) {
	        //boundary case    
	        if (numerator == 0 ) return "0";
	        
	        //convert it to long for futher convenience
	        long newNum = numerator;
	        long newDen = denominator;
	        boolean isNeg = false;
	        if(newNum*newDen <0){
	            isNeg = true;
	        }
	        
	        newNum = Math.abs(newNum);
	        newDen = Math.abs(newDen);
	        
	        HashMap<Long, Integer> hs = new HashMap<Long, Integer>();
	        StringBuilder sb = new StringBuilder();
	        if(isNeg) sb.append("-");
	        
	        //must declare quo and remainder to be long, otherwise if the newNum is Integer.MIN_VALUE, the abs value of it, will 
	        //exceed the Integer.MAX_VALUE
	        long quo = newNum/newDen;//quotient
	        long remainder = newNum%newDen;
	        
	        if(remainder == 0){
	            return sb.append(quo).toString();
	        }else{
	            sb.append(quo).append(".");
	            hs.put(remainder, sb.length());// remainder and its end index
	        }
	        
	        newNum = remainder;//reset newNum
	        
	        //remainder is num, num is remainder
	        while(newNum != 0){
	        	//System.out.println(sb.toString());
	            newNum *= 10;//the last remainder (now newNum) will always smaller than newDen, so we increase by *10
	            quo = newNum/newDen;//quotient
	            remainder = newNum%newDen;
	            if(!hs.containsKey(remainder)){
	            	sb.append(quo);
	                hs.put(remainder, sb.length());// remainder and its end index
	            }else{//found repeat remainder
	                //we firstly append current quo to result
	            	//sb.append(quo);
	            	sb.insert(hs.get(remainder), "(").append(quo + ")");
	            	
	                //sb.append(sb.substring(hs.get(remainder)) + "" + quo + ")");
	                break;
	            }
	            
	            //calculate the new newNum
	            //newNum = newNum - quo * newDen; //which is actually newNum %= newDen;
	            newNum %= newDen;
	        }
	        
	        
	        return sb.toString();
	        
	    }
}
