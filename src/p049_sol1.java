import java.util.*;
/**
 * my original AC solution without help.
 * I use sorted string as the key in hashMap
 * The tricky part is how to decide if it is ok to add the stored value in hashMap
 * Here I replace the value with null, if it has been added to the result, so next time when we find another 
 * anagram, we can safely skip add previous stored value
 * 
 * There are other approach that we can store index in the Map, if it is used then set it to null. Similar approach.
 * @author hpPlayer
 * @date Apr 6, 2015 9:16:31 PM
 */

public class p049_sol1 {
	public static void main(String[] args){
		String[] a = {""};
		System.out.println(anagrams(a));
	}
    public static List<String> anagrams(String[] strs) {
        if(strs == null) return null;
        if(strs.length == 0) return null;
        HashMap<String, String> hs = new HashMap<String, String>();
        List<String> result = new ArrayList<String>();
        for(String str : strs){
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String newStr = new String(c);
            if(hs.containsKey(newStr)){
                result.add(str);
                String temp = hs.get(newStr);
                if (temp != null){
                    result.add(temp);
                    hs.put(newStr, null);
                }
            }else{
                hs.put(newStr, str);
            }
        }

        return result;
    }
}
