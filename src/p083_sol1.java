/**
 * This is my original fist-pass AC solution without help
 * Use curr and prev node to scan the list
 * Be careful with the boundary case like input head == null
 * 
 * Sol2 is another iterative solution without extra pointers(prev and curr).
 * Although the code is shorter, I don't think it is more readable than my code
 * 
 * The recursive solution is little harder than iterative solution, since we have to delete node from bottom in recursion
 * 
 * Sol1 is my iterative solution, which is readable and beautiful, it deletes node forward
 * Sol3 is a recursive solution, which deletes node backward, of course it is readable and beautiful as well
 * 
 * Both sol1 and sol3 are recommended
 * @author hpPlayer
 * @date Sep 9, 2015 11:20:25 PM
 */

public class p083_sol1 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return head;
		ListNode prev = head;
		ListNode curr = head.next;
		while (curr != null) {
			ListNode next = curr.next;
			if (prev.val == curr.val) {
				prev.next = next;
				curr = next;
			} else {
				prev = curr;
				curr = next;
			}
		}

		return head;
	}
}
