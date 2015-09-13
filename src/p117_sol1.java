/*
Populating Next Right Pointers in Each Node II

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/  
    


/**
 * This is my original AC solution without help.
 * The tricky part of this problem:
 * How to find two non null nodes in next layer, which may be a really far away.
 *        1
 *      /  \
 *     2 -> 2
 *    /    / \
 *   3 -> 3-> 3
 *  /          \
 * 4    ->      4 
 * Like example above, how can we find the the pair of 4 in leaf layer and link them together?
 * For me, I think we can still use the similar idea as p116, where we can follow next pointer to scan all nodes in current layer
 * But now we will only do operations on nodes who have child. So I use a search(), which can return the next valid child. We run it
 * two times each node to find two valid child, then link them together. Also, we will mark the first valid child in next layer, so
 * our next loop will go from that node.
 * 
 * Although my solution was accepted, as we can find from above discussion, it is not a perfect solution. as we always search next 
 * valid child in each node even we have visited it before. 
 * 
 * Solutions online may have different implementations but the main idea is similar to my sol2. Some of them may state they only use 
 * one while loop, but the time complexity should be same as our next outer while loop will start from the first valid child in next
 * layer, so we didn't waste time on unnecessary nodes.
 * 
 * Sol1 is my original AC solution with 2 functions
 * Sol2 is another my original AC solution with 1 function and shorter code
 * Sol3 is another solution use similar idea as sol2 but with different implementation
 * 
 * Sol2 is more recommended, since its speed is fast and logic is clear, and the most important thing is it my solution!
 * @author hpPlayer
 * @date Sep 13, 2015 4:21:45 PM
 */
public class p117_sol1 {
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        //TreeLinkNode prev = root;
        TreeLinkNode curr = null;
        TreeLinkNode first = root;
        
        while(first != null){
            curr = first;
            first = null;
            while(curr != null){
                TreeLinkNode prev = search(curr);
                if(prev == null) break;
                if(first == null) first = prev;
                if(curr.right != null && curr.right != prev){
                    prev.next = curr.right;
                    prev = curr.right;
                }
                TreeLinkNode second = search(curr.next);
                //if(second == null) return;
                prev.next = second;
                curr = curr.next;
            }
        }
    }
    
    public TreeLinkNode search(TreeLinkNode curr){
        //if(curr == null) return null;
        TreeLinkNode temp = curr;
        while(temp != null){
            if(temp.left != null) return temp.left;
            if(temp.right != null) return temp.right;
            temp = temp.next;
        }
        return temp;
    }
}
