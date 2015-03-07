import java.util.ArrayList;
import java.util.List;
/**
 * This solution is more clear than my solution
 * The basic idea is very easy, we create an array that contains string for each number,
 * we treat each digit as a node. For each children(values) of node, we do the DFS, and move to next 
 * node and its children, for example:
 * 1:abc
 * we firslty do a
 * a+ one of [def]
 * then do b
 * b + one of [def]
 * then do c 
 * c + one of [def]
 * and so on...
 * so more clear!
 * 
 * 
 * Remark:
 * to convert char integer to int integer, we just need to let char integer - char '0'..
 * 
 * @author hpPlayer
 * @date Mar 6, 2015 9:27:53 PM
 */
public class p017_sol2 {
	public static void main(String[] args) {
		List<String> result = letterCombinations("23");
		System.out.println(result);
	}
	
	private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl",
			"mno", "pqrs", "tuv", "wxyz" };

	public static List<String> letterCombinations(String digits) {
	        List<String> result = new ArrayList<String>();
	        DFS("", digits, 0, result);
	        return result;
	    }
	private static void DFS(String temp, String digits, int offset, List<String> result){
		if(temp.length() == digits.length()){
			result.add(temp);
			return;
		}
		
		String Letters = KEYS[digits.charAt(offset) - '0'];//get " " in keys
		for(int i = 0; i < Letters.length(); i++){
			DFS(temp + Letters.charAt(i), digits, offset+1, result);
		}
	}
}
