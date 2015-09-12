import java.util.Stack;
/**
 * In postorder traversal, we know nothing about the boundary of right and left subtree, so we can have same array but one node may
 * in the left or subtree
 * Example:
 * post[] 6731
 *   1                   1
 *  / \                   \
 * 6   3                   3     
 *      \				  / \
 *       7               6   7
 * inorder[] 6137       inorder[] 1637 
 * now, node 6 can either be in the left or right tree
 * But with inorder[] and stack, we can help check if current node is in left tree or right tree
 * 
 * When we visit the postorder[], we push visited nodes into stack
 * Then we compare the stack.peek() with pointer in inorder[]. If there are same, it means we have done the visit of right subtree because
 * all right subtree nodes should just behind the curr node in inorder[]. Otherwise, we will still compare different nodes.
 * Then we go back, let stack.pop() and inorder[]'s pointer move backward, if they new pair is still same, it means we have done the visit on
 * the node we just poped out, this node does not have left subtree. However, if we found the new pair is not same, then we know the we found
 * a node whose left subtree has not been visited yet, and this node is the one we just poped out. However, from inorder[] alone, we only know 
 * there is a node whose left subtree has not been visited yet, but we still don't know what is root of left subtree. But remember, we always move
 * the pointer in the postorder[], and from stack and inorder[] we can find the node whose left subtree has not been visited yet (right tree has
 * been visited). So it means our pointer in postorder[] has passed all nodes in some node's right subtree, and stop at the last node of left subtree
 * which by the definition of post order traversal must be the root of left subtree. So we found the root of left subtree and found the node where
 * we have not start visited its left subtree yet, thus we simply attach left child to the node, and start visit its left subtree.
 * 
 * 
 * @author hpPlayer
 * @date Sep 12, 2015 2:54:52 PM
 */
public class p106_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //boundary case
        if(inorder.length == 0) return null;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode root = new TreeNode(postorder[postorder.length-1]);
        stack.push(root);
        
        int i = postorder.length-2, j = inorder.length-1;//i is index in postorder[], j is index in inorder[]
        
        while(i >= 0){
            TreeNode curr = stack.peek();
            if(curr.val != inorder[j]){
                //as long as we have not reach the rightmost node of subtree we can safely follow right path and attach right child
                TreeNode right = new TreeNode(postorder[i]);
                curr.right = right;
                stack.push(right);
                i--;
            }else{
                //found the node from stack where we have not visited its left subtree
                while(!stack.isEmpty() && stack.peek().val == inorder[j]){
                    curr = stack.pop();
                    j--;
                }
                
                TreeNode left = new TreeNode(postorder[i]);
                curr.left = left;
                stack.push(left);
                i--;
            }
        }
        
        return root;
    }
}
