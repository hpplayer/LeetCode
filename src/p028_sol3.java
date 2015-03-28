import java.util.HashMap;
/**
 * My recursive approach, and it gives memory exceed error..
 * But it should work.
 * 
 * @author hpPlayer
 * @date Mar 27, 2015 8:10:20 PM
 */

public class p028_sol3 {
	public static Integer index = null;
    public static int strStr(String haystack, String needle) {
    	//DFS(haystack, needle, new HashMap<String, Boolean>());
    	if(index != null) return index;
    	return -1;
    }
	 public static boolean DFS(String h, String n, HashMap<String, Boolean> hs){
	    	if(hs.containsKey(h)) return hs.get(h);
	    	if(n.length() == 0) return true;
	    	for(int i = 0; i < h.length(); i++){
	    		if(h.charAt(i) == n.charAt(0)){
	    			if(DFS(h.substring(1)+"", n.substring(1)+"", hs)){
	    				if(index == null) index = i;
	    				hs.put(h, true);
	    				return true;
	    			}else{
	    				index = null;
	    			}
	    		}
	    	}
	    	hs.put(h, false);
	    	return false;
	    }
}
