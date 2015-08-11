import java.util.ArrayDeque;
import java.util.Queue;
/**
 * This is a very smart solution.
 * It only costs O(1) in pop() and top(), but costs O(n) in each push()
 * In push(), we simply add value in it, then rotate the queue and make the newly added element become first element in queue
 * Other operations are same with queue
 * 
 * @author hpPlayer
 * @date Aug 11, 2015 12:02:34 AM
 */

public class p225_sol2 {
	Queue<Integer> que = new ArrayDeque<Integer>();
    // Push element x onto stack.
    public void push(int x) {
        //add value to que first then rotate queue until last element becomes the first
        que.offer(x);
        //needs que.size() - 1 since we have added new value and make size + 1 
        for(int i = 0; i < que.size()-1; i++){
            que.offer(que.poll());
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        que.poll();
    }

    // Get the top element.
    public int top() {
        return que.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return que.isEmpty();
    }
}
