import java.util.*;
/**
 * This is rewrite of my sol1. We still need an indicator for direction.
 * In one direction, we always use addLast() and pollFirst(), we add left first then add right
 * In the other direction, we always use addFirst() and pollLast(), we add right first then add left
 * We don't care about which one matches forward and which one matches backward.
 * but we do care about the initial indicator. Since the first loop must goes to forward direction, so the add left must goes first
 * 
 * Remark:
 * 1) The basic idea is similar to BFS, but we add Nodes more smartly
 * 2) It is better to draw figures to check the correctness
 * @author hpPlayer
 * @date Sep 11, 2015 8:08:10 PM
 */
public class p103_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) return result;
        Deque<TreeNode> que = new LinkedList<TreeNode>();
        que.addLast(root);
        boolean isForward = false;
        while(!que.isEmpty()){
            int size = que.size();
            LinkedList<Integer> lst = new LinkedList<Integer>();
            for(int i = 0; i < size; i++){
                if(isForward){
                    TreeNode curr = que.pollFirst();
                    if(curr.right != null) que.addLast(curr.right);
                    if(curr.left != null) que.addLast(curr.left);
                    lst.add(curr.val);
                }else{
                    TreeNode curr = que.pollLast();
                    if(curr.left != null) que.addFirst(curr.left);
                    if(curr.right != null) que.addFirst(curr.right);
                    
                    lst.add(curr.val);
                }
                
            }
            result.add(lst);
            isForward = isForward? false : true;
        }
        return result;
    }
}
