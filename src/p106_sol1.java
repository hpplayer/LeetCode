/*
Construct Binary Tree from Inorder and Postorder Traversal

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

/**
 * This is my original AC solution without help
 * This problem is very similar to Construct Binary Tree from Preorder and Inorder Traversal (p105)
 * Where we pick root of subtree from preorder[] and use inorder[] to decide the size of subtree, then do it recursively
 * The little difference is that current input[] is postorder[], which means the root is after left and right subtree, so we should build 
 * our tree backward. Accordingly, instead of choosing the left subtree size to locate right subtree, now we need use right subtree size to
 * locate left subtree.
 * 
 * Similar to p105, we use four parameters to indicate boundaries in two arrays. As long as left boundary does not pass the right boundary,
 * our recursion could continue. Since the root node is always behind the left and right subtree, we will always find the root backward 
 * in postorder[],then search this value in inorder[] to locate the size of left and right subtree. Then recursively find the root of two
 * subtrees and attach it to our current root, then return root
 * 
 * Sol1 and Sol2 are my own soluiton.
 * Sol1 is the recursive solution while sol2 is the iterative solution
 * 
 * Sol1 is more clear and easy-understanding but sol2 just provide another way to solve this problem
 * Both solutions are recommended
 * @author hpPlayer
 * @date Sep 12, 2015 2:27:10 PM
 */

public class p106_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return Helper(postorder, 0, postorder.length -1, inorder, 0, inorder.length -1);
    }
    
    public TreeNode Helper(int[] postorder, int p, int q, int[] inorder, int a, int b){
        if(p > q) return null;
        
        TreeNode curr = new TreeNode(postorder[q]);
        //extreme case
        if(p == q){
            return curr;
        }
        
        int i = a;
        for(; i < b; i ++){
            if(inorder[i] == postorder[q]) break;
        }
        
        int rightSize = b - i;
        
        curr.left = Helper(postorder, p, q - rightSize - 1, inorder, a, i-1);
        curr.right = Helper(postorder, q - rightSize, q -1, inorder, i+1, b);
        
        return curr;
    }
}
