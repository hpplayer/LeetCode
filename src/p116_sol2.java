/**
 * This solution is very brilliant. It still goes the tree layer by layer.
 * In each layer, we will create the next pointers for next layer, so actually our loops will only go h -1 loops (h is the height of tree)
 * We use two pointers in each layer. One pointer is pointing to the head node, so we can go to the head of next layer in next loop.
 * The other pointer is scanning the whole layer. Since the next pointer in current layer is supposed to be created in previous loop,
 * we can safely follow the next pointer to go through all nodes in current layer. We firstly build the next link between two children
 * of one single node, then if possible, link the right child with the next node's left child. We will do this to all nodes in current layer
 * After done that, we just use the first pointer to go the next layer
 * 
 * That's it, a very elegant solution!
 * @author hpPlayer
 * @date Sep 13, 2015 3:16:03 PM
 */

public class p116_sol2 {
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}
	
    public void connect(TreeLinkNode root) {
        if(root == null) return;//boundary case
        TreeLinkNode prev = root;
        TreeLinkNode curr = null;
        //while we still have next layer left 
        while(prev.left != null){
            curr = prev;
            //go through current layer node by node, and link their child together
            while(curr != null){
                curr.left.next = curr.right;//build link between two children
                if(curr.next != null) curr.right.next = curr.next.left;//if we have created next link in last layer
                curr = curr.next;//go to next node in current layer
            }
            prev = prev.left;//go to next layer
        }
    }
}
