/**
 * I am in mass...didnt work out this simple problem...
 * see solution2
 * @author hpPlayer
 * @date Mar 3, 2015 12:56:41 PM
 */
public class p098_sol1 {
	 public static boolean isValidBST(TreeNode root) {
		 //System.out.println(isValidBST(root.left, "l")&& isValidBST(root.left, "r"));
		 return isValidBST(root, "l") &&  isValidBST(root, "r");
		 
	 }
	  public static boolean isValidBST(TreeNode root, String dir) {
	
	        if(root == null) return true;
		
	        if(root.left == null && root.right == null) return true;
	        
	        if(root.left != null){

	            if(root.left.val >= root.val){
	    	    // 	System.out.println("im here");
	                return false;
	            }
	        }

	        if(root.right != null){
	        //	System.out.println("im here");
	            if(root.right.val <= root.val){
	    	     	//System.out.println("im here");
	                return false;
	            }
	        }
	        //done boundary condition
	       TreeNode temp = root;
	      // int smallest = Integer.MIN_VALUE;
	       int count = 0;
	       while(temp.left != null){
	    	   count ++;
	           temp = temp.left;//get leftmost node
	       }
	       if(dir.equals("l")){
	           if(count > 1){
			       if(temp.val >= root.val){
				     //	System.out.println("im here");
			            return false;
			       }
		       }   
	       }else if (dir.equals("r")){
	    	      if(count > 1){
				       if(temp.val <= root.val){
					     	//System.out.println(root.val);
				            return false;
				       }
			       }   
	       }
	

	      // System.out.println(root.val);  
	       temp = root;//reset temp
	       count = 0;
	        while(temp.right != null){
	        	count++;
	           temp = temp.right;//get leftmost node
	       }
		    if(dir.equals("l")){
		        if(count > 1){
		        	//System.out.println(temp.val);
		 	       if(temp.val >= root.val){
			            return false;
				       }
			       }
		    }else if (dir.equals("r")){
		    	 if(count > 1){
				        if(temp.val <= root.val){
				            return false;
					     }
		    	 }

		    }
		    


	            
	       if(root.left != null && root.right != null){
	           return(isValidBST(root.left, "l") && isValidBST(root.left, "r") && isValidBST(root.right, "l") && isValidBST(root.left, "r"));
	       }
	       else if(root.left != null) {
	    	  
	           return(isValidBST(root.left, "l") && isValidBST(root.left, "r"));
	       }
	       else//(root.right != null)
	       {
	    	   
	           return(isValidBST(root.right, "l") && isValidBST(root.left, "r"));
	       }

	    }
	  
	  public static class TreeNode {
		    int val;
		      TreeNode left;
		   TreeNode right;
		   TreeNode(int x) { val = x; }
		  }
	  
	  public static void main(String args[]){
		  /*
		  TreeNode a = new TreeNode(10);
		  TreeNode b = new TreeNode(5);
		  TreeNode c = new TreeNode(15);
		  TreeNode e = new TreeNode(6);
		  TreeNode f = new TreeNode(20);
		  a.left = b;
		  a.right = c;
		  c.left = e;
		  c.right = f;
		  */
		  TreeNode a = new TreeNode(0);
		  TreeNode b = new TreeNode(-1);
		  a.left = b;
		  System.out.println(isValidBST(a));
	  }
}
