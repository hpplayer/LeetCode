/**
 * Similar solution to my sol1 but with recursive way to insert new node
 * The logic is clear:
 * 1) don't assign head to be dummy.next, so our new list will be consistent with old list
 * 2) we use recursion here, each recursion will have information of previous node and next node, so we can easily change node accordingly
 * 3) The start of recursion has to be the dummy node
 * 4) we have to set node.next == null, because we don't want to mix up our new list
 * 
 * @author hpPlayer
 * @date Sep 8, 2015 10:36:23 PM
 */
public class p147_sol2 {
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(1);
		a.next = b;
		ListNode curr = new p147_sol2().insertionSortList(a);
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
        //dummy.next = head;//prev is dummy, curr is head
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = null;
            insertNode(dummy, curr);
            curr = next;
        }
        
        return dummy.next;
    }
    
    public void insertNode(ListNode prev, ListNode target){
        ListNode curr = prev.next;
        if(curr == null){
            prev.next = target;
            return;
        }
        
        //now curr will never be null
        if(curr.val >= target.val){
            prev.next= target;
            target.next = curr;
        }else{
            insertNode(curr, target);
        }
    }
}
