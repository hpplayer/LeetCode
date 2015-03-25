
/**
 * This is the best solution for this problem, which does not require think much of swap listNode and boundary case
 * Basically, we participate small and large nodes into two parts, and we always insert curr Node into the tail of two parts to preserve the 
 * order. Finally, we just need to merge these two parts and return the head of first part
 * Remark:
 * 1) Dont forget to set the final tail.next = null, otherwise it will cause loop
 * 2) Dont forget the case that we may have nothing in first part, so we need to check its validity before merging.
 * 3) Dont forget inital case when inserting into the part
 * 4) we can test all cases to validate our results, this problem includes 4 cases( all >= x, all < x, first >= x then < x or first < x then >= x) 
 * @author hpPlayer
 * @date Mar 25, 2015 1:09:36 AM
 */
public class p086_sol2 {
	public static void main(String[] args) {
		ListNode a = new ListNode(2);
		ListNode b = new ListNode(1);
		ListNode c = new ListNode(1);
		a.next = b;
		b.next = c;

		;
		ListNode curr = new p086_sol2().partition(a, 2);
		while (curr!= null) {
			System.out.println(curr.val);
			curr = curr.next;
		}
	}
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode partition(ListNode head, int x) {
		 if(head == null || head.next == null) return head;
			ListNode beforeStart = null;// start of first part
			ListNode beforeEnd = null;// end of first part
			ListNode afterStart = null;// start of second part
			ListNode afterEnd = null;// end of second part
			while (head != null) {
				ListNode next = head.next;
				if (head.val < x) {
					if (beforeStart == null) {// if it is first time meet head.val < x
						beforeStart = head;
						beforeEnd = head;
					} else {// insert node into the end of first part to keep order
						beforeEnd.next = head;
						beforeEnd = head;
					}
				} else {
					if (afterStart == null) {
						afterStart = head;
						afterEnd = head;
					} else {// insert node into the end of second part to keep order
						afterEnd.next = head;
						afterEnd = head;
					}
				}
				head = next;
			}
			if(beforeStart == null) return afterStart;//if all above x
			beforeEnd.next = afterStart;//end of first part's next is start of second part
			if(afterEnd != null) afterEnd.next= null;
			return beforeStart;
	}
}
