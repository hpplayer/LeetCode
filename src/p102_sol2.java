import java.util.ArrayList;
import java.util.List;

/**
 * This is DFS solution. We need to tell each layer how deep it is, so we we have a depth argument here
 * Based on this depth, we can retrieve the list stored in result, and add current root.val
 * We found that the size of result is numbers of layer we have inserted into the result.
 * layer number starts from index 0, so if current layer is already inserted into the result, the depth is supposed to be smaller
 * than result size. Otherwise we know we have not inserted into current layer, and need declear a new list then insert
 * 
 * @author hpPlayer
 * @date Sep 11, 2015 5:16:50 PM
 */
public class p102_sol2 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        DFS(root, result, 0);
        return result;
    }
    
    public void DFS(TreeNode root, List<List<Integer>> result, int depth){
        if(root == null) return;
        
        if(result.size() == depth){
            result.add(new ArrayList<Integer>());
        }
        
        result.get(depth).add(root.val);
        
        DFS(root.left, result, depth + 1);
        DFS(root.right, result, depth + 1);
    }
}
