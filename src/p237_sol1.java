public class p237_sol1 {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(1);
		a.next = b;
		
		new p237_sol1().deleteNode(a);
		while(a != null){
			System.out.println(a.val);
			a = a.next;
		}
	}
	public void deleteNode(ListNode node) {
		if (node == null || node.next == null)
			return;

		// while not tail
		while (node.next.next != null) {
			node.val = node.next.val;
			node = node.next;
		}

		node.val = node.next.val;
		node.next = null;

	}
}
