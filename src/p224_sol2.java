/**
 * This is the recursive solution towards this problem.
 * They key part is the use of global index i.
 * With i, we can scan the whole string during the recursion while avoid many duplicate visit cases
 * The recursion will return back once we finished the scan of whole string or current parentheses end.
 * Thanks to the global index i, once we got returned value from parentheses we will continue scan the rest of sequence after the 
 * Parentheses, in other words, the nested parentheses will be handled by recursive calls.
 * @author hpPlayer
 * @date Aug 17, 2015 1:40:51 PM
 */
public class p224_sol2 {
	public static void main(String[] args){
		//System.out.println(new p224_sol1().calculate(" 2-1 + 2 "));
		//System.out.println(Integer.valueOf("1".substring(1, 1)));
		System.out.println(new p224_sol2().calculate("(1)"));
	}	
		
	  //have to make index i global, so we will modify it in different layers of recursion
	  int i = 0;
	  public int calculate(String s) {
		  boolean isNeg = false;
		  int result = 0;
		  //stop the loop when we done the whole string, or the expression inside a parentheses
		  while (i < s.length() && s.charAt(i) != ')'){
			  char c = s.charAt(i);
			  if (c == ' ' || c == '+' || c == '-'){
				  if(c == '-'){
					  isNeg = true;
				  }
				  i++;
			  }else if (c == '('){
				  i++;
				  //i has been increased, so we can directly use s.substring(i)
				  //we will add the value based on the result inside the parentheses
				  //we have a global index pointer so need to pass substring to next recursion, just pass s
				  result += isNeg? -calculate(s) : calculate(s);
				  isNeg = false;//reset
			  }else{//get digits
				  int num = 0;
				  //building the number
				  while (i < s.length() && Character.isDigit(s.charAt(i))){
					 num = 10*num + (s.charAt(i) - '0');
					 i++;
				  }
					 //we will add the number based on the sign before it
					 result += isNeg? -num : num;
					 isNeg = false;

			  }
		  }
		  //since the loop is ended either the expression inside the parentheses is ended or the whole expression is ended
		 //so we need skip current ')'
		  i++;
		  //System.out.println(result);
		  return result;
	  }
}
