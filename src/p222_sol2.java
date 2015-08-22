/**
 * This is iterative solution
 * The basic idea is same except that we simply let root = root.left or root = root.right instead of 
 * recursive calls
 * @author hpPlayer
 * @date Aug 21, 2015 6:24:35 PM
 */

public class p222_sol2 {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	   public int getHeight(TreeNode root){
	        int height = 0;
	        while(root != null){
	            root = root.left;
	            height++;
	        }
	        return height;
	    }
	    public int countNodes(TreeNode root) {
	        int num = 0;
	        while (root != null){
	            int left = getHeight(root.left);
	            int right = getHeight(root.right);
	            if(left == right){
	                num += 1<<left;
	                root = root.right;
	            }else{
	                num += 1<<right;
	                root = root.left;
	            }
	        }
	        
	        return num;
	    }
}
