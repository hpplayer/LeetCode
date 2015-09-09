/*
Insertion Sort List

Sort a linked list using insertion sort.
*/

/**
 * Sol1 is my original iterative AC solution without help
 * Sol2 is my recursive version of sol1
 * 
 * Insertion sort is easy: read a new node, then search the sorted sublist and find the appropriate position to insert this new node
 * The average time complexity is O(n^2).
 * But sort a list is not so easy, since we have to consider the pointers, which may be mixed up during sorting
 * Here I implemented this process into two separated functions, one function is used to scan the original list, the other one is used to 
 * insert the node. 
 * In insertNode(), I just 
 * 
 * Since each node itself contains a pointer to next node, additionally, we need the head node to find where does our list begin,
 * there are some thing that needs to be noticed:
 * 1) we need dummy node, because the head node may be changed
 * 2) we are creating a new linked list, so the passed new node should be a single node without tailing nodes, otherwise we may bring other
 * nodes into our new linked list
 * 3) The most interesting point is that we don't need to assign head to dummy.next in the beginning. The logic is that, if we assign the 
 * head to be dummy.next, then our new linked list will starts with 2 nodes, but our scan of old linked list has not start yet. So it is wrong
 * 4) To always keep our dummy front, I assigned its value to be min-integer value, which will always be in the first. We used this trick,
 * so if incoming target also contains the min-integer value, we have to insert after dummy node, which means FOR THE DUPLICATE INPUTS, WE
 * HAVE TO INSERT NEW NODE IN THE LAST OF DUPLICATES
 * 
 * 
 * Sol1 and sol2 are all my code, but they use two functions, so they are long, and we have to use two dummy nodes for two functions
 * Sol3 provides a shorter and one function solution
 * 
 * So sol3 is recommended
 * @author hpPlayer
 * @date Sep 8, 2015 10:14:28 PM
 */

public class p147_sol1 {
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(1);
		a.next = b;
		ListNode curr = new p147_sol1().insertionSortList(a);
		while(curr != null){
			System.out.println(curr.val);
			curr = curr.next;
		}
	}
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode insertionSortList(ListNode head) {
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		//dummy.next = head;, not necessary, we will automatically add curr after newHead in insertNode() 
		ListNode curr = head;
		while (curr != null) {
			ListNode next = curr.next;
			insertNode(dummy, curr);
			curr = next;
		}

		return dummy.next;
	}

	public void insertNode(ListNode head, ListNode target) {
		target.next = null;//clear target avoid unnecessary errors
		ListNode dummy = new ListNode(-1);//still need dummy, in case head change
		dummy.next = head;
		ListNode prev = dummy;
		ListNode curr = dummy.next;
		//if(target == curr) return;//boundary case, we can avoid it if not assigning dummy.next = head in the beginning
		while (curr != null && curr.val < target.val) {
			prev = curr;
			curr = curr.next;
		}
		prev.next = target;
		target.next = curr;

	}
}
