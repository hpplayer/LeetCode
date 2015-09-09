/**
 * This solution combines two functions together, so we don't need to create two separated two dummy nodes
 * As we can see from code, we combine two while loop together. The outer loop will scan the list, the inner loop decides where to
 * insert the new node. 
 * 
 * We have to be careful with same things as discussed in sol1 and sol2:
 * 1) Use dummy node, in case we change head node
 * 2) set new node.next = null to make it a single node and make our insertion more easily
 * 3) Don't attach head to dummy.next before loop begin, so our new list will keep consistent with old list
 * 
 * more details can be found below
 * @author hpPlayer
 * @date Sep 8, 2015 11:02:42 PM
 */

public class p147_sol3 {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
    public ListNode insertionSortList(ListNode head) {
        //two cases that we don't need sort our list
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(-1);
        ListNode curr = head;
        ListNode prev = dummy;
        
        while(curr != null){
            //store next node first, so we won't lose it after inserting curr node into the new list
            ListNode next = curr.next;
            //set next to null, so now we are only inserting a single node into the new list
            curr.next = null;
            //find the place to insert our new node
            while(prev.next != null && prev.next.val < curr.val){
                prev = prev.next;
            }
            //after the loop end, prev.val < curr.val and prev.next.val >= curr.val
            //now we can just insert it
            curr.next = prev.next;
            prev.next = curr;
            
            //reset prev for next insertion
            prev = dummy;//IMPORTANT, DONT FORGET TO RESET PREV TO HEAD NODE
            
            //move curr to next node
            curr = next;
        }
        
        return dummy.next;
    }
}
