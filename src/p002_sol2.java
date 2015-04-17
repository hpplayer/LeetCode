/**
 * This approach combine my previous three parts together
 * in each loop, we will move pointer in l1/l2, as long as it is not null
 * For the new Node, its value will be the non overflow part in the sum,
 * If it is overflow, which means we need add 1 in the next loop, we can simply let sum in next loop start with 1
 * Finally, after exiting the loop, we check if we still get overflow, if it is, then we just simply add new node with value 1 in it to indicate
 * the overflow bit 
 * @author hpPlayer
 * @date Apr 17, 2015 4:07:16 PM
 */
public class p002_sol2 {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2){
		ListNode dummy = new ListNode(-1);
		ListNode curr = dummy;
		int sum = 0;
		while(l1 != null || l2 != null){
			sum /= 10;//get offset from previous loop
			if(l1 != null){
				sum += l1.val;
				l1 = l1.next;
			}
			if(l2 != null){
				sum += l2.val;
				l2 = l2.next;
			}
			curr.next = new ListNode(sum%10);//only store non-overflow part
			curr = curr.next;
		}
		if(sum/10 != 0){//if still have offset
			curr.next = new ListNode(1);
		}
		return dummy.next;
	}
}
