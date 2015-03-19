/**
 * DP approach. Still use matrix 
 * if we use S1 as the first row and S2 as the first column, then
 * matrix[i][j] means we take i elements from S2 and j elements from S1 and compare with i+j elements in S3. if true, then it is true
 * if not false, then false.
 * How can we filling this matrix? each matrix has two ways to get a value, either from top or from left. From top means get one element
 * from S2 and from left means get one element from S1. if newly added element equals i+j th element in S3 && the grid itself is true
 * then it will become true. One trick here is add (0,0) point to build the empty case that we don't take any element from S1 or S2.
 * Due to this operation, our index will based from 0 to 1, and every index operation will +1, so we need to subtract 1 from index
 * to get the correct char in the string
 * 
 * Remark:
 * 1) this algorithm is similar to the sequence matching taught in computational biology, this matrix is very useful in string comparison
 * and it is so smart to use matrix to represent string!
 * @author hpPlayer
 * @date Mar 17, 2015 12:35:39 AM
 */
public class p097_sol2 {
	 public boolean isInterleave(String s1, String s2, String s3) {
		 if(s1 == null || s2 == null || s3 == null) return false;
		     if(s1.length() + s2.length() != s3.length()) return false;
		     boolean[][] matrix = new boolean[s2.length()+1][s1.length()+1];//we need create one box for 0,0 case
		     matrix[0][0] = true;//all empty, it should return match
		     //we set s1 as columns, and s2 as rows
		     for(int i = 0; i <= s1.length(); i++){//i: columns
		    	 for(int j = 0; j <= s2.length(); j++){//j: rows
		    		 if(i > 0 && s1.charAt(i-1) == s3.charAt(i+j-1) && matrix[j][i-1]){
		    			 matrix[j][i] = true;
		    		 }
		    		 
		    		 if(j > 0 && s2.charAt(j-1) == s3.charAt(i+j-1) && matrix[j-1][i]){
		    			 matrix[j][i] = true;
		    		 }
		    	 }
		     }
		     return matrix[s2.length()][s1.length()];
	    }
}
