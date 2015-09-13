import java.util.ArrayList;

/*
Convert Sorted List to Binary Search Tree

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/

/**
 * This is my original AC solution without help
 * But this is not a good solution to this problem.
 * As here, I firstly convert the linkedlist to arraylist, which would cost O(n), then I will build the tree based on arraylist, which would 
 * still cost O(2n). The main program is similar to p108, except that we need build an arrayList before doing the recursive call
 * But we have a better solution, which will build the tree bottom-up instead of up-bottom, and use the property of linkedlist
 * 
 * 
 * Sol1 is my simple solution which use O(2n) space, and O(2n) time
 * Sol2 is a very smart recursive solution which use O(lgn) space and O(2n) time
 * So sol2 is more recommended.
 * 
 * 
 * It is hard to convert sol2 to an iterative solution, I will try to write an iterative solution out, stay tune!
 * 
 * 
 * @author hpPlayer
 * @date Sep 12, 2015 5:36:59 PM
 */


public class p109_sol1 {
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
	 
    public TreeNode sortedListToBST(ListNode head) {
        ArrayList<Integer> ary = new ArrayList<Integer>();
        ListNode curr = head;
        while(curr != null){
            ary.add(curr.val);
            curr = curr.next;
        }
        
        return DFS(ary, 0, ary.size()-1);
    }
    
    public TreeNode DFS(ArrayList<Integer> lst, int start, int end){
        if(start > end) return null;
        if(start == end) return new TreeNode(lst.get(start));
        
        int mid = start + (end - start)/2;
        
        TreeNode curr = new TreeNode(lst.get(mid));
        
        curr.left = DFS(lst, start, mid -1);
        curr.right = DFS(lst, mid +1, end);
        
        return curr;
    }
}
