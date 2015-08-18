import java.util.Stack;
/**
 * This algorithm uses a smart loop to convert * and / into integer first
 * During this loop we uses a temp variable num to record current number, we also have previous number in the stack
 * Every time, we found a new sign, we will stop there and update the stack.
 * If the sign is * or /, we will let them combined with immediate neighbor then push to the stack
 * if the sign is + or -, we simply add them to stack without combine with immediate neighbor since we may have * or / after them
 * For +, we just add it without modification, for -, we have to add -num into the stack
 * So after the loop, all & and /signs have been removed.
 * Before calculating the final result, we have to add the last pair of signs and numbers into the stack
 * In next step, we simply sum all numbers in the stack to form our final result
 * 
 * @author hpPlayer
 * @date Aug 17, 2015 9:51:22 PM
 */

public class p227_sol2 {
	public static void main(String[] args){
		String s= "0*0";
		System.out.println(new p227_sol2().calculate(s));
	}
	   public int calculate(String s) {
	        int num = 0;
	        char sign = '+';
	        Stack<Integer> stack = new Stack<Integer>();
	        //following loop will eliminate all * and / in expression
	        for(int i = 0; i < s.length(); i++){
	            char c = s.charAt(i);
	            if(Character.isDigit(c)){
	                num = num*10 + c - '0';
	            }else if (c == '+' || c == '-' || c == '*' || c == '/'){
	            	updateStack(stack,sign,num);             
	                sign = c;//update sign to the newest one
	                num = 0;//reset 0
	            }
	            
	        }
	        
	        //don't forget last pair of sign and num
	        updateStack(stack,sign,num);
	        int result = 0;
	        while(!stack.isEmpty()){
	            result += stack.pop();
	        }
	        
	        return result;
	    }
	   
	   public void updateStack(Stack<Integer> stack, char sign, int num){
		 //we face a new sign, need calculate based on previous sign and previous int in stack and current Num
           switch(sign){
           //for * and /, we need to calculate its result based on its immediate neighbor
           case '*':
               stack.push(stack.pop() * num);
               break;
           case '/':
               stack.push(stack.pop() / num);
               break;
           //for + and -, we can't calcuate its result based on its immediate neighbor since we may have * or / after it
           //like 3 + 5*3, if we use stack.push(stack.pop) here we will get 8 * 3
           case '-':
               stack.push(-num);
               break;
           case '+':
               stack.push(num);
               break;
           }
	   }
}
