/**
 * My recursive solution. AC without help
 * My idea is passing tail of last pair and head of next pair to each recursion
 * so in each recursion, we must guarantee curr.next.next != null, since we need it as the head of next pair
 * also we must guarantee curr.next != null, since we need it as the tail of current pair
 * The difficulty is the boundary case, we may have only two element cases or one element cases or zero element cases,
 * either of which shall not go into our recursion.
 * 
 *  For iteration version, see sol2
 * @author hpPlayer
 * @date Apr 6, 2015 12:52:28 AM
 */
public class p024_sol1 {
	public static void main(String[] args){
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		//ListNode n3 = new ListNode(3);
		//ListNode n4 = new ListNode(4);
		//ListNode n5 = new ListNode(5);
		n1.next = n2;
		//n2.next= n3;
		//n3.next = n4;
		//n4.next = n5;
		ListNode curr = swapPairs(n1);
		while(curr != null){
			System.out.println(curr.val);
			curr = curr.next;
		}
	}
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        if(head.next.next == null){//only two listnode cases
        	ListNode temp = head.next;
        	head.next.next = head;
        	head.next = null;
        	return temp;
        }
        ListNode temp = head.next;
        recursion(head, temp.next);
        temp.next = head;
        return temp;
    }
    
    public static void recursion(ListNode prevTail, ListNode nextHead){
    	if(nextHead.next == null){
    		prevTail.next = nextHead;
    		return;
    	}
    	if(nextHead.next.next == null){
    		ListNode temp = nextHead.next;
    		prevTail.next= temp;
    		temp.next = nextHead;
    		nextHead.next = null;
    		return;
    	}
    	ListNode temp = nextHead.next;
        prevTail.next = temp;
        ListNode newHead = temp.next;//next head
        temp.next = nextHead;
        recursion(nextHead, newHead);
    }
}
