import java.util.*;


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
	          if(x <= minHead.val){
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
