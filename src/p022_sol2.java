import java.util.ArrayList;
import java.util.List;

/**
 * Refined solution to sol1
 * Use temp+ "("/ temp + ")"  to replace StringBuffer.add and delete
 * 
 * This solution can be thinking in this way:
 * 1)How can we start build the subtree? when we have "(" left
 * 2)We firstly search the subtree with "(" after done search, then go to the subtree with ")"
 * 3)Recusively do this until we reach the right-most subtree of root, then we are done
 * 4)We add string to result, as long as we dont have "(" and ")" left
 * 
 * Remark:
 * 1) by adding condition (right > left), we dont need to check if the left or right < 0
 * 2) by passing string + ")" / "(" to its child the string is automatically back to original string after DFS is done,
 * no need to add/delete from StringBuffer or using substring()
 * 3) Another adv of this solution is that we dont need to change the original num of left, since our condition for adding "(" is
 * just when we have left >0...so it is guaranteed we have "(" in the head...
 * 
 * @author hpPlayer
 * @date Mar 6, 2015 1:04:08 PM
 */
public class p022_sol2 {
	public static void main(String[] args){
		List<String> list = generateParenthesis(3);
	System.out.println(list);
	}
	
	  public static List<String> generateParenthesis(int n) {
	    	if(n <= 0) return null;
	        List<String> list = new ArrayList<String>();
	        DFS(n, n, list, "");
	        return list;
	    }

	private static void DFS(int left, int right, List<String> Result,
			String temp) {
		// TODO Auto-generated method stub
		if(left == 0 && right ==0){
			Result.add(temp);
			return;
		}
		
		if(left > 0){//we always put left first
			DFS(left -1, right, Result, temp+"(");
		}
		
		if(left < right){//must using ">" not " >= ", otherwise we will have ")(", where ")" replaced "("
			DFS(left, right-1, Result,temp+")");
		}
		
		
	}
}
