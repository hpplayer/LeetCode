/**
 * Initially, I don' want use while loop in recursive solution
 * But then I find it is nearly impossible.
 * 
 * Now we are in the recursion, which means we will start remove nodes during the backtrack.
 * If the returned next node has same value with current value, what should we do? maybe we have to return the node after 
 * returned next node, so these two nodes are removed. But how about the above recursion? they have no idea that we have removed 
 * two nodes, which means if the third node still has the same value with these two nodes, then we have no way to remove the third
 * one.
 * 
 * So actually we will combine iteration and recursion here.
 * Each recursion, we treat head as currNode, and we will scan the list from left to right.
 * If currNode.val = nextNode.val, then we will start while loop to find the first node that does not have same value with currNode,
 * then pass that node as currNode to next recursion. We will directly return the result from the recursion, since currNode(head)
 * is a duplicate node.
 * However, if currNode.val != nextNode.val, then we will move pointer to next node and do recursion on next node. Then attach
 * the returned result to currentNode, which we have identified as non duplicate node
 * 
 * 
 * @author hpPlayer
 * @date Sep 10, 2015 1:07:35 AM
 */

public class p082_sol2 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
    public ListNode deleteDuplicates(ListNode head) {
        //boundary case
        if(head == null || head.next == null) return head;
        
        ListNode next = head.next;
        if(next.val == head.val){
            //if next.val == head.val, then we have to skip all nodes that have same value
            while(next != null && next.val == head.val){
                next =next.next;
            }   
            return deleteDuplicates(next);
        }else{
            //not same? great, search next non-duplicate next node
            head.next =  deleteDuplicates(next);
            return head;
        }
     
    }
}
