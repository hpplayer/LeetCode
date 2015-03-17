/**
 * Similar to sol3, but use substring version instead of pointer.
 * It becomes slower, since substring() costs O(n) time on each string
 * @author hpPlayer
 * @date Mar 16, 2015 2:44:01 PM
 */
public class p010_sol4 {
	public static void main(String[] args) {
		String a = "aaba";
		String b = "ab*a*c*a";
		System.out.println(a.matches(b));
		System.out.println(isMatch(a, b));
	}

	 public static boolean isMatch(String s, String p) {
	        return DFS(s, p);
	    }
	    
	    public static boolean DFS(String s, String p){
	        if(p.length() == 0){
	        	//System.out.println("Im herer");
	            return s.length() == 0;
	        }
	        
	        if(p.length() == 1 || (p.length() >1 && p.charAt(1) != '*')){
	            if(isValid(s, p)){
	                return DFS(s.substring(1), p.substring(1));
	            }else{
	                return false;
	            }
	        }else{
	            if(DFS(s, p.substring(2))) return true;
	            int offset = 0;
	
	            while(isValid(s.substring(offset), p) && offset < s.length()){
	             //	System.out.println(s);
                	//System.out.println(p);
	            	   offset++;
	            	//System.out.println(offset);
	                if(DFS(s.substring(offset), p.substring(2))){
	           
	                	//System.out.println("im herer");
	                	return true;
	                }
	             
	            }
	            return false;
	        } 
	    }
	    
	    public static boolean isValid(String s, String p){
	        if(s.length() <=0 || p.length() <= 0) return false;
	        return s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
	    }
}
