import java.util.HashSet;
/**
 * My AC solution without help..
 * 
 * Use a hashset to add Node, if it has been visited, next time if we found a node has been recored is been visiting again,
 * then we know there is a cycle 
 * Remark: 
 * 1) Don't forget root = root.next()
 * 2) we change change the loop condition to root != null, then we can cover null case
 * @author hpPlayer
 * @date Mar 23, 2015 9:44:36 PM
 */
public class p141_sol1 {
	public static void main(String[] args){
		ListNode n1= new ListNode(1);
		ListNode n2= new ListNode(2);
		n1.next = n2;
	
		System.out.println(new p141_sol1().hasCycle(n1));
	}
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public boolean hasCycle(ListNode head) {
		// HashTable<ListNode, Boolean> hs = new HashTable<ListNode, Boolean>();
		HashSet<ListNode> hs = new HashSet<ListNode>();
		while (head != null) {
			if (!hs.contains(head)) {
				hs.add(head);
			} else {
				return true;
			}
			head = head.next;
		}
		return false;
	}
}
