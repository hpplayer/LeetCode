/**
 * This approach uses n to control the deletion index.
 * What does it mean?
 * We added a trick when counting n.
 * We still let n-- even if n == 0, this means if n == 0 while currNode reaches list end, then we know n equals to the size of list, 
 * which means we need remove from head. When currPointer reaches the end, if n < 0, then we know we will delete in the mid.
 * If n > 0, we know n > size of list, which is not possible in this case.
 * Other details are similar to sol3(how to remove node) and p61(the way deal with prev pointer and curr pointer in while loop)
 * 
 * This sol provides another interesting way to solve the problem
 * @author hpPlayer
 * @date Mar 26, 2015 12:10:29 AM
 */
public class p019_sol4 {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	  public ListNode removeNthFromEnd(ListNode head, int n) {
	        ListNode prev = head, curr = head;
	        while(curr != null){
	            if(n >= 0){//we start count down, we use a trick here to distinguish if we need remove at beginning or mid
	                n--;
	            }else{//time to move prev node
	                prev = prev.next;
	            }
	            curr = curr.next;
	        }
	        if(n > 0){
	            return null;//our n is larger than list size, not valid input
	        }else if (n == 0){//remove at head
	            return head.next;
	        }else{//remove in the mid
	            prev.next = prev.next.next;//remove node
	        }
	        return head;
	    }
}
