/**
 * My original recursion version AC solution without help
 * The basic idea is as before, each recursion visits one bit in final output
 * As sol2, we initialize the new node's value with offset from previous recursion
 * Add l1 or l2 value as long as they are not null.
 * We attach the result from next recursion to current node.next, then return current node
 * @author hpPlayer
 * @date Apr 17, 2015 4:20:24 PM
 */
public class p002_sol3 {
	public static void main(String[] args){
		ListNode a= new ListNode(5);
		//ListNode b= new ListNode(8);
		//a.next = b;
		ListNode c= new ListNode(5);
		ListNode temp = addTwoNumbers(a, c);
		while(temp != null){
			System.out.println(temp.val);
			temp = temp.next;
		}
	}
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
		return addTwoNumbers(l1, l2, 0);
	}
	 public static ListNode addTwoNumbers(ListNode l1, ListNode l2, int offset) {
		 if(l1 == null && l2 == null && offset == 0) return null;
		 ListNode node = new ListNode(offset);
		 if(l1 != null){
			 node.val += l1.val;
			 l1 = l1.next;
		 }
		 if(l2!= null){
			 node.val += l2.val;
			 l2 = l2.next;
		 }
		 offset = node.val/10;
		 node.val = node.val%10;
		 node.next = addTwoNumbers(l1, l2, offset);
		 return node;
	 }
	 
	 
}
