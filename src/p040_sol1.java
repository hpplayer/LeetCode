import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* Combination Sum II
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie, a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/


/**
 * Recursive approach, iterative approach see sol2
 * Another problem that I work out without help.
 * Except for forgetting one ";" and misuse of "," where should be ":"
 * All other codes are passed at one time! yeah!
 * 
 * Basically, this problem is similar to the permutation problem (p47) which allows duplicates 
 * We use a boolean[] to indicate whether current index has been in use, and then do DFS on this node
 * After done this node, we need move to next node that is not duplicate of current node(also same as the permutation problem)
 * I think the only difference is here, we return the DFS when the target ==0 while for the permutation, wer return the DFS when the 
 * currArray.size() = input[].length
 * 
 * They all DFS problems!
 * 
 * Remark:
 * Only one trick here is when passing start index to next layer, instead of just using an int currIndex, we can use "i" instead, which means
 * first loop we only start at 0, second loop we start at 1...
 * We can do this trick to previous permutation problem since permutation allow each element to be in any place, so have no control of their 
 * order, here our output must start with the smallest values, so we can use this trick by only passing i+1 instead of creating boolean array
 * see function combinationSum2 and combinationSum3...
 * 
 * 
 * @author hpPlayer
 * @date Mar 5, 2015 11:57:07 PM
 */

public class p040_sol1 {
	public static void main(String[] args) {
		int[] candidates = {10,1,2,7,6,1,5};
		List<List<Integer>> result = combinationSum2(candidates,8);
		for (List<Integer> ary : result) {
			System.out.println(ary);
		}

	}
	 public static List<List<Integer>> combinationSum2(int[] num, int target) {
	        Arrays.sort(num);
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        List<Integer> currArray = new ArrayList<Integer>();
	        boolean[] visited = new boolean[num.length];
	        DFS(result, currArray, num, target, 0);
	        return result;
	    }
	    
	    public static void DFS(List<List<Integer>> result, List<Integer> currArray, int[] num, int target, int start){
	        if(target == 0){
	            result.add(new ArrayList<Integer>(currArray));
	            return;
	        }else if (target < 0){
	            return;
	        }else{
	            for(int i = start; i < num.length; i++){
	              //  if(visited[i] == false){
	                    currArray.add(num[i]);
	                    //visited[i] = true;
	                    DFS(result, currArray, num, target- num[i], i+1);
	                   // visited[i] = false;
	                    currArray.remove(currArray.size()-1);
	                    while(i + 1 < num.length && num[i] == num[i+1]) i++;
	              //  }
	            }
	        }         
	    }
	    
	    
        /**
         * original AC code submitted to LeetCode 
         */
        
        
        public List<List<Integer>> combinationSum3(int[] num, int target) {
            Arrays.sort(num);
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            List<Integer> currArray = new ArrayList<Integer>();
            boolean[] visited = new boolean[num.length];
            DFS2(result, currArray, num, visited, target, 0);
            return result;
        }
        
        public void DFS2(List<List<Integer>> result, List<Integer> currArray, int[] num, boolean[] visited, int target, int start){
            if(target == 0){
                result.add(new ArrayList<Integer>(currArray));
                return;
            }else if (target < 0){
                return;
            }else{
                for(int i = start; i < num.length; i++){
                    if(visited[i] == false){
                        currArray.add(num[i]);
                        visited[i] = true;
                        DFS2(result, currArray, num, visited, target- num[i], i);
                        visited[i] = false;
                        currArray.remove(currArray.size()-1);
                        while(i + 1 < num.length && num[i] == num[i+1]) i++;
                    }
                }
            }
        }
}
