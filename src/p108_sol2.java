import java.util.*;
/**
 * Ok, this is iterative solution towards this problem.
 * In sol1, we recursively build the left and right subtree, then attach it to our current Node, and we use (start, mid-1) and 
 * (mid +1, end) to locate the range of left subtree and right subtree, the mid points of these two ranges will be roots of two 
 * trees respectively.
 * 
 * To implement this idea into the iterative solution, we have to use an inner class where we can locate the range of subtree rooted 
 * at each node, here we call this class MyNode. Then things become general, we use a preorder traversal to visit each nodes in tree
 * we can easily locate the root of left and right subtree by simply use (start + mid-1)/2 and (mid + 1 + end)/2. Then we build two 
 * new roots, and insert into our stack. If we found the left boundary passed its mid point -1 or the mid pointer + 1 passed right boundary
 * then we know we have reached the null case, and we will not insert it into our stack, otherwise we will insert the left and right root
 * accordingly
 * 
 * 
 * @author hpPlayer
 * @date Sep 12, 2015 5:11:17 PM
 */

public class p108_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    public class MyNode{
        TreeNode node;
        int start;
        int end;
        
        public MyNode(int start, int end, TreeNode node){
            this.start = start;
            this.end = end;
            this.node = node;
        }
    }
    
    
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length ==0 ) return null;
        
        Stack<MyNode> stack = new Stack<MyNode>();
        int mid = 0 + (nums.length -1 - 0)/2;
        TreeNode root = new TreeNode(nums[mid]);
        MyNode MyRoot = new MyNode(0, nums.length -1, root);
        stack.push(MyRoot);
        while(!stack.isEmpty()){
            MyNode curr = stack.pop();
            int oldMid = curr.start + (curr.end - curr.start)/2;
            if(oldMid -1 >= curr.start){
                mid = curr.start + (oldMid-1 - curr.start)/2;
                root = new TreeNode(nums[mid]);
                stack.push(new MyNode(curr.start, oldMid - 1, root));
                curr.node.left = root;
            }
            
            if(oldMid +1 <= curr.end){
                mid = oldMid +1 + (curr.end - oldMid -1)/2;
                root = new TreeNode(nums[mid]);
                stack.push(new MyNode(oldMid + 1, curr.end, root));
                curr.node.right = root;
            }    
        }
        
        return MyRoot.node;
    }
}
