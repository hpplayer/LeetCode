import java.util.ArrayList;
import java.util.List;
/**
 * My AC solution, using DFS. (Another DFS problem that I have workd out by myself without help)
 * 
 * It is similar to previous valid IP problem, here instead of checking valid IP, we need check valid palindrome
 * Valid palindrome can be ref to the problem Valid Palindrome(p125), so our algorithm should also work on the non-letter input(like "e,fe")
 * Basic idea:
 * We search each combination of input(for example abc: a, ab, abc)
 * and for each combination, we will build a subtree to it(for example a: [a, b, c] or [a, bc]
 * So when visiting subtree, we need to pass substring without current node to next layer
 * when substring.length == 0, we know our DFS is done for this node
 * After DFS is done for this node, we just remove this node and replace with next combination, loop until all combinations has been DFSed.
 * 
 * 
 * Remark:
 * 1) this problem is slightly different from previous IP problem, here when previous subtree is invalid, we can still visit next subtree
 * like (ef maybe invalid, but efe maybe valid), so we cant break the loop inside
 * 2) the method I used to validate palindrome is from p125, and itself is a problem which needs practice
 * 3) technique to call substring like(substring(start, head) or substring(start)) should be memorized since they are really useful in those
 * problems that need pass substring to subtree. (Head: inclusive, tail exclusive), substring(start): pass string from index start to the end
 *  
 * 
 * @author hpPlayer
 * @date Mar 7, 2015 6:16:43 PM
 */

public class p131_sol1 {
	public static void main(String[] args){
		String s = "e,fe";
		// String str = s.substring(0,i+1); 
		//System.out.println(s.substring(0,3));
		List<List<String>> result= partition(s);
		for(List<String> ls : result){
			System.out.println(ls);
		}
	}
	  public static List<List<String>> partition(String s) {
	        List<List<String>> result = new ArrayList<List<String>>();
	        List<String> temp = new ArrayList<String>();
	        if(s.length() == 0) return result;
	        if(s.length() == 1){
	            temp.add(s);
	            result.add(temp); 
	            return result;
	        }
	        DFS(result, temp, s);
	        return result;
	    }
	    
	    private static void DFS(List<List<String>> result, List<String> temp, String s){
	        if(s.length() == 0){
	            result.add(new ArrayList<String>(temp));
	            return;
	        }
	        
	        for(int i = 0; i <s.length(); i++){
	        	//System.out.println(i);
	            String str = s.substring(0,i+1);  
	         //  System.out.println(str);
	            if(isPalin(str)){
	                temp.add(str);//add this node to the tree and start build tree(ex.a)
	                DFS(result, temp, s.substring(i+1));
	                temp.remove(temp.size()-1);//remove this node from tree, so next pair(ex.ab) will take place
	            }
	            /* we cant jump loop here, unlike valid IP, where if our previous number is invalid then we will never get valid ip
	             * here previous number maybe invalid, but the later pair may become valid
	             * for example: efe, ef maybe invalid, we cant break the loop, since efe is valid
	            else{
	                break;
	            }
	            */
	        }
	        
	    }
	    private static boolean isPalin(String s){
	    	//System.out.println(s);
	        int i = 0;
	        int j = s.length()-1;
	        String temp = s.toLowerCase();
	        while(i < j){//i == j, we must get true
	            while(i < j && !Character.isLetterOrDigit(temp.charAt(i))) i++;
	            while(i < j && !Character.isLetterOrDigit(temp.charAt(j))) j--;
	            if(temp.charAt(i) != temp.charAt(j)) return false;
	            i++;
	            j--;
	        }
	        return true;
	    } 
}
