/**
 * This is my perfect solution which has only one function with constant space
 * Like I discussed in sol1, the tricky part is to find the next valid child, which may be far away
 * So here we use a while loop to search nodes in current layer which has at least one child. We also use a variable "prev" to record the last 
 * valid child. So now thing becomes easy, we just set prev.next to this valid child and set this child to be "prev". We don't know if current 
 * node has one child or two children, so we will check each child and let loop continue. 
 * 
 * As sol1 indicates, the start node of next layer may differ, so we still use a variable "first" to indicate the first valid node and after we 
 * done current layer, we will start our next outer loop from this node
 * 
 * Remark:
 * a little optimization, we can set the first value when prev is null, since for each layer, prev will never be null value again
 * if we have met some nodes in current layer.
 * 
 * @author hpPlayer
 * @date Sep 13, 2015 4:55:47 PM
 */

public class p117_sol2 {
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}
    public void connect(TreeLinkNode root) {
        //if (root == null) return;//no need to check root since we set first = root in the beginning and the loop will not start when root == null
        //TreeLinkNode prev = root;
        TreeLinkNode curr = null;
        TreeLinkNode first = root;//we can use root directly to replace first, but I want to preserve the root as it is 
        TreeLinkNode prev = null;
        
        while(first != null){
            curr = first;
            first = null;
            //TreeLinkNode left = null;
            //TreeLinkNode right = null;
            prev = null;
            while(curr != null){
            	//we use below loop to skip all nodes in front in current layer that does not have child
            	//of course, we can remove it since our algorithm will do nothing to those nodes
            	//but I still keep it here as it will help skip a lot of unnecessary if-else statements
                while(curr != null && curr.left == null && curr.right == null) curr = curr.next;
                if (curr == null) break;
                if (curr.left != null){
                    if(prev != null) prev.next = curr.left;
                    //if(first == null) first = curr.left;
                    else first = curr.left;
                    prev = curr.left;//same as prev = prev.next
                }
                
                if(curr.right != null){
                    if(prev != null) prev.next = curr.right;
                    else first = curr.right;
                    //if(first == null) first = curr.right;
                    prev = curr.right;
                }
                curr = curr.next;
            }
        }
    }
}
