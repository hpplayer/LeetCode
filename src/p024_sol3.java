/**
 * Another simple recursive approach,
 * we use swapParis to receive newHead 
 * then attach it to current head.next (current head will be the tail)
 * then swap current head and current head.next
 * The boundary case is we don't have two nodes to swap
 * After done attach newhead from next recursion and swap current nodes,
 * we simply return newhead to the previous recursion
 * 
 * This is a good recursion more simple and neat
 * @author hpPlayer
 * @date Apr 6, 2015 1:21:20 AM
 */
public class p024_sol3 {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	public ListNode swapPairs(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode newHead = head.next;
		head.next = swapPairs(newHead.next);//get new head and attach it to current the new tail
		newHead.next = head;
		return newHead;
	}
}
