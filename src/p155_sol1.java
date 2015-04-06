/**
 * When I was doing thie question, I firstly used a variable to track current min value, but it is incorrect, since only one variable 
 * will not help us track the next min value, if current min value is deleted.
 * Here are two soltions toward this problem.
 * 1) use a new stack to track the min, and a normal stack to record data(sol1) 
 * 2) keep current min value in each node(sol3)
 * 
 * Leetcode's original solution uses the built-in stack, which make code more concise.(sol2)
 * Here i used a linkedlist to mimic the built-in stack api
 * 
 * Each time,when we insert new node, we will check if new node has smaller value than current min value,
 * if it is, just insert into both stack, if not, dont insert the new node to min stack
 * Next time, if we are poping values, we will check if this value = top value in min stack.
 * If it is, then pop from min stack, if not, do nothing to the min stack. For the normal stack, we pop it as usual
 * 
 * Remark:
 * Care about duplicate min value, ex: 1, 3, 1
 * In our min stack we need record 1 1 two times, otherwise, when pop most recent 1, we will also pop the min from min stack, which cause
 * our min value in min stack moved to next min value
 * @author hpPlayer
 * @date Apr 5, 2015 6:37:45 PM
 */
public class p155_sol1 {
	public static void main(String[] args){
		p155_sol1 test = new p155_sol1();
		test.push(-2);
		test.push(0);
		System.out.println(test.getMin());
		test.push(-1);
		//test.push(512);
		//test.pop();
		System.out.println(test.getMin());
		System.out.println(test.top());
		//System.out.println(test.getMin());
		test.pop();
		System.out.println(test.getMin());
	}
	  public class Node{
	        int val;
	        Node prev = null;
	        Node next = null;
	        public Node(int val){
	            this.val = val;
	        }

	    }
	    

	    Node head = null;
	    Node minHead = null;
	    public void push(int x) {
	        Node newNode = new Node(x);
	        Node newNode2 = new Node(x);
	        if (head == null){
	            head = newNode;
	            minHead = newNode2;
	        } else{
	          newNode.next = head;
	          head.prev = newNode;
	          head = newNode;
	          if(x <=  minHead.val){
	              newNode2.next = minHead;
	              minHead.prev = newNode2;
	              minHead = newNode2;
	          }
	        }
	    }

	    public void pop() {
	        if(head != null){
	            if(head.next == null){
	                head = null;
	                minHead = null;
	            }else{
	               if(head.val == minHead.val){
	                	minHead = minHead.next;
	                }
	                Node temp = head.next;
	                temp.prev = null;
	                head.next = null;
	                head = temp;

	            }
	        }
	    }

	    public int top() {
	        if(head != null){
	            return head.val;
	        }else{
	            return 0;
	        }
	    }

	    public int getMin() {
	         return minHead.val;
	    }
}
