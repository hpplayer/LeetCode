import java.util.*;
/*
Binary Tree Preorder Traversal

Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
*/
/**
 * My AC solution without help
 * At first I thought it is similar to p94(Binary Tree Inorder Traversal) with little changes,
 * so I used queue instead of stack, but later found it is wrong
 * Since when reach the leaf, we should not return to the very first node in the queue, which should be root, in the first loops,
 * we still need to add the right subtree of current node into the container before we visit the right subtree of root,
 * so it should still use the stack.
 * 
 * But in the in-order traversal, we always add node to the path, when we reach null and start pop from stack, since our alg in in-order is like
 * push current, push left, try to add left's left and reach leaf, pop left, try to add left'right and reach leaf, pop current, push current's right
 * so as long as we add the path when we pop from stack, our order would be kept.
 * (current : a, left: b, right: c, the pop order: b->a->c, so follow in-order alg)
 * 
 * Here, we greedily add new nodes into our path whenever we can push it into the stack.
 * And we will follow this order:
 * add current node, add current node.left (node a) if not null, add (node a).left node (node b) if not null, if now we reach the leaf
 * then we add the node b's right node if not null, if it is null, then we try to add the a'right node if not null, if it is null we try 
 * to add current node's right node. This order perfectly follow pre-order algorithm
 * (current : a, left: b, right: c, the insert into stack order: a->b->c, so follow pre-order alg)
 * 
 * Remark:
 * we don't want push null to path and stack, so every time before we add a new node, we need check if the node is null, if it is then dont add
 * it into our container.
 * 
 * The main structure is still very similar to in-order traversal
 * 
 * a more concise iterative way, please ref to sol2
 * @author hpPlayer
 * @date Apr 11, 2015 1:02:14 AM
 */
public class p144_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(4);
		b.right = c;
		b.left =a ;
		a.right = d;
		//c.right = d;
		System.out.println(preorderTraversal(b));
	}
	public static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> Path = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root != null){
            stack.add(root);
            Path.add(root.val);
        }
        TreeNode temp = root;
        int i = 0;
        while(!stack.isEmpty() || temp != null){
            //System.out.println("i "  + ++i);
        	if(temp != null){
                temp = temp.left;
                if(temp != null){
                    stack.push(temp);
                    Path.add(temp.val);
                }
            }else{
                temp = stack.pop();
                //System.out.println(temp.val);
                temp = temp.right;
                if(temp != null){
                   stack.add(temp);
                   Path.add(temp.val);
                }
            }
        }
        return Path;
    }
}
