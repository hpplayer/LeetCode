/**
 * Recursive solution!
 * In iterative solution, we scan the list forward, so we can keep current node, and delete all following nodes that have same value
 * In recursive solution, we will begin delete nodes, when we reach bottom, so we actually keep next node, and delete all previous nodes
 * that have same value
 * 
 * We firstly get next node.
 * If curr.val = next.val, then we delete curr node by assigning curr = next;
 * if curr.val != next.val, then we keep curr node and let curr node be the next node
 * then simply return curr node which may already be replaced with next node or keep alive
 * 
 * @author hpPlayer
 * @date Sep 9, 2015 11:40:08 PM
 */
public class p083_sol3 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
    public ListNode deleteDuplicates(ListNode head) {
        //two boundary case 
        if(head == null || head.next == null) return head;
        //we first get update next node from recursion
        
        ListNode next = deleteDuplicates(head.next);
        //if current value is same as next value, then we will delete current node, and replaced with next node
        if(head.val == head.next.val){
            head = next;
        }else{
            //if not same, then we will keep current node, and move pointer from curr to next node
            head.next = next;
        }
        
        return head;
    }
}
