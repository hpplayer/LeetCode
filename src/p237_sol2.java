/**
 * We can delete any node without doing operation on whole list, just replace value in current node by the value in next node
 * and let the pointer skip next node and directly points to the node after next node,
 * which will make current node magically becomes next node in the list and makes the effect of deleting current node
 * 
 * @author hpPlayer
 * @date Aug 10, 2015 12:32:47 AM
 */
public class p237_sol2 {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
    public void deleteNode(ListNode node) {
        if (node != null && node.next != null){
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}
