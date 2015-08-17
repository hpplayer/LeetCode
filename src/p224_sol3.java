import java.util.Stack;
/**
 * This problem uses two stacks technique, in which we use one stack to store values, another stack stores signs
 * We need to start the sequence from the tail, since only following this way our order will be correct.
 * Example 
 * 1 + 1 - 2:
 * From left to right
 * int_stack: 1->1->2
 * ops_stack: +->-
 * Our calculation would thus be 1-2 + 1 which return 0
 * if from right to left
 * int_stack:2->1->1
 * ops_stack: -->+
 * our calculation would thus be 1+1 -2 which is correct
 * 
 * The most beautiful part here is that we pop int values do the calculation, then push the temp results back to int_stack
 * In this way, we will always have previous part in stack thus the final stack will only have one final result, we can just return it
 * 
 * Remark:
 * we are scanning the string from tail to head, so when we build the string, we have to append the char in front
 * So we use a string here, and we can easily append new char in front
 * @author hpPlayer
 * @date Aug 17, 2015 4:22:04 PM
 */

public class p224_sol3 {
	public static void main(String[] args){
		System.out.println(new p224_sol3().calculate("2147483647"));
		//System.out.println(Integer.valueOf("1".substring(1, 1)));
		//System.out.println(new p224_sol3().calculate("1+9"));
	}
    public void calculate_helper(Stack<Integer> int_stack, Stack<Character> ops_stack){
        int prev = int_stack.pop();
        /*
        if(int_stack.isEmpty()){//single element rounded by ()
        	//System.out.println("here");
            char sign = ops_stack.pop();
            if(sign == '-') post = -post;
        	int_stack.push(post);
        	return;
        }
        */
        int post = int_stack.pop();
       // System.out.println(prev);
        char sign = ops_stack.pop();
        if(sign == '-') post = -post;
        //!!!!!!!!!!!!!!!!!!the smartest operation, push the temp result to the stack
        int_stack.push(prev + post);        
    }
    
    public int calculate(String s) {
        Stack<Integer> int_stack = new Stack<Integer>();
        Stack<Character> ops_stack = new Stack<Character>();
        for(int i = s.length() - 1; i >= 0; i--){
        	//System.out.println(int_stack);
            char c = s.charAt(i);
            if(c == '+' || c == '-' || c == ')'){
                ops_stack.push(c);   
            }else if (Character.isDigit(c)){
                String temp = "";
                while(i >=0 && Character.isDigit(s.charAt(i))){
                    c = s.charAt(i);
                    temp = c + temp;
                    i --;
                }
               // System.out.println(num);
                int_stack.push(Integer.valueOf(temp));
                i += 1;//since i will - 1 in next loop, we have to increase 1 from it first
            }else if(c == '('){
            	//if we have single number rounded by ()
            	//we will skip it here, since ops_stack.peek() == '('
                //pop number first then pop sign
                while(ops_stack.peek() != ')'){
                    calculate_helper(int_stack, ops_stack);
                }
                //pop ')' out of ops_stack
                ops_stack.pop();
            }
        }
        //above loop only means we push all ops and ints to the stack, and calculated the results in parantheses
        //we also need deal with plain expression without parantheses
        while(!ops_stack.isEmpty()){
             calculate_helper(int_stack, ops_stack);
        }
        return int_stack.pop();

    }
}
