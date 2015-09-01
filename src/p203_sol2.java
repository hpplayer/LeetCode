/**
 * This is a very short and beautiful recursive code
 * First part is boundary check
 * Second part is recursive call, we get the new next code after backtracking is done
 * Third part is check current node, if it should be removed, then we will return new returned next node, otherwise, we return next node
 * 
 * @author hpPlayer
 * @date Sep 1, 2015 5:27:06 PM
 */
public class p203_sol2 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;//boundary case
        head.next = removeElements(head.next, val);//assign new next node after bottom-up
        return head.val == val? head.next : head; //if current node would be removed, then we return next node,otherwise return currenet node
    }
}
