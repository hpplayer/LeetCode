import java.util.ArrayDeque;
import java.util.Queue;
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
