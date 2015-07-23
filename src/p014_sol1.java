/*Longest Common Prefix
 * 
 Write a function to find the longest common prefix string amongst an array of strings.
 */

/**
 * This problem is not hard as it only requires us to find longest common PREFIX
 * This is my original solution without help
 * The first string in the array is important, we need do for loop on this string.
 * The loop length and determined char depend on this string
 * 
 * I use a variable "same" to control the flow.
 * If we found condition like current string has reached its boundary or the char is different, we set "same" to false
 * then we can determine whether to append current char to the stringbuilder
 * 
 * Sol2 is another solution which is almost same, but get rid of the "same" and return string diretctly
 * @author hpPlayer
 * @date Jul 23, 2015 5:08:01 PM
 */
public class p014_sol1 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int len = strs[0].length();
        StringBuilder sb = new StringBuilder();
        boolean same = true; 
        for(int i = 0; i < len && same; i++){
            char c = strs[0].charAt(i);
            for(String str : strs){
                if ( i >= str.length() || str.charAt(i) != c){
                    same = false;
                    break;
                }
            }
            if(same){
                sb.append(c);               
            }
        }
        return sb.toString();
    }
}
