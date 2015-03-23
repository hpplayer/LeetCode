import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * My AC solution without help.
 * The basic idea is similar to p139, but the difference is here: we can't break the loop if found one match substring, 
 * we have to go through all combinations
 * Remark:
 * 1) Original recursive solution can work but slow, so I have to add the hashMap as the cache to indicate if current substring can 
 * be breakable
 * 2) Since we have to find all combinations of input string, and probably it can be breakable, but we will not stop here, how can 
 * we tell, in the end, that this string is breakable?
 * I used a flag to indicate it, if found breakable flag = true, and return true, otherwise return false.
 * When we do DFS on a substring, if the cache hashtable shows this cache is not breakable, then there is no need do DFS on this string 
 * we just jump to next substring.
 *   
 * @author hpPlayer
 * @date Mar 22, 2015 8:52:17 PM
 */

public class p140_sol1 {
	public static void main(String[] args){
		//String s = "catsanddog";
		String s = "aaaaaaa";
		Set<String> hs = new HashSet<String>();
		hs.add("aaaa");
		hs.add("aa");
		hs.add("a");
		//System.out.println(hs.contains("a"));
		//hs.add("cat");
		//hs.add("cats");
		//hs.add("and");
		//hs.add("sand");
		//hs.add("dog");
		//System.out.println("abc".contains(""));
		System.out.println(new p140_sol1().wordBreak(s, hs));
	}
	
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> result = new ArrayList<String>();
        DFS(result, "", s, dict, new HashMap<String, Boolean>());
        return result;
    }
    
    public boolean DFS(List<String> result, String temp, String s, Set<String> dict, HashMap<String, Boolean> hs){
    	if(hs.containsKey(s)){
    		if(!hs.get(s)){
    			return false;
    		}
    	}
        if(s.length() == 0){
        	int length = temp.length();
            result.add(temp.substring(0, length-1));
            return true;
        }
        
        boolean flag = false;
        for(int i = 0; i < s.length(); i++){
            if(dict.contains(s.substring(0, i+1))){
            	//System.out.println(s.substring(0, i+1));
                if(DFS(result, temp + s.substring(0, i+1)+" ", s.substring(i+1), dict, hs)){
                	hs.put(s, true);
                	flag = true;
                }
            }
        }
        if(!flag){
            hs.put(s, false);
            return false;
        }else{
        	return true;
        }

    }
}
