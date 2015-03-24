
/**
 * My AC solution with help from CTCL
 * A good chase problem from CTCL (chap.2, 2.6)
 * If we assume the rabbit always run 2 times faster than turtle, then when turtle just enters the loop, assume k steps before entering loop
 * the rabbit has traveled k steps in the loop, so the distance between rabbit and turtle in the loop is loop size - k steps,
 * since rabbit reduce the distance by 1 each step, so after loop size - k steps, they will meet.
 * At that time, when they first meet, turtle enter the loop size - k steps, and rabbit also reach the same position, which is 2*(size-k) away
 * from the position where he stays when turtle enter the loop, thus the rabbit only needs to jump forward k steps, it will reach 
 * the start point of the loop. How to calculate k steps? we simply reset turtle to the start point, and let him keep original speed
 * and approach loop again, which will cost k steps, we let rabbit slow down, and also move at 1 step each phase, so after they meet 
 * second time, they should meet at the start of the loop, which is our result.
 * 
 * Remark:
 * In my first try, I overlooked the condition that the start of loop may overlap with the start of whole path,
 * so our loop condition should be while rabbit != turtle, otherwise we will force rabbit and turtle move at least one step,
 * which will cause error 
 * @author hpPlayer
 * @date Mar 24, 2015 12:55:08 AM
 */

public class p142 {
	public static void main(String[] args){
		ListNode n1= new ListNode(1);
		ListNode n2= new ListNode(2);

		n1.next = n2;
		n2.next = n1;

		System.out.println(new p142().detectCycle(n1).val);
	}
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
    public ListNode detectCycle(ListNode head) {
        ListNode rabbit = head;
        ListNode turtle = head;
        while(rabbit != null && rabbit.next != null){
            rabbit = rabbit.next.next;
            turtle = turtle.next;
            if(rabbit == turtle){//they meet after s-k loops when turtle just enter the loop
            	//System.out.println(rabbit.val);
                turtle = head;//reset, so after k steps, rabbit is at the start of cycle
                //while(rabbit != null && rabbit.next != null){//we can't let loop condition like this, this will force both of rabbit and turtle move one step
                //but it is possible that the start of the loop is also the start of whole path
                  while(rabbit != turtle){
                      rabbit = rabbit.next;
                      turtle = turtle.next;
                  }
                  return rabbit; //return here, since when we enter here, rabbit must have met turtle twice
            }
        }
        return null;
    }
}
