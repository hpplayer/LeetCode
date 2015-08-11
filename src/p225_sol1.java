import java.util.ArrayDeque;
import java.util.Queue;

/*
Implement Stack using Queues 

Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.

Notes:
1. You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
2. Depending on your language, queue may not be supported natively.You may simulate a queue by using a list or deque (double-ended queue),
as long as you use only standard operations of a queue.
3. You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

Update (2015-06-11):
The class name of the Java function had been updated to MyStack instead of Stack.
*/

/**
 * This is my original AC solution without help
 * In my first try, I used two queues, but I found it would't work since queue order will always be maintained
 * offer() and poll() of queue will not change the order of values, thus will not give the effect of stack
 * 
 * So my idea is using a queue to add value normally, then use a temporary array to store queue and get the last value in queue
 * when doing pop() and top()
 * 
 * So, for my solution, top and pop will run in O(n) time, and take O(n) space complexity
 * Actually, we don't need extra space to solve this problem, a more brilliant solution can be found in sol2
 * @author hpPlayer
 * @date Aug 10, 2015 11:54:53 PM
 */

public class p225_sol1 {
	   Queue<Integer> que = new ArrayDeque<Integer>();
	    int[] temp = null;
	    // Push element x onto stack.
	    public void push(int x) {
	        que.offer(x);
	    }

	    // Removes the element on top of the stack.
	    public void pop() {
	        temp = new int[que.size()];
	        for(int i = 0; i < temp.length; i++){
	        	temp[i] = que.poll();
	        }
	        
	        for(int i = 0; i < temp.length - 1; i++){
	        	que.offer(temp[i]);
	        }
	        
	    }

	    // Get the top element.
	    public int top() {
	            temp = new int[que.size()];
	            for(int i = 0; i < temp.length; i++){
	            	temp[i] = que.poll();
	            }
	            
	            for(int i = 0; i < temp.length; i++){
	            	que.offer(temp[i]);
	            }
	        
	        return temp[que.size()-1];
	    }

	    // Return whether the stack is empty.
	    public boolean empty() {
	        return que.isEmpty();
	    }
}
