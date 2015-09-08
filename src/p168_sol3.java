/**
 * Similar solution as sol2, but here we just use recursion, each recursion will visit the value of higher digit,
 * so we always append the returned value in front
 * @author hpPlayer
 * @date Sep 7, 2015 10:56:54 PM
 */
public class p168_sol3 {
    public String convertToTitle(int n) {
        if(n == 0) return "";
        
        //each recursion, we get the value before higher digit like n->n^2->n^3
        return convertToTitle((n-1)/26) + (char) ((n-1)%26 + 'A');
    }
}
