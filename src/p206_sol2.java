
/**
 * This is the iterative way, it is even more intuitive than recursive way
 * We have two pointers that point to prev and curr node
 * We simply store curr.next, then let curr.next points to prev
 * After that, we update prev to curr, curr to previously stored curr.next
 * 
 * Remark:
 * When we should stop?
 * The condition is curr != null, since if we stop at curr.next != null, then we will not revert the last pointer that between
 * last node and the last second node.
 * Thus, we need return prev instead of curr in the end, since now curr points to null while prev points to the last node
 * 
 * Following is the explanation from my previous solution:
 * iterative way
	
	 * work in thise way:
	 * 1 -> 2 ->3
	 * 
	 * prev:null
	 * curr:1
	 * 
	 * next:2
	 * 1->null
	 * prev:1
	 * curr:2
	 * 
	 * next = 3
	 * 2->1
	 * prev:2
	 * curr = 3
	 * 
	 * next = null
	 * 3->2
	 * prev:3
	 * curr:null
	 * 
	 * exit loop
	 * 
	 * So finally we have 3->2->1->null
	 
 * @author hpPlayer
 * @date Jul 23, 2015 11:34:37 PM
 */
public class p206_sol2 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
    public ListNode reverseList(ListNode head) {
        if(head == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
}
