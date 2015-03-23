import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Other people's solution..similar to my solution
 * If a string can be breakable, then do DFS, otherwise, dont bother do DFS
 * But it implements in another way, which can be used as reference
 * Cache store String and its possible remaining combinations
 * @author hpPlayer
 * @date Mar 22, 2015 9:45:07 PM
 */

public class p140_sol2 {
	public static void main(String[] args){
		String s = "catsanddog";
		//String s = "aaaaaaa";
		Set<String> hs = new HashSet<String>();
		//hs.add("aaaa");
		//hs.add("aa");
		//hs.add("a");
		//System.out.println(hs.contains("a"));
		hs.add("cat");
		hs.add("cats");
		hs.add("and");
		hs.add("sand");
		hs.add("dog");
		//System.out.println("abc".contains(""));
		System.out.println(new p140_sol2().wordBreak(s, hs));
	}
	private final Map<String, List<String>> cache = new HashMap<String, List<String>>();
	
	private boolean containsSuffix(String s, Set<String> dict){//check if we have at least one available suffix that can match current string
		for(int i = 0; i < s.length(); i++){
			if(dict.contains(s.substring(i))) return true;
		}
		return false;
	}
	
	public List<String> wordBreak(String s, Set<String> dict){
		if(cache.containsKey(s)) return cache.get(s);
		List<String> result = new LinkedList<String>();

		if(dict.contains(s)){
			result.add(s);//if contains itself, we must add it, otherwise the boundary case will return empty
		}
		for(int i = 0; i < s.length(); i++){
			String left = s.substring(0, i);
			String right = s.substring(i);

			if(dict.contains(left) && containsSuffix(right, dict)){
				for(String temp : wordBreak(right, dict)){
					result.add(left + " " + temp);
				}
			}
		}
		cache.put(s, result);
		return result;
	}
}
