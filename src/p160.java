/**
 *My AC solution without help. My basic idea is similar to other people's idea that is 
 *firstly visiting both linkedlist, and calculate the difference between the length, say offset.
 *Then let the longer list move offset steps before both of them start move. Now they should be at same start point 
 *we just move step one by one, if there is an intersection, they will definitely meet somewhere
 *Time: O(m+n), and O(1) space
 * @author hpPlayer
 * @date Apr 2, 2015 11:52:03 PM
 */
public class p160 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
			return null;
		int lenA = 0;
		int lenB = 0;
		ListNode fast = headA;
		ListNode slow = headB;
		while (fast != null) {
			fast = fast.next;
			lenA++;
		}
		while (slow != null) {
			slow = slow.next;
			lenB++;
		}
		int offset = 0;
		if(fast != slow) return null;
		if (lenB > lenA) {
			offset = lenB - lenA;
			fast = headB;
			slow = headA;
		} else {
			offset = -(lenB - lenA);
			fast = headA;
			slow = headB;
		}

		while (fast != null) {
			if (offset > 0) {
				fast = fast.next;
				offset--;
			} else {
				if (fast == slow) {
					return fast;
				}
				fast = fast.next;
				slow = slow.next;

			}
		}
		return null;

	}
}
