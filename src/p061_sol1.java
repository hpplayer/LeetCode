/**
 * I tried to write the code, but there are so many cases that needs to handle, so I did not make it..
 * 
 * Without right way, this problems become very complicate, since we need to record the pivotal, the one before pivotal, 
 * the tail and the head. There will be many pointers and cases that needs handle.
 * 
 * See sol2 for a better way
 * @author hpPlayer
 * @date Mar 25, 2015 1:31:20 PM
 */

public class p061_sol1 {
	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		a.next = b;
		b.next = c;

		ListNode curr = new p061_sol1().rotateRight(a, 2000000000);
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
	public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        ListNode tail = null;
        ListNode pivotal = null;
        ListNode headPointer = head;
        ListNode dummyhead = null;
        ListNode beforePivotal = null;
        ListNode prev = null;
        int count = 0;//count size
        int size = 0;
        while(headPointer != null){
        	size++;
        	headPointer = headPointer.next;
        }
        if(k >= size ) k %= size;
        System.out.println(k);
        headPointer = head;
        while(headPointer != null){
            if(count == k){
                if(pivotal == null){
                    beforePivotal = prev;
                    pivotal = headPointer;
                }else{
                    beforePivotal = pivotal;
                    pivotal = pivotal.next;
                }
            }else if (count < k){
            	if(dummyhead == null){
            		dummyhead = headPointer;
            	}else{
                    dummyhead.next = headPointer;
                    dummyhead = headPointer;
            	}
            }
            if(headPointer.next == null){//meet tail
                 tail = headPointer;
            }
            prev = headPointer;
            headPointer = headPointer.next;
            count++;
        }
       // if(k >= count) return head;
        if(dummyhead == null) return head;
        if(tail != null && tail != head) tail.next = head;
         beforePivotal.next = null;
        if(pivotal != null){
            return pivotal;
        }else{
        	return head;
        }
    }
}
