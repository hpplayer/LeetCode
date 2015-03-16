/**
 * test code 
 * @author hpPlayer
 * @date Mar 15, 2015 1:27:38 AM
 */
public class p010_sol3 {
	public static void main(String[] args) {
		String a = "a";
		String b = "ab*";
		System.out.println(a.matches(b));
		System.out.println(isMatch(a, b));
	}
	
	 public static boolean isMatch(String s, String p) {
	        return DFS(s, p, 0, 0);
	    }
	    
	    public static boolean DFS(String s, String p, int indexInS, int indexInP){
	        if(indexInP == p.length()){//if our IndexInP has reached the end
	            return indexInS == s.length();//is our IndexInS also reach end?
	        }
	        
	        if(indexInP == p.length() -1 || p.charAt(indexInP+1) != '*'){//we may have  two cases that dont have "*" in the next
	            if(!isValidPair(s, p, indexInS, indexInP)){
	                return false;//if not matched return false
	            }else{
	                return DFS(s, p, indexInS+1, indexInP +1);//if matched, continue DFS
	            }
	        }else{//case our next char in P is "*"
	        	//System.out.println("im here");
	            //case if we can skip a*, ex: "a", "ab*", after "a" matched "b*"" and " " may not valid, but we DFS should be true
	            //i.e. when do dfs on "b*", we may still come to this case that we dont use "b*", and both string becomes empty
	           // if(isValidPair(s, p,indexInS,indexInP+2 )){//we can try case we can skip a*
	                if(DFS(s, p, indexInS, indexInP+2)){
	                //	System.out.println("im here");
	                    return true;//if it work, then no need look below
	                }
	            //}
	            //we look at current indexInP and indexInS
	            while(isValidPair(s,p,indexInS,indexInP)){//try different number
	                if(DFS(s, p, indexInS+1, indexInP+2)){//we are looking for different pair, start with use 1 a*, indexInS +1 since we have 
	                //used indexInS in current pair with indexInP
	                    return true;
	                }
	                //indexInS ++, means we are skipping a in string s, we are here since current char are matched, we are trying to find 
	                //next char in S that matched p.charAt(indexInP+2),
	                indexInS ++; //case we use different a*(2, 3, ...), so we can skip different bits in S, until we found the correct one
	            }
	            //if current char in P and char in S does not match, then this DFS is invalid, all we cant find such indexInS that match p.charAt(indexInP+2)
	            return false;
	 
	        }
	       // return false; // no need, since we have if else, and we will never reach here
	    }
	    
	    public static boolean isValidPair(String s, String p, int indexInS, int indexInP){
	        if(indexInS >= s.length() || indexInP >= p.length()) return false;
	        return (s.charAt(indexInS) == p.charAt(indexInP) || p.charAt(indexInP) == '.');
	    }
}
