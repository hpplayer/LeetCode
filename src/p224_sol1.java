import java.util.Stack;
/*
Basic Calculator 

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.
*/

/**
 * The difficulties of this problem are how to deal with values after the sign
 * Recursion? maybe, but it is hard to implement, since the sign may only affect the number after it not the whole sequence.
 * Iteration? maybe, but it is hard to implement, you need two pointers, one points to the sign or the start digit of next number
 * another pointer points to the end digit of next number, but how about the ' '? The next char after sign may be ' ' or start of next 
 * number, so maybe we need three pointers? Not even we also need to consider the '()' cases
 * 
 * So here comes this solution. The most beautiful part is the trick that we push previous result and sign into the stack. So after
 * we done calculation inside the parentheses, we can simply pop the top two elements in the stack to add it into the result, which may
 * be the temp result inside another parentheses.
 * 
 * Basic idea:
 * 1) Scan each char in the string
 * 2) there will be five cases:
 * - digit: it should be one digit from the current number
 *  - '+': number is over, we can add the previous number and start a new number
 *  - '-': same as above
 *  - '(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
 *  - ')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis,
 *         second is the temporary result before this pair of parenthesis. We add them together.
 * 3) Finally, if last number is not ended with ')', then we need manually add it into our result
 * 
 * sol1 lists an iterative solution with stack
 * sol2 lists a recursive solution without stack
 * sol3 lists an iterative solution with two stacks
 * sol4 is the python version of sol1 and it runs very fast!
 * sol5 is the python version of sol2 and I have to redefine constructor to set global variable i
 * sol6 is the python version of sol3
 * 
 * Among those solutions, sol1 is the best solution regarding the time and space complexity!
 * @author hpPlayer
 * @date Aug 17, 2015 11:25:41 AM
 */

public class p224_sol1 {
	public static void main(String[] args){
		//System.out.println(new p224_sol1().calculate(" 2-1 + 2 "));
		//System.out.println(Integer.valueOf("1".substring(1, 1)));
		System.out.println(new p224_sol1().calculate(" 2-1 + 2 "));
	}
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;//current result, will be pushed to stack
        int number = 0;//temp number value, will be updated on the fly and will be pused to stack
        int sign = 1;//key part, we need to record the sign before '('
        for(char c: s.toCharArray()){
            if (Character.isDigit(c)){
                number = 10 * number + (c - '0');
            }else if (c == '+'){
                //end of number updating, we will add it into our result, based on sign updated before
                result += sign * number;
                sign = 1;//reset sign
                number = 0;//reset number
            }else if (c == '-'){
                result += sign * number;
                sign = -1;//reset sign
                number = 0;//reset number
            }else if (c == '('){//need push sign and result to the stack, so in later calculation, we will always pop top two values
                //push current value (before '(' ) into stack
                stack.push(result);
                //push sign to the top of stack, so we know what should be 
                stack.push(sign);
                //reset result and sign, so it will be a new start for expressions inside parentheses
                result = 0;
                sign = 1;
            }else if (c == ')'){
                //get result inside the parentheses
                result += sign * number;
                number = 0;
                //update current result inside parentheses based on the sign before '(', stack.pop() return the sign before parentheses
                result *= stack.pop();
                //update result to our final output, stack.pop() return previous calculation result
                result += stack.pop();
            }
        }
        //if single number or no '()', no '+', '-', ')', we will not add it/last number into the result, so double check here
        if(number != 0){
            result += sign*number;
        }
        
        return result;
    }
}
