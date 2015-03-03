/**
 * my algorithm is simple and clean. 
 * It uses recursive approach, check the current smallest node, and recursively search the next smallest node and add to it
 * 
 * this is my first program that runs very smoothly in the first runs without help (failed first run though since one ')' is missed), but the algorithm
 * should work good, cheers! :)
 * 
 * @author hpPlayer
 */


public class p021_sol1 {
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
        if(l1 == null && l2 == null) return null;
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l2.val < l1.val){
            ListNode node = mergeTwoLists(l1, l2.next);
            l2.next = node;
            return l2;
        }else{
            ListNode node = mergeTwoLists(l1.next, l2);
            l1.next = node;
            return l1; 
        }
    }
}
