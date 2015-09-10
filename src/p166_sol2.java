import java.util.HashMap;
/**
 * The main idea is same with sol1, but with a little different implementation
 * The logic is clear:
 * 1) we firstly get the sign: pos or neg
 * 2) we then get the integer part of result
 * 3) we use remainder as the new numerator, denominator will keep the same
 * 4) we use a hashMap to record the index where we insert the quotient that leads to current result
 * 5) if we found the repeat remainder, then we end the loop, add "(" before the quotient that leads to current result, and add ")" to the end
 * 6) Or if the remainder is 0, we can return our result immediately
 * 
 * Remark:
 * Be careful about the overflow of integers! 
 * @author hpPlayer
 * @date Sep 9, 2015 10:56:41 PM
 */

public class p166_sol2 {
	public static void main(String[] args){
		//System.out.println("AAA");
		System.out.println(new p166_sol2().fractionToDecimal(-1, -2147483648));
	}
	
	  public String fractionToDecimal(int numerator, int denominator) {
	        if(numerator == 0) return "0";
	        long newNum = numerator, newDen = denominator;
	        StringBuilder sb = new StringBuilder();
	        boolean isNeg = false;
	        //get neg sign
	        if(newNum * newDen < 0){
	            isNeg = true;
	        }
	        
	        newNum = Math.abs(newNum);
	        newDen = Math.abs(newDen);
	        
	        sb.append(isNeg? "-" : "");
	        sb.append(newNum/newDen);
	        
	        //the key part of this problem remainder 
	        long remainder = newNum%newDen;
	        
	        if(remainder == 0) return sb.toString();
	        
	        //if we have decimal parts
	        sb.append(".");
	        
	        HashMap<Long, Integer> hs = new HashMap<Long, Integer>();
	        
	        //if we have not touched repeat remainder yet
	        while(!hs.containsKey(remainder)){
	            hs.put(remainder, sb.length());
	            sb.append(10*remainder/newDen);
	            remainder = 10*remainder%newDen;
	            if(remainder == 0){//if new Remainder is 0, then we return immediately
	                return sb.toString();
	            }
	        }
	        
	        //each while loop has inserted the quotient that lead to next remainder, so we don't need insert it
	        sb.insert(hs.get(remainder), "(");
	        sb.append(")");
	        
	        return sb.toString();
	    }
}
