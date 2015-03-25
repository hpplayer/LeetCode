/**
 * My solution, its too length and buggy...failed.
 * I was intend to use a pointer points to the current last node of small part, a previous pointer 
 * and current node pointer.
 * But it results out to be very complicated in this way due to a lot of unexpected boundary conditions,
 * So please ref to sol2 
 * Also this problem is in the CTCL 2.4
 * Without help of dummy before and after, the code is not easy to write;
 * @author hpPlayer
 * @date Mar 25, 2015 12:37:00 AM
 */
public class p086_sol1 {
	public static void main(String[] args) {
		ListNode a = new ListNode(2);
		ListNode b = new ListNode(1);
		ListNode c = new ListNode(0);
		a.next = b;
		b.next = c;

		;
		ListNode curr = new p086_sol1().partition(a, 2);
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

	public ListNode partition(ListNode head, int x) {
		ListNode headPointer = head;
		ListNode smallPointer = head;
		ListNode currPointer = head;
		ListNode prevPointer = head;
		
		while(currPointer != null){
			if(currPointer.val >= x){//no change needed 
				//System.out.println(currPointer.next.val);
				prevPointer = currPointer;
				currPointer = currPointer.next;
				continue;
			}else{//we need 1 remove it from list and add in front
				//System.out.println("im here");
				if(smallPointer == head){//first time meet smaller node
					prevPointer.next = currPointer.next;//delete node
					currPointer.next = head;//insert node 
					headPointer = currPointer;
					currPointer = prevPointer.next;
					continue;
				}
				if(prevPointer == smallPointer && smallPointer.val >= x){//inital case
					//System.out.println("im here");
					if(prevPointer.val <= currPointer.val){//normal case
						//System.out.println("im here");
						smallPointer = currPointer;
						currPointer = currPointer.next;
						prevPointer = prevPointer.next;		
						continue;
					}else{//need swap
						//System.out.println("im here");
					//	System.out.println(prevPointer.next.val);
						//ListNode temp = currPointer;
						prevPointer.next = currPointer.next;//delete currNode
						smallPointer = currPointer;
						currPointer.next = prevPointer;//currNode become the head
						headPointer = currPointer;//head changed
						currPointer = prevPointer;
						//currPointer = prevPointer.next;
						//System.out.println(smallPointer.val);
						//System.out.println(headPointer.next.val);
						continue;
					}
				}
				//done initial case
				//System.out.println("im here");
				ListNode temp = currPointer.next;//next pointer
				prevPointer.next = temp;
				//deletion done
				currPointer.next = smallPointer.next;
				smallPointer.next = currPointer;
				//insertion done
				currPointer = prevPointer;
			}
			//System.out.println("im here");
			prevPointer = currPointer;
			currPointer = currPointer.next;
			
		}
		return headPointer;
	}
}
