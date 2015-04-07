/**
 * My original AC solution without help
 * This approach again proves the power of dummy head
 * Our list can be divided into 4 parts
 * 1.the tail of prev segment (must not be null)
 * 2.the new head of reversed list (not null, as described by the question)
 * 3.the new tail of reversed list (not null, as described by the question)
 * 4.the head of next segment (can be null) 
 * 
 * So my idea is find the 1 4, then reverse interval linked list to get 2 and 3
 * as described above 1 shall not be null, but we may have the case that interval list start from first node
 * then we would not have such 1, how? just let dummy node be the head, so we will always have 1.
 * since we will get new tail, which is allowed to be null.
 * If our input is 1,1 then no problem, since we will find (1) from dummy node, and attach appropriate (4), which is null, to the tail
 * so no problem
 * @author hpPlayer
 * @date Apr 6, 2015 4:27:27 PM
 */
public class p092_sol1 {
	public static void main(String[] args){
		ListNode a = new ListNode(5);
		//ListNode b = new ListNode(6);
		//ListNode c = new ListNode(7);
		//a.next = b;
		//b.next = c;
		ListNode curr = reverseBetween(a, 1, 1);
		//ListNode curr = reverse(a);
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
			next = null;
		}
	}
	public static ListNode reverse(ListNode head){
		ListNode curr = head;
		ListNode prev = null;
		ListNode next = head;
		while(curr != null){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;
		ListNode newHead = head;
		ListNode newtail = head;
		ListNode curr = dummyHead;
		for (int i = 0; i < n; i++) {
			if (i == m - 1) {
				newHead = curr;
			}
			curr = curr.next;
		}
		// curr now points to nth node
		newtail = curr.next;
		curr.next = null;

		ListNode tail = newHead.next;// the tail of this reversed linkedlist
		ListNode prev = newHead;
		curr = newHead.next;
		ListNode next = null;
		
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		// now prev is head of this reversed linkedlist
		 newHead.next = prev;
		 tail.next = newtail;//tail maybe null


		return dummyHead.next;
	}
}
