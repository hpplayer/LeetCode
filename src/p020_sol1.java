import java.util.Stack;
/*
Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/


/**
 * This is my original AC solution
 * For parentheses, it is very easy to come up with stack
 * This problem does not have any trap, so it can be easily solved.
 * 
 * Remark:
 * When I was trying to solve this problem, I thought the input is "<>" instead of "[]", so be careful about input
 * 
 * A beautiful DP solution can be found in sol2
 * A stack approach but with better code management can be found in sol3
 * @author hpPlayer
 * @date Apr 16, 2015 4:38:36 PM
 */

public class p020_sol1 {
	public static void main(String[] args){
		String s = "()[]{}";
		System.out.println(isValid(s));
	}
	  public static boolean isValid(String s) {
	        Stack<Character> stack = new Stack<Character>();
	        for(int i = 0; i < s.length(); i++){
	            if(s.charAt(i) == ')'){
	                if(stack.isEmpty() || stack.peek() != '('){
	                    return false;
	                }else{
	                    stack.pop();
	                }
	            }
	            
	            else if(s.charAt(i) == '}'){
	                if(stack.isEmpty() || stack.peek() != '{'){
	                    return false;
	                }else{
	                    stack.pop();
	                }
	            }
	            else if (s.charAt(i) == ']'){
	                if(stack.isEmpty() || stack.peek() != '['){
	                    return false;
	                }else{
	                    stack.pop();
	                }
	            }
	            else{
	            	System.out.println(s.charAt(i));
	                stack.push(s.charAt(i));
	            }
	        }
	       // System.out.println(stack.size());
	        return stack.isEmpty();
	    }
}
