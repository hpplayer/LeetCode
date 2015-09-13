/**
 * This is a very brilliant solution. The search in LinkedList is O(n), we don't want to waste time to search nodes in linkedlist.
 * So here, we just follow the linkedlist to build the tree. The nodes in linkedlist is like left subtree -> current node -> right subtree
 * So actually we will build the tree bottom-up to the left subtree, then append it to current node, then build the right tree, and append it 
 * to current node. We will strictly follow the linkedlist without jumps, so the final time complexity is still O(n)
 * 
 * To distinguish the boundaries of left subtree, current node and right subtree, we still need two pointers to indicate the ranges. When range
 * is 1, it means we are looking at the single leaf node, so we can just create a new node, then return this node, the most important thing is 
 * don't forget moving the pointer in linkedlist as well.
 * 
 * The core idea is:
 * 1) To follow the order of linkedlist, we will firstly build the left subtree
 * 2) We use start and end pointers to find the range of left subtree, recursively to create the structure of left subtree
 * 3) after we reach the leaf node, we will return back and fill nodes in left subtree
 * 4) After we fill all nodes in left subtree and current node, we will start create the structure of right subtree, so we will strictly follow
 * the structure of linkedlist
 * 5) After we done the right subtree, we will return current node to upper recursion.
 * 6) Since our pointer in linkedlist should move forward each time we fill a node, it is better to use a global variable so that our recursion
 * can get its corresponding node in linkedlist
 * 
 * Time complexity:
 * O(n) to find the length of linkedlist, which will be used as the range for inital root, O(n) to scan the linkedlist to build our tree, so 
 * the total running time is O(n)
 * 
 * @author hpPlayer
 * @date Sep 12, 2015 6:30:29 PM
 */

public class p109_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	 
    private ListNode curr = null;
    public TreeNode sortedListToBST(ListNode head) {
        curr = head;
        int len = 0;
        while(head != null){
            head = head.next;
            len ++;
        }
        
        return DFS(0, len-1);
    }
    
    public TreeNode DFS(int start, int end){
        if(start > end) return null;//boundary case
        if(start == end){
            TreeNode p = new TreeNode(curr.val);
            curr = curr.next;//we have used curr.val, so move pointer forward
            return p;
        }
        
        int mid = start + (end - start)/2;
        TreeNode left = DFS(start, mid -1);
        //curr will be updated during visint left, when we get here, curr should point to left's parent value
        TreeNode p = new TreeNode(curr.val);
        curr = curr.next;//we have used curr.val, so move pointer forward
        p.left = left;
        
        TreeNode right = DFS(mid+1, end);
        p.right = right;
        
        return p;
    }
}
