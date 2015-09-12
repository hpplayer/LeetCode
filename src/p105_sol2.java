import java.util.Stack;


/**
 * This is an iterative solution, it is far more difficult than recursive solution
 * To finish the problem with iteration, we have to use a stack to help us do the DFS
 * We will insert nodes based on preorder[] since we know the left and right child will be the first nodes in left part and second part
 * like this: 1 2... 3... We will use inorder[] to help us locate the boundary of left and right part
 * 
 * We know the first node in inorder[] is the leftmost node in tree, and in preorder[] all nodes before this leftmost node are all nodes 
 * in left most edge. like inorder[3...], preorder[1,2,3...], then our leftmost edge is 1->2->3. This principle can be applied to all nodes
 * as long as our current preorder node is not same with current inorder[], we can safely assign left node one by one. It is convenient to
 * use stack to follow all nodes we visit in preorder, so it becomes as long as the top of our stack is not same with current index in 
 * inorder[], then we can safely insert left node
 * 
 * However, if we reach the bottom, the top of stack will be same with current index in inorder[], it means we have to go back. Follow the 
 * preorder[], we know that the index immediately after current index will be the right child if we have one. But how can we find which node
 * is its parent node? The answer is looking up the inorder[] with stack. if we found the top of stack is same with inorder[], then we will
 * pop a node from stack and move the pointer one step futher in inorder[], if the next top of stack and next node in inorder[] are not same,
 * it means we have reached a node we never visited before, and the last poped node is our last visited node before meet this new node. Notice
 * the new node in inorder[] is not necessary the right child of last poped node, since even we just found a right subtree for this node, in the
 * inplace traversal, this new node may be the leftmost node in right subtree. But fortunately, we have preorder[]. Current index in preorder[]
 * points to a new node but we don't know which parent it points to. While through the visit of inorder[], we found the node which has a right subtree
 * So based on this fact, we can append the new node in preorder[] to the parent node we found from stack and inorder[]
 * 
 * In summary, we will add nodes from preorder[] since we can easily find left and right child if we know the tree size, while the inorder[]
 * and stack just provides the feature to help us find the size. So we will check the traversal with inorder[] and stack, while inserting 
 * nodes from preorder[]
 *
 * @author hpPlayer
 * @date Sep 12, 2015 1:40:38 AM
 */
public class p105_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0) return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        
        int i = 1, j = 0;//i is index in preorder, j is index in inorder
        while(i < preorder.length){
            TreeNode curr = stack.peek();
            //if we are still visiting the left path and not reach end yet
            if(inorder[j] != curr.val){
                TreeNode left = new TreeNode(preorder[i]);
                i++;
                curr.left = left;
                stack.push(left);
            }else{
                //skip all left nodes we have added until we found a right node or we reach the top (stack empty)
                while(!stack.isEmpty() && inorder[j] == stack.peek().val ){
                    curr = stack.pop();
                    j++;
                }
                //node in j is an unvisited node in right tree. We don't know if it is curr.right child 
                //or the leftmost node in right tree
                //but since we have finished the left subtree, we can safly move pointer in preorder 
                TreeNode right = new TreeNode(preorder[i]);
                i++;
                curr.right = right;
                stack.push(right);
            }
        }
        
        return root;
    }
}
