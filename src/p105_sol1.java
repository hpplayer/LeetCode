/*
Construct Binary Tree from Preorder and Inorder Traversal

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/


/**
 * We will firstly solve this problem with recursion
 * There may be a bunch of different ways to write the recursive solution, but I will use my code below to illustrate the solution,
 * because I think it is more clear and easy-to-understand
 * 
 * Firstly lets talk about the basic idea, how to solve this problem recursively?
 * For example now we have two arrays preorder[1,2,3] and inorder[2,1,3]
 * We found the root node 1 is the first element in preorder[], if we search node 1 in inorder[], we will find its the second element in the
 * inorder[], and the left part of inorder[] is the left subtree of node 1, while the right part of inorder[] is the right subtree of node 1
 * So the basic idea is
 * 1) we found the root node of a subtree in preorder[]
 * 2) then search this node in inorder[]
 * 3) then we will know the size of left subtree and the size of right subtree
 * 4) By knowing the size of two subtrees, we can immediately find the root node of two subtrees. The root of left subtree if we have one is
 * just the node next to our current node, the root of right subtree if we have one is the node that size of left subtree away from current node
 * 5) We then do the same things to those two subtrees and the recursion will start and help us attach the correct children nodes
 * 6) To track the size of left and right subtree, we keep record and update the boundaries of current subtree. The left subtree size will be 
 * the  ( index of root - left boundary ), the right subtree size will be the ( right boundary - index of root ).
 * 
 * Remark:
 * 1) Some solutions may optimize the solution by remove some edge boundaries, since we can calculate the right size by just knowing the total
 * size and left subtree size. But I still keep all boundaries here to keep my solution consistent and clean
 * 2) the corner case is when the left boundary exceed the right boundary, we check it by ethier p > q or a > b
 * 3) The bottom case is when left == right, we know current subtree only has one node so we can just return node to speed up program
 * 
 * Sol1 is a very short and clean recursive solution
 * Sol2 is a complex iterative solution and it is extremely difficult to understand
 * But sol2 provides a way to solve the problem in iterative way
 * 
 * So both sol1 and sol2 are recommended
 * @author hpPlayer
 * @date Sep 11, 2015 11:40:12 PM
 */
public class p105_sol1 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    
    //p and q are the boundaries of preorder[], a and b are the boundaries of inorder[]
    public TreeNode helper(int[] preorder, int p, int q, int[] inorder, int a, int b){
        if(p > q) return null;//we always start search from preorder, so we only need check preorder[], but replaced with (a > b) is also accetpable 
        
        
        //find the pivot
        int pivot = preorder[p];
        //create new node based on pivot
        TreeNode curr = new TreeNode(pivot);
        
        if(p == q) return curr;
        
        int i = a;//start search pivot in inorder[]
        for(; i < b; i++){
            if(inorder[i] == pivot) break;
        }
        
        //now i is the index of pivot in inorder[], we can get size of left and right subtree
        int leftSize = i - a;//a is the start of current subtree in inorder[], their difference is left size
       
       
        curr.left = helper(preorder, p+1, p+leftSize, inorder, a, a + leftSize - 1);
        curr.right = helper(preorder, p+ leftSize+1, q, inorder, i+1, b);
        
        return curr;
        
    }
}
