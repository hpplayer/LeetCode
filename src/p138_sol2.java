import java.util.HashMap;
/**
 * This is recursive solution. To get rid of stack overflow, we firstly remove the recursion on random pointer. 
 * We will always do recursive call on next only. That is like we create a copy of all nodes for next pointer firstly, then we begin
 * assign random pointer when backtrack. Since next pointers include all nodes, we can just retrieve all random pointers from HashMap
 * no need to create new node
 * @author hpPlayer
 * @date Sep 15, 2015 3:17:03 PM
 */

public class p138_sol2 {
	static class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	}
	
    HashMap<RandomListNode, RandomListNode> hs = new HashMap<RandomListNode, RandomListNode>();
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode root = returnCopy(head);
        return root;
    }
    
    public RandomListNode returnCopy(RandomListNode head){
        if(head == null) return null;
        /*each recursion is for one next node, which will always be new, so need to check if it is in HashMap
        if(hs.containsKey(head)){
            return hs.get(head);
        }
        */
        RandomListNode newNode = new RandomListNode(head.label);
        hs.put(head, newNode);
        newNode.next = returnCopy(head.next);
        
        if(head.random != null){
            newNode.random = hs.get(head.random);
        }else{
            newNode.random = null;
        }

        
        return newNode;
    }
}
