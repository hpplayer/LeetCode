/*
Reorder List

Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
*/


/**
 * Haha, I am so happy I solved this problem
 * This is my original AC solution without help.
 * This problem is a combination of slow-fast runner and reverse LinkedList
 * We firstly use fast and slow runner to find the half of the LinkedList. The place where slow pointer stop is the start of second half.
 * We then reverse the second half list. so the start of second half actually become the last node. Then we will combine them with a zig-zag 
 * style.
 * 
 * Remark:
 * Don't forget to set first node's next = null during the reverse, otherwise we may fall into a loop
 * 
 * some examples:
 * 1) odd nodes:
 * 1 2 3
 * fast pointer stops at 3, slow pointer stops at 2,
 * first half 1, second half 2->3, we reverse it and get 3->2
 * combine in zig-zag way: 1->3->2
 * 
 * 2) even nodes:
 * 1 2 3 4
 * faster pointer stops at null, slow pointer stops at 3,
 * first half 1->2, second half 3->4, we reverse it and get 4->3
 * combine  in zig-zag way: 1->4->2->3
 * 
 * 
 * Remark:
 * There is also recursive solution that can be used to solve the problem, but it is not as easy-understanding as this version and also recursion
 * will take place in stack, so it is not recommended
 * @author hpPlayer
 * @date Sep 15, 2015 4:48:36 PM
 */
public class p143_sol1 {
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		a.next = b;
		b.next = c;
		c.next = d;
		
		new p143_sol1().reorderList(a);
		new p143_sol1().print(a);
	}
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        
        ListNode fast = head;
        ListNode slow = head;
        while( fast != null && fast.next != null){
        	fast = fast.next.next;
        	slow = slow.next;
        }
        ListNode newHead = reverse(slow);
        ListNode forward = head;
        //print(newHead);
        while(forward != null){
            ListNode next = forward.next;
            forward.next = newHead;
            forward = next;
            if(newHead != null){
            	ListNode next2 = newHead.next;
                newHead.next = next;
                newHead = next2;
            }

        }
    }
    
    public ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
    
    public void print(ListNode head){
    	ListNode curr = head;
    	while(curr != null){
    		if(curr.next != null){
    			System.out.print(curr.val + " -> ");
    		}else{
    			System.out.print(curr.val);
    		}
    		
    		curr = curr.next;
    	}
    	System.out.println();
    }
}
