import java.util.HashMap;

/*
Copy List with Random Pointer

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/

/**
 * This is my original AC solution without help.
 * I firstly did this with recursive solution, which cost stack overflow problem. So I change it to iterative solution.
 * This problem is very like problem Clone Graph (p133), but is simpler, since we only have two neighbors "next" and "random"
 * We will check if random and next have been copied, if yes, then retrieve them from HashMap, if not, then create a new one and put into the 
 * HashMap. after we done neighbors, we just let our node move to its next neighbor
 * 
 * Remark:
 * 1) Since the random/next  pointer may point to null, we have to check null before create a new node. Since null can be treated as a created node
 * 2) We will check next and random pointer in each node and create a new Node if it is not in HashMap, so we have to always check
 * if the node is existed in hashMap when checking further node's next  and random
 * 
 * Sol1 is my iterative iterative one-pass algorithm with HashMap
 * Sol2 is a recursive solution with HashMap
 * Sol3 is a three-pass algorithm without HashMap
 * 
 * All are recommended. For myself, I like sol1 and sol3, since sol1 is my solution and sol3 is very smart
 * @author hpPlayer
 * @date Sep 15, 2015 2:53:51 PM
 */
public class p138_sol1 {
	public static void main(String[] args){
		RandomListNode a = new RandomListNode(1);
		RandomListNode b = new RandomListNode(2);
		RandomListNode c = new RandomListNode(3);
		a.next = b;
		b.next = c;
		c.random = a;
		System.out.println(new p138_sol1().copyRandomList(a));
	}
	static class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	}

    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        HashMap<RandomListNode, RandomListNode> hs = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode root = new RandomListNode(head.label);
        hs.put(head, root);
        RandomListNode curr = head;//copy of head, since we don't want change head
        RandomListNode dummy = new RandomListNode(-1);//create dummy, so we can use root
        dummy.next= root;
        
        while(curr != null){
        	if(hs.containsKey(curr.next)){
        		root.next = hs.get(curr.next);
        	}else{
        	    if(curr.next == null){
        	        root.next = null;
        	    }else{
            		RandomListNode newNode = new RandomListNode(curr.next.label);
            		hs.put(curr.next, newNode);
            		root.next = newNode;        	        
        	    }
        	}
        	
        	if(hs.containsKey(curr.random)){
        		root.random = hs.get(curr.random);
        	}else{
        	    if(curr.random == null){
        	        root.random = null;
        	    }else{
            		RandomListNode newNode = new RandomListNode(curr.random.label);
            		hs.put(curr.random, newNode);
            		root.random = newNode;        	        
        	    }
        	}
        	root = root.next;
        	curr = curr.next;
        }

        return dummy.next;
    }
    
    
}
