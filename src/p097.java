/**
 * Recursion approach is time consuming and cannot pass extreme long test cases
 * @author hpPlayer
 * @date Mar 17, 2015 12:14:44 AM
 */
public class p097 {
	public static void main(String[] args){
		p097 test = new p097();
		System.out.println(test.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
		System.out.println(test.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
	}
	  public boolean isInterleave(String s1, String s2, String s3) {
		     if(s1.length() + s2.length() != s3.length()) return false;
		        
		       return recursion(s1, 0, s2, 0, s3, 0); 
	    }
	  
	  public boolean recursion(String s1, int p1, String s2, int p2, String s3, int p3){
		  if(p3 == s3.length()) return true;
		  if(p1 == s1.length()) return s3.substring(p3).equals(s2.substring(p2));
		  if(p2 == s2.length()) return s3.substring(p3).equals(s1.substring(p1));
		  if(s3.charAt(p3) == s1.charAt(p1) && s3.charAt(p3) == s2.charAt(p2)){
			  return recursion(s1, p1+1, s2, p2, s3, p3+1) || recursion(s1, p1, s2, p2+1, s3, p3+1);
		  }else if (s3.charAt(p3) == s1.charAt(p1)){
			  return recursion(s1, p1+1, s2, p2, s3, p3+1);
		  }else if (s3.charAt(p3) == s2.charAt(p2)){
			  return recursion(s1, p1, s2, p2+1, s3, p3+1);
		  }else{
			  return false;
		  }
		  
	  }
}
