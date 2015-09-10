
public class p083_sol2 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode curr = head;
		
		while(curr.next != null){
			if(curr.val == curr.next.val){
				curr.next = curr.next.next;
			}else{
				curr = curr.next;
			}
		}
			
		return head;
	}
}
