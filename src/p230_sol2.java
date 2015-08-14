import java.util.Stack;
/**
 * This is my second original AC solution without help.
 * Although I look up p74 too see the iterative way of inorder traversal
 * Here is the idea:
 * while(node != null){
 * push(node)
 * node = node.left
 * }
 * if node == null, that means we need go back, and add right nodes, let pop top node from stack and let node = node.right
 * It also means we need start our counter now. So we let k-- in each back visit
 * 
 * @author hpPlayer
 * @date Aug 14, 2015 2:20:41 PM
 */
public class p230_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        while(!stack.isEmpty() || curr != null){
            if(curr!= null){
                stack.push(curr);
                curr = curr.left;
            }else{//need go back
                TreeNode temp = stack.pop();
                if (--k == 0){
                    return temp.val;
                }
                curr = temp.right;
            }

        }
        
        return root.val;
    }
}
