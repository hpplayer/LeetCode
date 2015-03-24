/**
 * A clean version of rabbit and turtle runner algorithm
 * @author hpPlayer
 * @date Mar 23, 2015 11:03:00 PM
 */
public class p141_sol3 {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	public boolean hasCycle(ListNode head) {
		ListNode Rabbit = head;
		ListNode Turtle = head;
		while(Rabbit != null && Rabbit.next != null){
			Rabbit = Rabbit.next.next;//rabbit moves 2 steps a time 
			Turtle = Turtle.next;//turtle moves 1 step a time
			if(Turtle == Rabbit) return true;
		}
		return false;
	}
}
