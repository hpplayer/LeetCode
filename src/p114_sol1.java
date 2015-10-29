/**
 * My recursive AC solution, without help
 * Basically, we will add right subtree to the rightmost node of left subtree
 * so we need record the old right, so that after updating right node after done DFS on left subtree, we would know 
 * how to attach right subtree to the left subtree.
 * We also need to take care of different boundary cases:
 * return currNode if it is leaf node(left and right subtree both == null)
 * if left subtree is null, then do DFS on right subtree,
 * if right subtree is null return left subtree
 * 
 * I firstly made mistakes what should be return if right subtree is null. It turns out to be that we need return left subtree
 * based on the given requirement, the order should be currNode ->left subtree -> right subtree
 * 
 * Also, it is better to set left subtree to null after we have done DFS on this subtree. Also if we dont do that, leetcode will return
 * TLE error, due to it is checking all tree(left and right subtree)
 * 
 * Iterative version see sol2
 * @author hpPlayer
 * @date Apr 3, 2015 7:26:39 PM
 */
public class p114_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(4);
		TreeNode e = new TreeNode(5);
		a.left = b;
		b.left= c;
		c.left = d;
		d.left = e;
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
    	if (root == null) return;
        DFS(root);
    }
    
    public static TreeNode DFS(TreeNode currNode){
        if(currNode.left == null && currNode.right == null) return currNode;//leave node
    	//System.out.println(currNode.val);
        //TreeNode left = currNode.left;
        TreeNode right = currNode .right;
        TreeNode leftLast = null;
        //if(left != null){
        if(currNode.left != null){
            leftLast = DFS(currNode.left);
            //System.out.println(leftLast.val);
            currNode.right = currNode.left;
            leftLast.right = right;
        }
       
        
        if(right == null){
        	currNode.left = null;//set left to null, without it, tests will return TLE error, why? maybe because the test cases test all nodes
        					    //without it, it will take nlogn time to visit results, instead of n
        	return leftLast;//I originally made mistake here 
        }
        TreeNode rightLast = DFS(right);
        currNode.left = null;
        return rightLast == null? leftLast : rightLast;
 
    }
}
