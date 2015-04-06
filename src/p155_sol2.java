import java.util.Stack;

/**
 * Same approach as used in sol1
 * but instead of implementing stack, here I used build-in stack api
 * 
 * Remark
 * when comparing the minstack.peek() and stack.peek(), we need use equal not ==
 * since both stored integer.
 * using == will cause error, as tested on leetcode
 * 
 * 
 * @author hpPlayer
 * @date Apr 5, 2015 6:53:11 PM
 */
public class p155_sol2 {
	Stack<Integer> stack = new Stack<Integer>();
	Stack<Integer> minStack = new Stack<Integer>();
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()){
        	minStack.add(x);
        }
    }

    public void pop() {
        if(stack.peek().equals(minStack.peek())){//use equal not ==, since integer is object. If use ==, it will return incorrect solution
        	//tested on leetCode
        	minStack.pop();
        }
    	stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
