/**
 * The logic of this solution is very clear
 * we have two pointers: slow and fast.
 * We firstly let fast pointer moves n nodes away from head.
 * If we found fast pointer already reach the node after tail, then we know the deletion point is the head
 * (ex: {1,2,3}, k =3, after iterate 3 times, fast pointer become null, which means k is the size of our list, so we need remove head)
 * In this case, we just return the head.next
 * In other cases, we then start move both the slow and fast pointer until faster pointer reaches the end, then we know the slow points
 * should be at the node before the deletion node
 * (ex: {1,2,3} k = 1, after iterate 1 time, faster pointer is at 2, then move fast and slow pointer 1 step away, then faster pointer reach
 * the end and slow pointer reach the node before deltion node (3))
 * In this case, we simply set slow.next = slow.next.next, and we are done
 * 
 *  Remark:
 *  1) It is easier to draw pictures to find where the slow pointer is. When I was doing this problem, I always got confusion about 
 *  where would slow pointer stops, and when faster pointer stops(tail? null?). With figure, it is more easy to think of
 *  2) Since we stop fast pointer at end, and n > 1, so we guarantee slow pointer is at least one node away from fast pointer (ex: 2,3),
 *  and they will never be overlap. So it is very safe to set slow.next = slow.next.next, as the rightmost case is slow.next.next == null
 *  
 *  This algorithm is a standard two pointer one pass algorithm
 *  But we have an interesting solution that solves it with one pass and n-based approach,
 *  please ref to sol4
 * @author hpPlayer
 * @date Mar 25, 2015 11:59:35 PM
 */
public class p019_sol3 {
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		a.next = b;
		b.next = c;

		ListNode curr = new p019_sol3().removeNthFromEnd(a,3);
		while (curr!= null) {
			System.out.println(curr.val);
			curr = curr.next;
		}
	}
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        //we firstly move fast node n nodes away from head
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }
        if(fast == null) return head.next;//this means n equals size of list, so we need remove head node
        //now fast node is at n nodes away from head, we just need to start to move fast and slow now. When fast node hits end, slow node
        //should at the n+1th node from end
        while(fast.next!= null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next; //remove nth node from end
        return head;
    }
}
