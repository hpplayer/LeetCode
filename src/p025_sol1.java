/*Reverse Nodes in k-Group 
 * 
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
 * 
 */


/**
 * This is my original solution without help, and it is a hard level problem! yeah!!!!!!!!!! So happy!
 * My idea is using the recursive way. Why? Because we need reach the boundary case to deal with the reverse
 * ex: 1->2->3->4, and k = 2. The result should like 2->1->4->3. 1->2 is the first group and 3->4 is the second group.
 * The difficult part is how to connect this two parts. Obviously, If we do the iterative way, there is no way that we can connect
 * 1 with 4, since we don't know 4 is the new head of second part when we are visiting the fisrt part.
 * So here come with the recursive solution.
 * 
 * We use recursion to reach the tail of this list.
 * After that, we return the new head of last group.
 * Reverse the last second part, connect its original head with new head of last group, and return the last node in this group as 
 * the new head
 * Continue do this until we reach the top layer
 * 
 * @author hpPlayer
 * @date Jul 24, 2015 1:45:30 PM
 */
public class p025_sol1 {
	
	public static void main(String[] args){
		p025_sol1 test = new p025_sol1();
		ListNode a = test.new ListNode(1);
		ListNode b = test.new ListNode(2);
		ListNode c = test.new ListNode(3);
		ListNode d = test.new ListNode(4);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = null;
		
		ListNode curr = test.reverseKGroup(a, 2);
		while(curr != null){
			System.out.print(curr.val + "->");
			curr = curr.next;
		}
		
	}
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

	}

	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k == 0)
			return head;
		// deal with boundary case
		ListNode ptr = head;
		for (int i = 0; i < k - 1; i++) {
			ptr = ptr.next;
			if (ptr == null) {// reach tail
				return head;
			}
		}
		// now ptr points to the last node in k nodes
		//temp points to the next node of ptr
		ListNode temp = ptr.next;
		
		//return the new head of next group
		ListNode newhead = reverseKGroup(ptr.next, k);
		ListNode curr = head;
		ListNode prev = null;
		
		//temp is used as the indicator which can tell us when to stop the reversion
		while (curr != temp) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		
		//now head becomes the last node in this group, we ju
		head.next = newhead;
		//now prev becomes the new head
		return prev;
	}
}
