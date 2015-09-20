import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/**
 * My original AC solution without help
 * The main idea is recursive search, but original recursive returns TLE error 
 * So I used a cache HashMap to improve the search.
 * I used a HashMap to store the result of each substring.(Ex: String: "MyFavCat", dict contains "My", "Fav", so when checking My 
 * we already know Cat is not existed in the set, next time, when we try MyFav, we will directly return false, instead of checking the
 * set again) 
 * @author hpPlayer
 * @date Mar 22, 2015 7:50:36 PM
 */

public class p139_sol2 {
	public static void main(String[] args){
		String s = "aaaabaaaaac";
		Set<String> hs = new HashSet<String>();
		hs.add("aaaaaaaaaaa");
		hs.add("b");
		hs.add("c");
		//System.out.println("abc".contains(""));
		System.out.println(new p139_sol2().wordBreak(s, hs));
	}
	public boolean wordBreak(String s, Set<String> dict) {
		HashMap<String, Boolean> hs = new HashMap<String, Boolean>();
		boolean result = wordBreak(s, dict, hs);
		System.out.println(hs.size());
		System.out.println(hs);
		return result;
	}
	
	public boolean wordBreak(String s, Set<String> dict, HashMap<String, Boolean> hs) {
		if(hs.containsKey(s)) return hs.get(s);
		if(s.length() == 0) return true;
		for(int i = 0; i < s.length(); i++){
			//System.out.println("im here");
			if(dict.contains(s.substring(0, i+1))){//try each possible substring start from 0 index
				if(wordBreak(s.substring(i+1), dict, hs)){//if found such substring, then try each possible of its substring
					hs.put(s, true);
					return true;
				}
			}
		}
		hs.put(s, false);
		return false;
	}
}
