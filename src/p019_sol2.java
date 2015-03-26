/**
 * This is my second algorithm, which is one pass. AC solution without help.
 * I treat it as same as p63, where I create a cycle, so we can handle boundary case like target node is the head
 * Since we created a cycle, we need decide where to cut. The position can either be tail, head or mid.
 * Doing problem in this way may not be the best, since the logic is not so clear, so it is not recommended. 
 * Refer to sol3 and sol4 for more good solution
 * @author hpPlayer
 * @date Mar 25, 2015 11:56:00 PM
 */
public class p019_sol2 {
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		//ListNode b = new ListNode(2);
		//ListNode c = new ListNode(3);
		//a.next = b;
		//b.next = c;

		ListNode curr = new p019_sol2().removeNthFromEnd(a,1);
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
		 if(head == null ) return head;
		 if(head.next == null) return null;
		 ListNode curr = head;
		 ListNode del = head;
		 
		 while(curr.next != head || n > 0){
			 if(n > 0){
				 n--;
			 }else{
				 del = del.next;
			 }
			 curr = curr.next;
			 if(curr.next == null) curr.next = head;
		 }
		 //curr should be tail
		 if(del.next == head){
			 curr.next = null;
			 return head.next; //if delete head
		 }
			
		 if(del.next == curr){//if delete tail
			// System.out.println("Im here");
			 del.next = null;
		 }else{
			 curr.next = null;
			 del.next = del.next.next;//should be right, since we at least have two node, del and del.next
		 }
			
		// System.out.println(curr.val);
		// System.out.println(del.val);

		 return head;
	 }
}
