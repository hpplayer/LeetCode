import java.util.LinkedList;
import java.util.List;
/**
 * This is a modified version of recursive solution
 * There are two improvements here, and it is more close to my iterative solution
 * Improvement 1:
 * We only do the recursive call when the next node is not null, so we can safely assume every recursive call is on an available node
 * Improvement 2:
 * Since each recursive call is on available nodes, we can safely add "->" when calling next node, thus we don't have to the check if the node 
 * is the first or last node as in sol1, which will shorten our code
 * 
 * But basically the idea is similar to sol1
 * 
 * 
 * @author hpPlayer
 * @date Aug 23, 2015 12:47:14 PM
 */
public class p257_sol3 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new LinkedList<String>();
        if (root == null) return result;
        DFS(root, "", result);
        return result;
    }
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		a.left = b;
		a.right = c;
		for (String str : new p257_sol3().binaryTreePaths(a)){
			System.out.println(str);
		}
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
    //always insert "->" before visiting next available node
    public void DFS(TreeNode root, String str, List<String> result){
        str += root.val;
        //leaf node
        if (root.left == null && root.right == null){
            result.add(str);
            return;
        }
        
        if(root.left != null){
            DFS(root.left, str + "->", result);
        }
        
        if(root.right != null){
            DFS(root.right, str + "->", result);
        }
    }
}
