/*
Remove Duplicates from Sorted List II 

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
*/


/**
 * This is my original AC solution without help
 * My basic idea:
 * we have three pointers:
 * prev, curr and next
 * if we found curr.val == next.val
 * we will let next = next.next
 * until we found a node that has different value with current node
 * then we will keep prev node still, assign current node to be that node, let updated current node to be the next of prev node
 * then do a new check on updated current node
 * If we found curr.val != next.val
 * then we will simply let prev = curr, and curr = next
 * 
 * Some boundary cases needs to be taken care:
 * 1) when we search the next different value node, our node may reach the end and become null.
 * That's ok, we just stop here, still assign curr = next (which is actually null now), and assign updated curr node to be the next node 
 * of prev node
 * 2) When start a new loop, the next node may be null already, for this case, current node will definitely not be a duplicate node
 * so we just stop the loop and return
 * 3) we may remove head node as well, so we need a dummy node to help us connect with new head
 * 
 * 
 * Sol1 is my own iterative solution
 * Sol2 is a recursive solution
 * 
 * Compare 1 and 2, I think 1 is better, but 2 also provides a great recursive solution
 * So both sol1 and sol2 are recommended
 * 
 * @author hpPlayer
 * @date Sep 10, 2015 12:33:59 AM
 */
public class p082_sol1 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return null;

		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prev = dummy;
		ListNode curr = head;
		// ListNode next = head.next;

		while (curr != null) {
			ListNode next = curr.next;
			if (next == null)
				break;
			if (next.val == curr.val) {
				while (next != null && next.val == curr.val) {
					next = next.next;
				}

				prev.next = next;
				curr = next;
			} else {
				prev = curr;
				curr = next;
			}

		}

		return dummy.next;
	}
}
