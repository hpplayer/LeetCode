/**
 * this is another approach, instead of using DFS, we pass the min and max value to each node to check if its value is in range.
 * When branching left node, the max value should be updated = current node's value, and when branching right node, the min value should 
 * be updated = current node's value;
 * 
 * Remark: 
 * 1) in LeetCode, we dont allow root.val = left.val || root.val = right.val. But the real case may differ, see explanations below
 * 2) we use Integer to pass value, so we can make boundary case to NULL (also, we can use long.max and long.min as boundary case to
 * deal with cases that node's value = Integer.MIN_VAL || value = Integer.MAX_VAL; 
 */
public class p098_sol3 {
    public static class TreeNode {
	    int val;
	      TreeNode left;
	   TreeNode right;
	   TreeNode(int x) { val = x; }
	  }
    
  public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
  
  public boolean isValidBST(TreeNode root, Integer min, Integer max){
	  if(root == null) return true;
	  //here we even dont allow left node = parent node, if we allow that, we need change it to root.val > max instead, so 
	  //search left, max value is its parent node, and we make left.val = parent.val = true
	  if((min != null && root.val <= min) || (max != null) && root.val >= max){
		  return false;
	  }
	  return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
  }
}
