/**
 * The solution here is very brilliant.
 * The problem asks us to return the length of last word, so instead of scanning whole strings then reach last word,
 * we can just start from the tail. Firstly skip all spaces in the tail, then reach the last word, then scanning the length of last word
 * until we reach the space before last word
 * 
 * This solution will skip all useless words in the input if there is some
 * 
 * @author hpPlayer
 * @date Sep 3, 2015 7:53:36 PM
 */
public class p058_sol2 {
    public int lengthOfLastWord(String s) {
        int l = s.length() - 1;
        while(l >= 0 && s.charAt(l) == ' ') l--;
        int len = 0;
        while(l >= 0 && s.charAt(l) != ' '){
            l --;
            len ++;
        }
        
        return len;
    }
}
