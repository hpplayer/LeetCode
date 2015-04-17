/*
Add Two Numbers 

You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/


/**
 * This is my original AC approach without help
 * This algorithm is not hard, just doing the normal sum calculation 
 * and pass 1 if current index got overflow
 * 
 * The problem is there is redundant in my solution
 * As we can see from below, the while loop, and two if blocks follow the while loop, all repeat the same thing:
 * set offset = 1 if needed and create new node attach to current node
 * So, maybe we can make the code more concise
 * 
 * See sol2 for concise code
 * See sol3 for recursion version
 * @author hpPlayer
 * @date Apr 17, 2015 3:57:44 PM
 */
public class p002_sol1 {
	public static void main(String[] args){
		ListNode a= new ListNode(9);
		ListNode b= new ListNode(8);
		a.next = b;
		ListNode c= new ListNode(1);
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

	 public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode curr1 = l1;
	        ListNode curr2 = l2;
	        ListNode dummyHead = new ListNode(-1);
	        ListNode curr = dummyHead;
	        int offset = 0;
	        while(curr1 != null && curr2!= null){
	            int val = curr1.val + curr2.val + offset;
	            if(val >= 10){
	                offset = 1;
	                val = val - 10;
	            }else{
				offset = 0;
			}
	            ListNode newNode = new ListNode(val);
	            curr.next = newNode;
	            curr = curr.next;
	            curr1 = curr1.next;
	            curr2 = curr2.next;
	        }
	        if(curr1 == null && curr2 != null){
	            while(curr2 != null){
	                int val = curr2.val + offset;
	            if(val >= 10){
	                offset = 1;
	                val = val - 10;
	            }else{
				offset = 0;
			}
	            ListNode newNode = new ListNode(val);
	            curr.next = newNode;
	            curr = curr.next;
	            curr2 = curr2.next;
	            }
	        }
	        
	        if(curr2 == null && curr1 != null){
	            while(curr1 != null){
	                int val = curr1.val + offset;
	            if(val >= 10){
	                offset = 1;
	                val = val - 10;
	            }else{
				offset = 0;
			}
	            ListNode newNode = new ListNode(val);
	            curr.next = newNode;
	            curr = curr.next;
	            curr1 = curr1.next;
	            }
	        }
	       if(offset != 0){
			ListNode newNode = new ListNode(1);
			curr.next = newNode;
	    	}
	        return dummyHead.next;
	    }
}
