/**
 * This is my one-pass AC solution
 * We can either delete node in iterative way or recursive way
 * My solution here is iterative as the logic would be very clear
 * We use a dummy node to handle the case the head would be deleted
 * We use two pointers to track the previous node and current node
 * If current node would be removed, then we let previous node points to current.next node
 * 
 * Then we move current pointer to current.next
 * 
 * here comes a question, should we move prev pointer?
 * It depends, if we just removed current node, then prev pointer should stay still, as we don't know whether new current node would be removed or not
 * But, if current node would be remain, then we can simply let prev pointer moves
 * 
 * 
 * Sol2 provides a recursive solution
 * 
 * Both sol1 and sol2 are short and beautiful, I like them all!
 * @author hpPlayer
 * @date Sep 1, 2015 5:01:18 PM
 */

public class p203_sol1 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode removeElements(ListNode head, int val) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prev = dummy;
		ListNode curr = head;
		while (curr != null) {
			if (curr.val == val) {
				prev.next = curr.next;
			} else {
				prev = curr;
			}
			curr = curr.next;
		}

		return dummy.next;
	}
}
