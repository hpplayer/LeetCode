import java.util.*;
/**
 * DFS recursive solution. Same as p102, here we are using DFS, so we need an extra argument depth to tell the recursion how deep it is
 * We used a trick here. By mod 2, we can now if current layer is even or odd.
 * Based on the problem description, we will add nodes forward in even layer and add nodes backward in odd layer
 * Because each recursion always goes to left child first then goes to right child. It is safe to add our nodes based on layer number
 * if it is even layer, we add left first then right, which is true
 * if it is odd layer, we add left from front first then right, which is also true
 * 
 * Remark:
 * 1) use arraylist as result list, so search can be done in O(1)
 * 2) use linkdelist as inner list, so addlast and addfirst can be done in O(1)
 * 3) since depth is 0 based, if the size of result is same with depth, then it means our result does not include current layer, so we need
 * insert a new list into the result
 * 
 * @author hpPlayer
 * @date Sep 11, 2015 8:33:01 PM
 */
public class p103_sol3 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	   public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	        //arraylist help us search in O(1)
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        DFS(root, 0, result);
	        
	        return result;
	    }
	    
	    public void DFS(TreeNode root, int depth, List<List<Integer>> result){
	        if(root == null) return;
	        
	        //depth is 0 based, so if the size is same, it means we have not add current layer to result
	        if(depth == result.size()){
	            //linkedlist help us insertfront and insertback in O(1)
	            result.add(new LinkedList<Integer>());
	        }
	        
	        LinkedList<Integer> lst = (LinkedList<Integer>) result.get(depth);
	        
	        if(depth%2 == 0){//even layer, add forward
	            lst.add(root.val);    
	        }else{
	            lst.add(0, root.val);//odd layer add backward
	        }
	        
	        DFS(root.left, depth+1, result);
	        DFS(root.right, depth+1, result);
	    }
}
