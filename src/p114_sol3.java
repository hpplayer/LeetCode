/**
 * Another recursive soluion, which uses the similar idea as iterative way.
 * We do DFS on left and right subtree, and replace right subtree with left subtree and set left subtree = null
 * Then we attach the original right child to the rightmost node in left subtree. (the last line of code is very important)
 * No matter we replace right tree with null left tree, or we dont have right subtree, this step will always help us keep the oder
 * curren node -> left subtree -> right subtree
 * @author hpPlayer
 * @date Apr 3, 2015 8:43:10 PM
 */

public class p114_sol3 {
	
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		//TreeNode d = new TreeNode(4);
		//TreeNode e = new TreeNode(5);
		a.left = b;
		b.right= c;
		//a.right = e;
		//b.left = c;
		//b.right = d;
		flatten(a);
		while(a != null){
			System.out.println(a.val);
			a = a.right;
		}
	}
	public static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode (int x){
			val = x;
		}
	}
	public static void flatten(TreeNode root) {
		if(root == null) return;
		if(root.right != null) System.out.println("before: " + root.right.val);
		TreeNode right = root.right; //as talked before, we need record the right node, since we need attach it to the rightmost node in left subtree
		flatten(root.left);
		flatten(root.right);
		if(root.right != null) System.out.println("after: " + root.right.val);
		
		root.right =root.left;
		root.left = null;
		
		TreeNode currNode = root;
		while(currNode.right != null){
			currNode = currNode.right;//find rightmost node
		}
		
		//anyway we will recover the original right child here 
		currNode.right = right;//even if our left tree is null, and we set root.right = root.left, and let subtree become null 
								//this step can help us find the original rigth child node and attach it to the root

	}
}
