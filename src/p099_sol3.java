/**
 * The follow up of problem requires us to implement an algorithm that use constant space
 * We have showed the problem can be solved by in-order traversal with the observation that we always have prev < curr
 * Usually traversal can be done with recursion or iteration, but they all take non constant space. But we have a very good algorithm
 * called Morris Traversal can take constant space.
 * 
 * How does Morris Traversal work?
 * The key is to find the rightmost node in the left subtree, or which we called predecessor.
 * Then we assign this node's right node become root node. So we create an edge that can lead us back after we done the left subtree
 * 
 * To achieve that, we have to use one pointer points to the root node, which we call curr here, and use another pointer to search the rightmost node in the left subtree,
 * which we call pred here. We firstly find and mark the pred node, then start visit left subtree. At some point we will reach the pred node again, it will lead us 
 * jump back to the root node. Then we search the left tree again and find the pred node. we will cut the edge we added and recover the structure and start visit the 
 * right subtree. So this is how Morris Traversal works.
 * 
 * In this problem, our goal is to locate the prev and curr nodes. Like I said above, pred is not prev. Prev node is always the node before current node during the visit.
 * By contrast, the pred is only a pointer node that we used to find the rightmost node in left subtree, so they are differernt. We have to use another pointer to locate the 
 * previous node, which we call prev here.
 * 
 * In sol1 and sol2, we only do check when backtracking from left path and visiting the right path. But here the backtrack is achieved by visiting the right node of rightmost 
 * node in left subtree, so we will check the nodes as long as we start visit right path, though it may not the real path but the back edge path that leads us to parent node.
 * Here we will set prev = curr, everytime before we move to the right child node
 * 
 * The time complexity is O(n), as each node is traversed at most 3 times and we have n nodes
 * Space complexity is O(1) as we only use several extra pointers
 * @author hpPlayer
 * @date Sep 11, 2015 4:23:48 PM
 */
public class p099_sol3 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;//just the previous node during the visit
        TreeNode curr = root;
        //new variable 
        TreeNode pred = null;//the pred is used to help us return to the node after done its subtree
        
        while(curr != null){
            
            //if right child is null, then after we done left subtree, prev will be root, curr will be right node
            //which is null, so by putting the comparison in front, we can guarantee curr is not null
            if(prev != null && prev.val > curr.val){
                if(first == null) first = prev;
                if(first != null) second = curr;
            }
            
            
            //if no left subtree, we can just visit current node and its right subtree
            if(curr.left == null){
                //visit right node, record prev node, in Morris traversal, right node may also be parent node
                prev = curr;
                curr = curr.right;
            }else{
                pred = curr.left;//the root of left subtree
        /*
        we will stop iterative visit left path if 1) we reach the rightmost node, and we mark it as pred 2) we reach the pred previously marked
        
        */
                while(pred.right != null && pred.right != curr){
                    pred = pred.right;//find the rightmost node (predecessor) of current left subtreee
                }
                
                //if we stop because we reach the rightmost node
                if(pred.right == null){
                    pred.right = curr;//link it back, so we are able to backtrack
                    curr = curr.left;//we have found way to go back, safely move forward
                }else{
                    //if stop because we reach the predecessor that we previously marked
                    pred.right = null;//cut the back edge, keep the original structure
                    prev = curr;//visit right subtree, here prev is always the parent of curr
                    curr = curr.right;//reach pred means we have done the left tree, now we can go right tree
                }
            }
            
        }
        
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
