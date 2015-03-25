/**
 *Basic idea:
 *Firstly, we need count the length of the list, and we breakout when we reach the tail
 *We do this for two reasons:
 *1) we want to know the length of list, so we would know where to rotate the list
 *2) we want reach the tail and then attach the head to it to make the cycle
 *Cycle can make use easy to manipulate rotation, we can cut anywhere while still preserve the order
 *After formed the cycle, then we count where the pivotal is. The pivotal should be length - k + 1 if count from left, while length - k
 *will be previous node of pivotal node (or we can call the last node of first part)
 *after length-k moves, we will reach the previous node of pivotal
 *Ex. (1,2,3) k = 1, length =3, 3-1 =2 
 *So we can set its next to null to cut the cycle, and pivotal will automatically become the head. Of course,
 *before the cut, we need to record the pivotal, and set it to head, then our rotation is done.
 *So smart!
 *
 *Remark:
 *Count here(k) includes itself(ex: (123), k = 1 then we need rotate at 3 and results are 312)
 *This algorithm can optimize the solution if k is very large
 *Here we treat k > n as k %n, since we allow rotate several rounds...
 *It is also right if k <= n, so % is an amazing operation
 *Remember the length after first loop is the actual length of list(at first I didnt figure it out, i thought it does not include the 
 *tail, but it does)
 * @author hpPlayer
 * @date Mar 25, 2015 1:24:03 PM
 */

public class p061_sol2 {
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		a.next = b;
		b.next = c;

		ListNode curr = new p061_sol2().rotateRight(a, 1);
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
	public ListNode rotateRight(ListNode head, int n){
		if (head == null) return head;
		ListNode p = head;
		int len = 1;//length 1, since we added the head
		while(p.next != null){//found tail
			len ++;
			p = p.next;
		}
		//loop breaks when we reach tail, the len is the actual length 
		p.next = head;//form a cycle;
		n = n % len;//remove duplicate
		//System.out.println(len);//%: mod 
		//len - n is the position if count forward
		for(int i = 0; i < len - n; i++){//we are looking for the pivotal
			p = p .next;
		}
		//now we (p) should be 1 index before the pivotal due to our length is 1 less then the actual lenght
		head = p .next;//pivotal, p is the last index of previous fragment
		p.next = null;
		return head;
	}
}
