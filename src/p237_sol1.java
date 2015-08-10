/*
Delete Node in a Linked List 

Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3,
the linked list should become 1 -> 2 -> 4 after calling your function.
*/

/**
 *  My original AC solution without help
 *  This problem asks ask to remove the node without giving the head node
 *  So the idea of changing pointer/values comes to my mind.
 *  But in the first try, I let the loop continue until the tail, then set tail node to null.
 *  This operations turns out to be incorrect since the operation tail = null is not changing the tail node but instead let 
 *  tail variable points to null, so the previous node in list still points to the original tail node.
 *  
 *  Then I changed the code as below which become accepted.
 *  
 *  But here I use the while loop to change the value, which is actually redundant
 *  We can actually just operate on two nodes.
 *  
 *  Sol2 lists such the idea 
 *  Sol3 is the python version of sol2
 * @author hpPlayer
 * @date Aug 10, 2015 12:28:18 AM
 */
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

		//node = null is setting node ref to null, but prev node is still pointing to the original node
		//so we have to stop before the tail node, i.e. last second node
		while (node.next.next != null) {
			node.val = node.next.val;
			node = node.next;
		}

		node.val = node.next.val;
		node.next = null;

	}
}
