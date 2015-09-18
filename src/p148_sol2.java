/**
 * This is a variant of standard merge sort.
 * The advantage of bottom up merge sort is that It uses the iterative way instead of recursive way,
 * so we will reduce the pressure on stack.
 * 
 * The idea can be learn from video I included in the p148 file.
 * The idea is:
 * 1) Initially, the sublist size is set to 1
 * 2) then we double the sublist size and start merge the previous sublist(similar to normal merge sort)
 * 3) Finally until our size reach the length of original list size, we are done
 * 
 * The trick is how to find sublist and merge them
 * Here it uses a very smart way:
 * for split(): we find the tail of first sublist, store its next node, then set tail.next= null, to make 
 * first sublist becomes a separated list, then return the next node, which should be start of second sublist
 * for merge(): we pass the node before current two sublists as the head, then do normal merge process to attach nodes to it,
 * since we add tail in each sublist in split(), so we can treat them as a simple merge two sort list problem, then we return the last node
 * of merged list, which will be used as the new head for next set of two sublists.
 * In the main loop, our algorithm works like:
 * use cur node to find the start of first sublist
 * use split() on cur to find the start of next sublist
 * use split() in second sublist to find the start of next cur
 * use tail to record the last node of current two sublists, which can be used as the new head for next set of two sublists
 * use dummy to help us retrieve the head of new merged list
 * finally return dummy.next to get the new head
 * @author hpPlayer
 * @date Apr 10, 2015 7:23:36 PM
 */
public class p148_sol2 {
	public static class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val = x;
			next = null;
		}
	}
	
	public static void main(String[] args){
		ListNode a = new ListNode(5);
		ListNode b = new ListNode(4);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(2);
		ListNode e = new ListNode(1);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		
		ListNode temp = sortList(a);
		while(temp != null){
			System.out.println(temp.val);
			temp = temp.next;
		}
	}
	
	public static ListNode sortList(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode cur = head;
		int length = 0;
		while(cur != null){
			cur = cur.next;
			length++;
		}
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode left, right, tail;
		for(int i = 1; i < length; i <<= 1){//*2, increase step two times each loop, which mimic the forward merge sort 
			cur = dummy.next;//start Node of current sequences
			tail = dummy;//last Node of previous sequences
			while(cur != null){//split and merge to all sublists with i length each
				left = cur;
				right = split(left, i);//find the head of right which is ith step away
				cur = split(right, i);//we will do merge two these right and left, so cur should skip left + right in next loop
				tail = mergeTwoLists(left, right, tail);//merge two sublists and find the last node of merged list
			}
		}
		return dummy.next;
	}

	
	public static ListNode split(ListNode head, int n){//n is current length of each sequence
		//look for last Node of current sequence
		for(int i = 1; i < n && head != null; i++){
			head = head.next;
		}
		if(head == null) return null;
		ListNode second = head.next;//start Node of next sequence
		head.next = null;//build tail of previous sublist of length n, i.e. cut the link between two seq, so we can reorder freely
		return second;//return new head of next sublist
	}
	
	 public static ListNode mergeTwoLists(ListNode left, ListNode right, ListNode head){//head is the tail of last sublist,
	     ListNode cur = head;
	     while(left != null && right != null){
	    	 if(left.val > right.val){
	    		 cur.next= right;
	    		 cur = cur.next;
	    		 right = right.next;
	    	 }else{
	    		 cur.next= left;
	    		 cur = cur.next;
	    		 left = left.next;
	    	 }
	     }
	        cur.next = (left == null? right : left);
	        while(cur.next != null){
	        	cur = cur.next;
	        }
	        return cur; //return the last Node of current merged list
	    }
}
