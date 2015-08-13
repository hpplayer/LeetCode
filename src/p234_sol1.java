/**
 * This is my original AC solution without help
 * Since the problem requires us use O(1) space, we could not use an array to record and compare  values
 * The basic idea is locating the second half of the list, then reverse it, then compare the reversed second half with 
 * first half one by one
 * In my solution, I uses size() and do some calculation to get the mid 
 * But other's solution has a better idea to find the middle point, which is using fast and slow pointer technique
 * Compared with other's fast-slow poiner technique, my solution may get potential overflow problem if size > Integer.MAX_VALUE
 * 
 * Compare sol1 and sol2, I prefer sol2, which is more clear
 * Sol3 is python version of sol2, check it out
 * @author hpPlayer
 * @date Aug 13, 2015 1:23:14 PM
 */
public class p234_sol1 {
	public static void main(String[] args){
		ListNode a = new ListNode(2);
		ListNode b = new ListNode(3);
		ListNode c = new ListNode(4);
		a.next = b;
		b.next = c;
		
		ListNode dummy = new ListNode(-1);
		dummy.next = a;
		
		System.out.println(new p234_sol1().isPalindrome(a));
		ListNode curr = dummy.next;
		while(curr != null){
			System.out.println(curr.val);
			curr = curr.next;
		}
				
	}
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public boolean isPalindrome(ListNode head) {
		if (head == null)
			return true;
		int size = 1;
		ListNode curr = head;
		while (curr.next != null) {
			curr = curr.next;
			size++;
		}

		int mid = 0;
		boolean single = false;
		if (size % 2 == 1) {
			mid = size / 2 + 1;
			single = true;
		} else {
			mid = size / 2;
		}

		// anyway second part of list will start from mid +1
		curr = head;
		int count = 1;
		while (count != mid + 1) {
			curr = curr.next;
			count++;
		}

		ListNode newHead = reverseList(curr);

		count = 1;
		if (single) {
			while (count <= (size - 1) / 2) {
				count++;
				if (head.val != newHead.val) {
					return false;
				}
				head = head.next;
				newHead = newHead.next;
			}
		} else {
			while (count <= size / 2) {
				count++;
				if (head.val != newHead.val) {
					return false;
				}
				head = head.next;
				newHead = newHead.next;
			}
		}

		return true;
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
