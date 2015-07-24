/*  Reverse Linked List 
 * 
Reverse a singly linked list.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?
 */


/**
 * I have done this practice before, the solution works in this way:
 * 1->2->3
 * firstly recursive visit our list until reach the tail 3
 * return 3 as the new head, and we will return it until the first layer to report the new head
 * 
 * We simply let "head".next.next points head, so now we form a loop between 2 and 3
 * By this step, we have formed the pointer that is from 3 to 2
 * Next step is cutting the original pointer that is from 2 to 3
 * which is simply set 2.next = null
 * 
 * Now the list will looks like 1->2<-3
 * 
 * Since we will build the new pointer every time we return to last layer, we can safely set .next = null
 * 
 * Repeat above steps, we will finally get list like 1<-2<-3
 * then we return new head to the program, and we are done
 * 
 * Iterative way, please see sol2
 *	
 * @author hpPlayer
 * @date Jul 23, 2015 11:25:41 PM
 */

public class p206_sol1 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null)
			return head;// found new head
		ListNode newhead = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return newhead;
	}
}
