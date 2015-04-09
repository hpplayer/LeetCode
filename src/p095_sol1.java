import java.util.*;
/*
 * Unique Binary Search Trees II 
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

   For example,
   Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 
 *
 */
/**
 * I didn't not work it out.
 * But it is very similar to recursion version of p96_sol1
 * how can attach collection subtree to current node? use list!
 * use list to store the head of collection of subtree, we generate all combination of left subtree and right subtree
 * add attach to current treeNode, then return this new group of combination up.
 * Here, we also used the HashMap as a cache to speed up the operation 
 * 
 * @author hpPlayer
 * @date Apr 8, 2015 6:08:55 PM
 */


public class p095_sol1 {
	public static void main(String[] args){
		for(TreeNode t : generateTrees(1)){
			System.out.println(t.val);
		}
	}
      public static class TreeNode{
    	  int val;
    	  TreeNode left;
    	  TreeNode right;
    	  TreeNode(int x){
    		  val = x;
    		  left = null;
    		  right = null;
    	  }
      }
	  public static List<TreeNode> generateTrees(int n) {
	        if(n==0){
	            List<TreeNode> temp = new ArrayList<TreeNode>();
	            temp.add(null);
	            return temp;
	        }
	        return DFS(1, n, new HashMap<String, List<TreeNode>>());
	    }
	    
	    public static List<TreeNode> DFS(int left, int right, HashMap<String, List<TreeNode>> hs){
	        if(hs.containsKey(left + " " + right)){
	            return hs.get(left + " " + right);
	        }
	        List<TreeNode> list = new ArrayList<TreeNode>();
	        if(right < left){
	            list.add(null);
	            return list;
	        }
	        if(right == left){
	        	list.add(new TreeNode(left));
	        	return list;
	        }
	        
	        for(int i = left; i <= right; i++){
	        	//in p96, we can simply return the m*n, here we need return real TreeNodes
	            List<TreeNode> leftNodes = DFS(left, i-1, hs);
	            List<TreeNode> rightNodes = DFS(i+1, right, hs);
	            for(int j = 0; j < leftNodes.size(); j++){
	                for(int l = 0; l < rightNodes.size(); l++){
	                	//System.out.println("im here");
	                    TreeNode leftNode = leftNodes.get(j);
	                    TreeNode rightNode = rightNodes.get(l);
	                    TreeNode node = new TreeNode(i);
	                    node.left = leftNode;
	                    node.right = rightNode;
	                    list.add(node);
	                }
	                
	            }
	        }
	        hs.put(left + " " + right, list);
	        return list;
	    }
}
