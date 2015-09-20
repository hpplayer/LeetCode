
/**
 * Another recursive solution, which provides another implementation of recursion.
 * @author hpPlayer
 * @date Sep 17, 2015 11:32:02 PM
 */
public class p206_sol3 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
    public ListNode reverseList(ListNode head) {
        if(head== null) return head;

        return reverse(null, head);
    }
    
    public ListNode reverse(ListNode prev, ListNode curr){
        if(curr == null) return prev;
        ListNode newStart = reverse(curr, curr.next);
        //ListNode next = curr.next;
        curr.next = prev;
        //prev.next = null;//avoid loop
        
        return newStart;
    }
}
