/**
 * This approach uses the similar solution as sol1
 * But it is more clear and beautiful.
 * 
 * The idea is: the main while loop always stop at the last node in previous group, then run reverse_list() on the head of new group.
 * This reverse_list() will reverse the new list and return the new head. So after the execution, we can easily attach the new head in 
 * new group to the last node in last group.
 * 
 * Also, it pull out the reverse list function, so this function can be used elsewhere 
 * 
 * The code is from ×ó¶ú¶äºÄ×Ó leetcode
 * @author hpPlayer
 * @date Jul 24, 2015 2:24:03 PM
 */
public class p025_sol2 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

	}

	public ListNode reverseKGroup(ListNode head, int k) {
		if (k <= 0 ) return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode curr = dummy;//we always let curr points to the node right before a group, i.e. curr always points to the last node in last group
		while(curr != null){
			curr.next = reverseList(curr.next, k);//reverse this group and connect new head in new group with last node in last group
			//move curr k steps away, i.e. move to the new group
			for(int i = 0; curr != null && i < k; i++){
				curr = curr.next;
			}
		}
		
		return dummy.next;
	}
	
	public ListNode reverseList(ListNode head, int k){
		ListNode ptr = head;
		while(ptr != null && k > 0){
			ptr = ptr.next;
			k--;
		}
		
		if(k > 0 ) return head;//we reach tail and num of nodes < k in this group
		
		//now ptr points to the node after last node in this group
		ListNode prev = ptr, curr = head;
		while( curr != ptr){
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;		
		}
		
		return prev;//return new head
	}
}
