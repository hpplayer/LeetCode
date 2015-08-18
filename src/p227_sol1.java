import java.util.Stack;
/*
Basic Calculator II 

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.
*/

/**
 * This is my Original AC solution without help
 * I use two stacks as used in sol224_sol3.
 * sol1 and sol2 in that problem uses the property of (), so they are not easy to translate them towards this problem
 * In this solution, one stack is used to put operators, another stack is used to put numbers
 * I found we need to get the result of * and / before put + into the operator stack, otherwise we may run +/- first
 * Example: 3 + 2*2, if we don't calculate 2*2 before put into stack, we will get (3+2)*2
 * So each time we found +/-, we will start calculate result in stack.
 * Actually, it is not enough, since we may have several * and / before reach a +/-. We have to calculate each of them before 
 * put + into the stack
 * Example: 3 + 2*2*3, if only put first * into the stack, then we may have (3+4)*3
 * So we have a while loop to pop out all *and / inside the +/- block
 * 
 * Also we need to notice that to keep +/3 in order, we have to push elements from the tail to beginning, which means when we calculate 
 * result, the first pop element is the first element in operation, the second pop element is the second element in operation
 * 
 * Because we use two stacks to play with operators and integers, the space and time complexity is not good
 * 
 * sol2 lists a solution that only uses one stack
 * sol3 optimized sol2 that only uses one variable to mimic the operation in stack
 * sol4 is the python version of sol1
 * sol5 is the python version of sol2
 * sol6 is the python version of sol3
 * 
 * Considering those solutions, sol3 is the best solution due to its O(1) space complexity and O(n) time complexity
 * @author hpPlayer
 * @date Aug 17, 2015 9:17:41 PM
 */
public class p227_sol1 {
	  public int calculate(String s) {
	        Stack<Integer> num_s = new Stack<Integer>();
	        Stack<Character> op_s =new Stack<Character>();
	        for(int i = s.length()- 1; i >= 0; i--){
	            if(Character.isDigit(s.charAt(i))){
	                String num = "";
	                while(i >= 0 && Character.isDigit(s.charAt(i))){
	                    num = s.charAt(i) + num;
	                    i--;
	                }
	                i+=1;//put 1 back
	                //System.out.println(num);
	                num_s.push(Integer.valueOf(num));
	            }else if(s.charAt(i) == '/'){
	            	op_s.push(s.charAt(i));
	            }else if (s.charAt(i) == '*'){
	                op_s.push(s.charAt(i));
	            }else if( s.charAt(i) == '+' || s.charAt(i) == '-'){
	            	while(!op_s.isEmpty() && (op_s.peek() == '*' || op_s.peek() == '/')){//before add +/- into op_stack, we need pop up all *and / sign
	                	helper(num_s, op_s);
	            	}
	            	op_s.push(s.charAt(i));
	            }
	        }
	        
	        while(!op_s.isEmpty()){
	        	helper(num_s, op_s);
	        }
	        
	        return num_s.pop();
	    }
	    
	    public void helper(Stack<Integer> num_s, Stack<Character> op_s){
	    	int prev = num_s.pop();
	    	if(num_s.isEmpty()){//no */ after the +/-
	    		num_s.push(prev);
	    		return;
	    	}
	    	int post = num_s.pop();
	        switch (op_s.pop()){
	        	case '*':
	        		num_s.push(prev*post);
	        		break;
	        	case '/':
	        		num_s.push(prev/post);
	        		break;
	        	case '-':
	        		num_s.push(prev-post);
	        		break;
	        	case '+':
	        		num_s.push(prev + post);
	        		break;
	        	default:
	        		break;
	        }
	    }
}
