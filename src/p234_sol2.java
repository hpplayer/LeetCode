/**
 * This is a very clear approach that uses the similar idea as mine, but we don't use size here, we use fast and slow move pointer
 * fast pointer will move two nodes each move, slow pointer will move one node each move
 * We let the fast pointer stops at tail or null depending on the real case
 * So if the size is odd, like 3 nodes, fast pointer will move one step and reach tail, while slow pointer will reach the mid
 * If the size is even, like 4 nodes, fast pointer will move two steps and reach null, while slow pointer will reach the second mid node
 * After getting the mid, we need to reverse the second half list, this method can be referred in p206
 * After that we need compare two lists, the boundary case should be the pointer in first half list not touching the mid point
 * If it is the odd size, that means we will compare the list before and after mid node, that's ok
 * If it is the even size, that means we will compare the first list from head to first mid node with the second list from tail to second mid
 * which is exactly what we want.
 * So there is that 
 * 
 * @author hpPlayer
 * @date Aug 13, 2015 1:40:53 PM
 */

public class p234_sol2 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	/*
	 * If the size is odd, after FindMid, slow pointer will point to the mid node
	 * If the size is even, after FindMid, slow pointer will point to the second mid node (there are totally two mid nodes in even sized list
	 * when size is even, like 4 nodes, the fast pointer will move twice then become null, and slow pointer thus will point to the second mid node
	 * This is what we what, since if the total size is even, we can compare exactly two halves
	 */
	public ListNode findMid(ListNode head){
		ListNode fast = head, slow = head;
		//since fast pointer moves at x2 speed, at some point we may jump to the tail node or null, so we need check fast itself and fast.next
		while(fast != null && fast.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		
		return slow;
	}
	
	
	public ListNode reverseList(ListNode head){
		ListNode prev =  null;
		ListNode curr = head;
		while (curr != null){
			ListNode temp = curr.next;
			curr.next= prev;
			prev = curr;
			curr= temp;
		}
		return prev;
	}
	
    public boolean isPalindrome(ListNode head) {
        ListNode Mid = findMid(head);//second mid or the unique mid node
        ListNode newHead = reverseList(Mid);
        for(; head != Mid; head = head.next, newHead = newHead.next){
        	if(head.val != newHead.val) return false;
        }
        
        return true;
    }
}
