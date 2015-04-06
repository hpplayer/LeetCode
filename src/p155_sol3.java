/**
 * Most beloved solution, without using additional space, just store currrent min in the node
 * Since we insert the min value by comparing curret min and current node's value 
 * so if we delete some unique min value, it will never appear again
 * Example:
 * insert 1->2->3->0
 * node's min value: 1->1->1->0
 * pop:
 * stack value: 1->2->3
 * stack min value: 1->1->1
 * 
 * which is correct and smart!
 * @author hpPlayer
 * @date Apr 5, 2015 7:17:06 PM
 */
public class p155_sol3 {
	public class Node{
		int val;
		int min;
		Node next;
		public Node(int val, int min){
			this.val = val;
			this.min = min;
		}
	}
	Node head = null;
    public void push(int x) {
        if(head == null){
        	head = new Node(x, x);
        }else{
        	Node temp = new Node(x, Math.min(head.min, x));//store current min value into the node's min
        	temp.next= head;
        	head = temp;
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}
