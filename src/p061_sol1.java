/*
Rotate List

Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*/	
		

/**
 * I tried to write the code, but there are so many cases that needs to handle, so I did not make it..
 * 
 * Without right way, this problems become very complicate, since we need to record the pivotal, the one before pivotal, 
 * the tail and the head. There will be many pointers and cases that needs handle.
 * 
 * This sol1 i a standard 2 pointers approach, I used to have same idea, but I didn't use the technique of creating cycle to connect
 * list, so I did not make it out. 
 * Also this alg dpends highly on value of k, if k is large, it will cost extreme long time, so leetCode return TLE error..
 * 
 * Basic idea:
 * We have two pointer, one is used to find tail, one is used to find the node before pivotal.
 * Second pivotal start moves after first pointer has moved k steps (ex: 1,2,3 k =1, when first pointer reach tail,
 * second pointer reaches 2, which is just before the pivotal 3)
 * 
 * Then we connect tail with head to form cycle(1-2-3-1), and points head to the pivotal(head = 3), 
 * finally cut after the node before pivotal(3-1-2)
 * 
 * Remark:
 * rotateRight2() is my first try after referring the solution, but it contains a bug.
 * Where the bug is? we may have case that k > size, which means even we reach the tail, we still not found the correct node before pivotal
 * we need force the loop continue until k -> 0. so we have correct version see rotateRight():
 * We use a trick to control the loop,
 * we create cycle in the loop, and we know the tail should be one that just before the head
 * if we have found the node before pivotal, then we can break loop while we reach the tail. 
 * However, if k still > 0, then we need force loop continue, until k == 0, then we break the loop when reach the tail
 * This is very important, since this trick will help us cover the case that k > list.size();
 * Additionally, dont forget check boundary case in the start otherwise we may get error due to the fact we control the loop by 
 * looking Node.next != head instead of Node.next != null, so if we only have one node, it will cause error
 * Problem:
 * If k is very large, then this algorithm will take very long time O(k+n)
 * But actually, we dont need to iterate k loops, since we are repeating same steps, we can reduce time to O(2n), 
 * See sol2 for a better algorithm and its implementation
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
	//	k = k +1;
        if(head == null || head.next == null || k == 0) return head;
        ListNode currPointer = head, pvtPointer = head;
        while(currPointer.next != head || k > 0){//looking for tail, k > 0 is important because we must know we have found pivotal
        	if( k > 0){
        		k--;//count k times, and we dont move pvtPointer
        	}else{//when we visit node after pivotal
        		pvtPointer = pvtPointer.next;//start move pivotal pt after currPointer moves K steps 
        	}
        	currPointer = currPointer.next;
        	if(currPointer.next == null) currPointer.next = head;
        }
       // System.out.println(pvtPointer.val);
       //we reach the tail, we can make a cycle and cut it mid
       // currPointer.next = head;
        head = pvtPointer.next;
        pvtPointer.next = null;
        return head;
    }
	
	public ListNode rotateRight2(ListNode head, int k) {
		//	k = k +1;
	        if(head == null) return head;
	        ListNode currPointer = head, pvtPointer = head;
	        while(currPointer.next != head || k > 0){//looking for tail, k > 0 is important because we must know we have found pivotal
	        	if( k > 0){
	        		k--;//count k times, and we dont move pvtPointer
	        	}else{//when we visit node after pivotal
	        		pvtPointer = pvtPointer.next;//start move pivotal pt after currPointer moves K steps 
	        	}
	        	currPointer = currPointer.next;
	        	if(currPointer.next == null) currPointer.next = head;
	        }
	       // System.out.println(pvtPointer.val);
	       //we reach the tail, we can make a cycle and cut it mid
	       // currPointer.next = head;
	        head = pvtPointer.next;
	        pvtPointer.next = null;
	        return head;
	    }
	
	
	
	

}
