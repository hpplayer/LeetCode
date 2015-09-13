/**
 * This is recursive solution.
 * Each recursion is working a one node and we only do three things:
 * 1)if current node has next layer, then we will let its left child.next = right child
 * 2) if current node itself has next node, then we will let its right child.next = node.next.left
 * 3) do same thing recursively on its left and right children
 * 
 * Since we always do thing from same order (left -> right), our algorithm will work as we guaranteed to has a next link for next node 
 * in same layer which we added in last recursive call. Also, we will firstly finish left subtree then go to next tree. So I believe it is more 
 * like a DFS solution
 * @author hpPlayer
 * @date Sep 13, 2015 3:28:42 PM
 */
public class p116_sol3 {
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null) root.right.next = root.next.left;
        }
        
        connect(root.left);
        connect(root.right);
    }
}
