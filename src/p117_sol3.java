/**
 * This solution use a similar idea as sol2, i.e. we need to search the next nodes in current layer which has at least one child
 * But here we build a dummy node, and attach the next valid child after the dummy node. By using the dummy node, we can avoid the check
 * whether the first node has been set already. But the cost is that we need a dummy node to find the beginning.
 * 
 * @author hpPlayer
 * @date Sep 13, 2015 5:30:35 PM
 */
public class p117_sol3 {
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}
    public void connect(TreeLinkNode root) {
        TreeLinkNode dummy = new TreeLinkNode(0);
        while(root != null){
            TreeLinkNode curr = dummy;
            while(root != null){
                if(root.left != null){
                    curr.next = root.left;
                    curr = root.left;
                }
                
                if(root.right != null){
                    curr.next = root.right;
                    curr = root.right;
                }
                
                root = root.next;
            }
            
            root = dummy.next;
            dummy.next = null;//reset dummy.next to avoid revisiting same nodes in last layer
        }
    }
}
