import java.util.*;
/*
Binary Tree Postorder Traversal 

Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].
*/

/**
 * My AC solution without help
 * 
 * Basic idea:
 * I solved this problem in following way:
 * In post-order traversal, our traversal order should like 
 * left -> right -> mid
 * Thus, in our stack, the order should like
 * mid-> right -> left
 * So, in this time, we need firstly visit the right node
 * However, if we only use one stack, then we cannot jump from right to mid, then to left, since 
 * we need somewhere to store mid point when we visit right node(Using one stack, the mid will be the bottom, and we can't get 
 * mid.
 * 
 * I firstly pick queue as the secondary structure, but it is incorrect
 * Since if our root has right node, and this right node has only one child(Node a), then using queue, when visited the a, we will dequeue root 
 * jump to the left node, and skip the right node.
 * 
 * So then I found a secondary stack will be fine, since it will still help us get the correct order when backtrack
 * I found several other approach also uses two stacks
 * 
 * For result use only one stack, see sol2
 * @author hpPlayer
 * @date Apr 14, 2015 7:16:43 PM
 */
public class p145_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(3);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(4);
		//TreeNode d = new TreeNode(1);
		a.left = b;
		a.right = c;
		//c.left = d;
		for(Integer i : postorderTraversal(a)){
			System.out.println(i);
		}
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static List<Integer> postorderTraversal(TreeNode root) {
		Stack<TreeNode> Stack = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		List<Integer> path = new ArrayList<Integer>();
		
		TreeNode temp = root;
		while(!stack2.isEmpty() || temp != null){
			if(temp != null){
				Stack.push(temp);
				stack2.push(temp);
				temp = temp.right;
			}else{
				temp = stack2.pop();
				temp = temp.left;
			}
		}
		
		while(!Stack.isEmpty()){
			path.add(Stack.pop().val);
		}
		return path;
	}
}
