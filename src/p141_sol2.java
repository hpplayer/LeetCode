/**
 * My second AC solution without help..
 * Using 2 pointers
 * At first, I increased second pointer one by one like 1, 2, 3, 4, and I found it is possible become a dead loop, due to the huge 
 * gap increase and it is possible that p1 will never catch it.
 * Example(input: 1, 2, 3, 4 and 4 connect 2)
 * we have:
 * p1: 1 2 3 4 2 3 4 2 3...
 * offset 1 each time
 * p2: 2 4 4 2 4 4 2 4 4... 
 * offset diff+1 each time
 * So I modify the gap from exponential to constant 2, i.e. p1 = curr.next while p2 = curr.next.next,
 * then it will guarantee p2 will catch p1 if loop is existed 
 * 
 * Remark:
 * Increase one more step can reduce distance in cycle by one each iteration
 * If increase exponential steps may even increase distance in cycle by one each iteration
 * 
 * So next time, when facing such rabbit and turtle running problem, please increase faster runner one step each time!
 * A clean version of same algorithm is illustrated in sol3
 * @author hpPlayer
 * @date Mar 23, 2015 10:16:54 PM
 */


public class p141_sol2 {
	public static void main(String[] args){
		ListNode n0= new ListNode(-3);
		ListNode n1= new ListNode(3);
		ListNode n2= new ListNode(2);
		ListNode n3= new ListNode(0);
		ListNode n4= new ListNode(-4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n1;
		System.out.println(new p141_sol2().hasCycle(n1));
	}
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public boolean hasCycle(ListNode head) {
		ListNode pointer1 = head;
		ListNode pointer2 = head;
		int count = 2;
		while(true){
			
			System.out.println();
			for(int i = 0; i < count; i++){
				//System.out.println(count);
				if(pointer2 != null){
					pointer2 = pointer2.next;
				}else{//pointer2 reach tail
					return false;
				}
			}
			System.out.println("p1: " + pointer1.val);
			System.out.println("p2: " + pointer2.val);
			if(pointer2 == pointer1){
				return true;
			}
			pointer1 = pointer1.next;
			//count ++;
		}

	}
}
