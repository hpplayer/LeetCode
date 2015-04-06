/**
 * Iterative approach, based on the assumption that, each swap just involved four nodes
 * curr, curr.next, curr.next.next, curr.next.next.next
 * The boundary case for the swap is, current node does not next, or curr.next.next == null
 * curr.next.next.next == null is ok, since we can simply attach null to new tail, and treat it as a normal node
 * Create a dummy node here to help us retrieve node easily, and thus begin our first round of swap involving head 
 * and head.next
 * 
 * For the reconstruct the links among different nodes, it would be better if we can draw a connection figure to illustrate our 
 * reconstruction
 * Anyway, this is a clean and smart approach 
 * @author hpPlayer
 * @date Apr 6, 2015 1:15:31 AM
 */

public class p024_sol2 {
	public static void main(String[] args){
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		//ListNode n3 = new ListNode(3);
		//ListNode n4 = new ListNode(4);
		//ListNode n5 = new ListNode(5);
		n1.next = n2;
		//n2.next= n3;
		//n3.next = n4;
		//n4.next = n5;
		ListNode curr = swapPairs(n1);
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
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);//easy for retrieve new head
        dummy.next = head;
        ListNode curr = dummy;//we only know curr, curr.next and curr.next.next for swap
        while(curr.next != null && curr.next.next != null){//satisfy above assumption
            ListNode temp = curr.next;//backup curr.next
            curr.next = temp.next;
            temp.next = curr.next.next;//even if curr.next.next.next == null it does not matter
            curr.next.next = temp;
            curr = temp;
        }
        return dummy.next;
    }

}
