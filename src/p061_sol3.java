/**
 * sol3 is my modified algorithm same with sol2, but instead of using one pointer, I used two pointers...
 * @author hpPlayer
 * @date Mar 25, 2015 6:54:16 PM
 */
public class p061_sol3 {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	  public ListNode rotateRight(ListNode head, int k) {
	        if(head == null) return head;
	        ListNode headPointer = head;
	        ListNode pvtPointer = head;
	        int len = 1;//we already include head into our list 
	        while(headPointer.next != null){//update length
	            headPointer = headPointer.next;
	            len ++;
	        }
	        k = k%len;//get optimized k
	        //(1,2,3), k = 1, last node of first part is 3 -1 =2
	        for(int i = 1; i < len - k; i++){//start from 1 since we already include head into our list
	            pvtPointer = pvtPointer.next;
	        }
	        headPointer.next = head;//form loop
	        head = pvtPointer.next;
	        pvtPointer.next = null;//cut between pvtPointer and Pivot
	        return head;
	    }
}
