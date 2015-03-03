/**
 * another approach that create a new list, my previous solution is in place (I only attach nodes to one of the input list, does not need 
 * extra spaces), while this approach attach nodes to  a dummy head node and finally return the dummyNode.next, which claimed by the author of LeetCode will be a plus for the interview
 * Straightforward approach except the step that we don't need add null to the last node. Details and explanations can be found below.
 * @author hpPlayer
 * @date Mar 2, 2015 10:08:58 PM
 */
public class p021_sol2 {
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(4);
		ListNode c = new ListNode(6);

		a.next = b;
		b.next = c;

		ListNode a1 = new ListNode(2);
		ListNode b1 = new ListNode(3);
		ListNode c1 = new ListNode(5);

		a1.next = b1;
		b1.next = c1;
		
		ListNode node = mergeTwoLists(a, a1);
		while(node != null ){
			System.out.println(node.val);
			node = node.next;
		}
	}
	
	private static class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val = x;
			next = null;
		}
	}
	
	 public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        ListNode dummyHead = new ListNode(-1);//node that always points the head
	        ListNode currNode = dummyHead; //pointer points to the current Node
	        while(l1 != null && l2 != null){
	            if(l1.val < l2.val){
	                currNode.next = l1;
	                l1 = l1.next;
	                currNode = currNode.next;
	            }else{
	                currNode.next = l2;
	                l2 = l2.next;
	                currNode = currNode.next;
	            }
	            
	        }
	        
	        if(l1 == null){
	            currNode.next = l2;
	        }else if(l2 == null){
	            currNode.next = l1;
	        }
	        //we dont need to care about if the last node.next = other node, since we must use ethier the last node of l1 or last node of l2
	        //to be the last node of final output. Either of them has next == null, so no need to worry about the next node in this problem
	        //for k-sorted lists, since we uses the min-heap, the last node may be originally in mid in the input like 122, 111, we have 2 of
	        //2's, if the first 2 becomes the last node, then it still has the next link to the 2nd 2
	        
	        return dummyHead.next;
	        
	    }
}
