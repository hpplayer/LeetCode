import java.util.*;

/*
Populating Next Right Pointers in Each Node

Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
     1
   /  \
  2    3
 / \  / \
4  5  6  7
After calling your function, the tree should look like:
     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
*/


/**
 * This is my original AC solution without help
 * But it does not take constant extra space as I used queue.
 * When I saw the problem, I immediately come up with BFS solution. When we visit nodes in each layer one by one, we can link their
 * children together. I created two nodes left and right, for the children fo same node, left is left child and right is right child.
 * then we update let left = right, so when visiting the next node in same layer, we will firstly try to link this updated left with 
 * the left child of next node. Since we only need to visit each node in current layer and creat the link to next node in same layer
 * we can actually get rid of queue, please see sol2 for a such solution
 * 
 * Remark:
 * 1)Since the problem states the input tree is perfect binary tree, each non leaf node will have two children and all leaf nodes are
 * at same level, so the problem is not so tricky, a harder version can be found in p117
 * 2)People can also solve this problem through the recursive way, but extra stack space will be used so it is not recommended, but I still list
 * it here, please ref to sol3
 * 
 * Sol1 is my own BFS solution which use extra non constant space
 * Sol2 is a perfect BFS iterative solution which use constant space
 * Sol3 is a DFS recursive solution which use constant space in each recursion, but will occupy non constant space in stack
 * 
 * So, sol2 is the best solution
 * @author hpPlayer
 * @date Sep 13, 2015 3:11:57 PM
 */

public class p116_sol1 {
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        Queue<TreeLinkNode> que = new LinkedList<TreeLinkNode>();
        que.offer(root);
        
        while(!que.isEmpty()){
            int size = que.size();
            TreeLinkNode left = null;
            TreeLinkNode right = null;
            for(int i = 0; i < size; i++){
                TreeLinkNode curr = que.poll();
                if(curr.left != null){
                    if(left != null) left.next = curr.left;
                    left = curr.left;
                    right = curr.right;
                    left.next = right;
                    que.offer(left);
                    que.offer(right);
                    left = right;
                } 
                
                
            }
        }
        
    }
}
