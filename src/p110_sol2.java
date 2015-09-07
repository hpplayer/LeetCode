import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 * This is a very beautiful iterative solution.
 * Because we need store current node's depth after we have visited its left and right subtrees, so post-order traversal is used here
 * Unlike recursion, in which we can return depth after done search and begin backtrack, here we need some techniques to help us 
 * track the depth of subtree.
 * Using HashMap is a good way, also we can use inner class. Comparing them, hashMap would be continent
 * 
 * We firstly push root into the stack. Then check its left and right child.
 * We assume we have done the search of its subtree if either this child is null or we have stored this child in HashMap
 * If we found an unvisited subtree, then we go to else block to visit this subtree.
 * 
 * Our solution here always push the left subtree first, so we guarantee if we come to else block because we are searching right child
 * then our right child will guaranteed to be non null, otherwise we should fall in the if block already.
 * 
 * However, if we come to the else block for searching the left child, then we can not guarantee the left child is not null because 
 * we have not visited right subtree yet, we don't know any previous search result. So to push the left subtree, we need check if 
 * left child is null
 * 
 * After we have done the search on both subtrees, then we can update current node's status
 * We check the difference, if it is larger than 1, then report 1 immediately
 * Otherwise put current node into the Map
 * 
 * @author hpPlayer
 * @date Sep 7, 2015 2:49:43 AM
 */

public class p110_sol2 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //map record the depth of each node, which is the key part
        Map<TreeNode, Integer> hs = new HashMap<TreeNode, Integer>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            TreeNode right = node.right;
            //we will stop search either we have visited this node before or it is null
            //if we have done search in current subtree
            if( (left == null || hs.containsKey(left)) && ((right == null) || hs.containsKey(right))){
                int l = left == null ? 0 : hs.get(left);
                int r = right == null ? 0 : hs.get(right);
                //if differerd more than 1
                if(Math.abs(l - r) > 1) return false;
                //put node into the map
                hs.put(node, Math.max(l, r) + 1);
            }else{
                //we may come here because we have not visited left or visited right before
                //case node.right == null has been taken care in above if()
                //since we always insert the left child into the stack, and pop left child after reach bottom
                //if the current right child is null, we should already enter if above.
                //so if we enter here, we guarantee current node's right is not null
                //but for left, we may have reached the end, which is null now. So now need begin visit right
                if(left != null && !hs.containsKey(left)){
                    //if we enter here because left is not visited
                    stack.push(node);
                    stack.push(left);
                }else{
                	//we can safely push right to stack without checking null
                    stack.push(node);
                    stack.push(right);
                }
                
            }
        }
        
        return true;
    }
}
