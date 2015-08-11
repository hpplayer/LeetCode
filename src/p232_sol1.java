import java.util.Stack;
/**
 * My AC solution without help. 
 * The most important thing is the solution passed on first try! cheers!
 * 
 * The idea is simple: keeping two stacks
 * Since the order in stack and the order of pop() from stack will be different, this is why we can use two stacks here 
 * to mimic Queue operations. Forward stack keeps the original order and we can add new elements on top of it, while backward
 * is used when we need to achieve Queue-like operation like pop() and peek()
 * 
 * Of course, there may be different implementation of this idea, like mimic p225's idea, where we use the backward stack to 
 * change the order of forward stack in push(), so that pop() and peek() will be O(1). But the basic idea should be same, and I like 
 * my pass-on-first-try code
 * 
 * 
 * 
 * @author hpPlayer
 * @date Aug 11, 2015 12:47:57 AM
 */

public class p232_sol1 {
	public static void main(String[] args){
		p232_sol1 test = new p232_sol1();
		test.push(1);
		System.out.println(test.peek());
		test.push(2);
		test.pop();
		System.out.println(test.peek());
	}
    Stack<Integer> forward = new Stack<Integer>();
    Stack<Integer> backward = new Stack<Integer>();
    // Push element x to the back of queue.
    public void push(int x) {
        while(!backward.isEmpty()){
            forward.push(backward.pop());
        }
        forward.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        while(!forward.isEmpty()){
            backward.push(forward.pop());
        }
        backward.pop();
    }

    // Get the front element.
    public int peek() {
        while(!forward.isEmpty()){
            backward.push(forward.pop());
        }
        return backward.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return forward.isEmpty() && backward.isEmpty();
    }
}
