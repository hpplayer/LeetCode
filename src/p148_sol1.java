/*Sort List
  Sort a linked list in O(n log n) time using constant space complexity.
*/

/**
 * the problem requires O(nlogn), it implies that we need use a sorting algorithm that has stable time complexity of O(nlogn)
 * To perform a merge sort on linked list, we need two functions
 * one is to split the list (find middle, pass left and right sublist, to next split function recursively)
 * another one is to merge the list(merge list can be ref to problem p21 Merge Two Sorted Lists)
 * 
 * However the problem here is that recursive call will never cost constant space complexity 
 * since the number of recursive will depend on input size(stack will increase size to fit input size)
 * so, in order to achieve constant space complexity we need use bottom up merge sort 
 * see sol2
 * @author hpPlayer
 * @date Apr 10, 2015 5:17:35 PM
 */
public class p148_sol1 {
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		a.next = b;
		
		ListNode temp = sortList(a);
		while(temp != null){
			System.out.println(temp.val);
			temp = temp.next;
		}
	}
	public static class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val = x;
			next = null;
		}
	}
	 public static ListNode mergeTwoLists(ListNode left, ListNode right){
	        ListNode dummy = new ListNode(-1);
	        ListNode curr = dummy;
	        while(left != null && right != null){
	            if(left.val < right.val){
	                curr.next = left;
	                left = left.next;
	                curr = curr.next;
	            }else{
	                curr.next = right;
	                right = right.next;
	                curr = curr.next;
	            }
	        }
	        
	        if(left == null && right != null){//attach right to tail
	            curr.next = right;
	        }
	        if(right == null && left != null){//attach left to tail
	            curr.next = left;
	        }
	        
	        return dummy.next;
	    }
	    public static ListNode sortList(ListNode head) {
	    	//System.out.println(head.val);
	        if(head == null || head.next == null) return head;
	        ListNode curr = head;
	        int len = 0;
	        while(curr != null){
	            len ++;
	            curr = curr.next;
	        }
	       // System.out.println(len);
	        int mid = len/2;
	        curr = head;
	        for(int i = 1; i < mid; i++){
	            curr = curr.next;
	            //System.out.println("im here");
	        }
	        ListNode temp = curr.next;
	        curr.next = null;//make left sublist clean by adding the tail
	        ListNode left = sortList(head);
	        ListNode right = sortList(temp);
	       // curr.next = null;
	        return mergeTwoLists(left, right);
	    }
}
