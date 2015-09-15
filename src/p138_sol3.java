/**
 * This is a very beautiful solution without HashMap, but the cost is we have to scan the list for three times
 * In the first loop, we create a copy of each node and insert it between current node and its next node
 * In the second loop, we assign the random pointer of each copy by link it with the node after the node where random pointer points to, i.e.
 * let the random pointer of each copy points to the copy of each random node
 * In the third loop, we will recover the structure, we let original current node points to its original next node, while let our copy node points
 * to next copy node
 * 
 * This algorithm makes use of the LinkedList structure (just two pointers, next and random) and get rid of HashMap, which is very smart
 * @author hpPlayer
 * @date Sep 15, 2015 3:36:34 PM
 */
public class p138_sol3 {
	static class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	}
	
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;//boundary case
        RandomListNode curr = head;
        RandomListNode next = null;
        while(curr != null){//scan the list for the first time, create the copy of all next pointers first 
            next =curr.next;//record original next
            RandomListNode newNode = new RandomListNode(curr.label);
            //insert copy between curr and next
            curr.next = newNode;
            newNode.next = next;
            curr = next;//move pointer
        }
        
        curr = head;//reset pointer
        while(curr != null){
            //if we have random pointer at current Node
            if(curr.random != null){
                //copy is after each node
                //we set copy of current node's random points to the copy of random node
                curr.next.random = curr.random.next;
            }
            //move to next original curr node, since we know each original node will get its copy
            //then we can safely move the next node, by skipping two nodes (itself and its copy)
            curr = curr.next.next;
        }
        
        curr = head;//reset pointer
        RandomListNode root = curr.next;//record the copy of start node
        RandomListNode copy = curr.next;//pointer of copy nodes, initally point to the start of copy nodes
        while(curr != null){
            curr.next = copy.next;//cut the copy, let original curr Node to its original next Node
            curr = curr.next;//move pointer to next Node
            copy.next = curr == null? null : curr.next;//the next copy node is the node after next node
            copy = copy.next;//move copy node to its next
        }
        
        return root;
    }
}
