import java.util.ArrayList;
import java.util.List;
/**
 * This is not my solution.
 * My first thought is recursion, but I didn't figure it out
 * Actually if solving this problem with recursion, it will not be so hard.
 * We simply do loop on input.
 * If we found current char is a sign, then split the string into two parts
 * Then recursively do same thing on each substring.
 * We will do this until we reach the substring without sign in it, then we simply add this value into result and return it
 * After we get return values from next recursions, we simply do certain operations on them
 * 
 * That's it.
 * Not a diffculty problem if solving with recursion
 * 
 * A DP approach be found in sol2. But I have not understood it yet.
 * DP approach seems very complex, and there are little discussion about it online now. 
 * So I will come back if i undertand it.
 * 
 * Updated (Aug-7-2015):
 * DP approach has been added as python version in sol3 and sol4
 * DP approach does improve the speed but is hard to understand and code
 * So for this problem sol1 is more recommended
 * @author hpPlayer
 * @date Aug 6, 2015 7:02:18 PM
 */

public class p241_sol1 {
	public static void main(String[] args){
		for(int i : new p241_sol1().diffWaysToCompute("2-1-1")){
			System.out.println(i);
		}
	}
	
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<Integer>();
    	for(int i = 0; i < input.length(); i++){
        	if (input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '+'){
        		String part1 = input.substring(0, i);
        		String part2 = input.substring(i+1);
        		List<Integer> list1= diffWaysToCompute(part1);
        		List<Integer> list2= diffWaysToCompute(part2);
        		for(int j : list1){
        			for(int z : list2){
        				switch (input.charAt(i)){
        					case '+':
        						result.add(j+z);
        						break;
        					case '-':
        						result.add(j-z);
        						break;
        					case '*':
        						result.add(j*z);
        				}
        			}
        		}
        	}
        }
        
    	//if still size = 0, it means we dont have sign in given input, we just add it to our result
        if(result.size() == 0){
        	result.add(Integer.valueOf(input));
        }
        return result;
    }
    
  
}
