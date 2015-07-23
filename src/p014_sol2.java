/**
 * This solution found that, if we found any case like out-of-boundary or different char,
 * that means we have found  the longest common string, we can return it directly
 * Otherwise, we have reach the end of first string, then the first string is the longest common prefix
 * 
 * @author hpPlayer
 * @date Jul 23, 2015 5:11:32 PM
 */
public class p014_sol2 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int len = strs[0].length();
        StringBuilder sb = new StringBuilder();
        boolean same = true; 
        for(int i = 0; i < len; i++){
            char c = strs[0].charAt(i);
            for(String str : strs){
                if ( i >= str.length() || str.charAt(i) != c){
                   return str.substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
