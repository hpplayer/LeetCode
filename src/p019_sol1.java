/**
 * My first version AC solution without help.
 *  This algorithm firstly get the length of list, then count from end and remove the index at length-k position.
 * This algorithm solves the problem with 2 pass, not optimum. 
 * Ref to sol2, sol3 and sol4 for other solution
 * @author hpPlayer
 * @date Mar 25, 2015 11:53:20 PM
 */

public class p019_sol1 {
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		//ListNode c = new ListNode(3);
		a.next = b;
		//b.next = c;

		ListNode curr = new p019_sol1().removeNthFromEnd(a,1);
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
	 public ListNode removeNthFromEnd(ListNode head, int n) {
		 int len = 0;
		 ListNode curr = head;
		 while(curr != null){
			 curr = curr.next;
			 len++;
		 }
		 int index = len - n;
		 //System.out.println(index);
		 if(index == 0) return head.next;
		 curr = head;
		 for(int i = 1; i < index; i++ ){
			 curr = curr.next;
		 }
		// System.out.println(curr.val);
		 curr.next = curr.next.next;
		 return head;
	 }
		   
		 
}
