import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
/**
 * Another implementation of DFS (using Stack instead of ArrayList)
 * Similar idea but I think using stack may be better (LIFO -> exactly what we do for each node)
 * 
 * BuT.....I found leetcode runs faster with arraylist than stack...maybe arraylist would be better.
 * Probably due to the addAll command in transferring data from stack to arraylist...
 * 
 * @author hpPlayer
 * @date Mar 5, 2015 11:08:18 PM
 */

public class p039_sol2 {
	public static void main(String[] args) {
		int[] candidates = { 1,2 };
		List<List<Integer>> result = combinationSum(candidates, 2);
		for (List<Integer> ary : result) {
			System.out.println(ary);
		}

	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		//List<Integer> currArray = new ArrayList<Integer>();
		Stack<Integer> path = new Stack<Integer>();
		Arrays.sort(candidates);
		DFS(candidates, target, 0, result, path);
		return result;
	}

	//public static void DFS(int[] candidates, int currSum, int target, int start, List<List<Integer>> result, List<Integer> currArray) {
	public static void DFS(int[] candidates, int target, int start, List<List<Integer>> result, Stack<Integer> path) {
		if (target == 0) {
			ArrayList<Integer> pathAry = new ArrayList<Integer>();
			pathAry.addAll(path);
			result.add(pathAry);
			return;
		} else if (0 > target) {
			return;
		} else {// currSum < target
			for (int i = start; i < candidates.length; i++) {
				//currArray.add(candidates[i]);
				path.add(candidates[i]);
				//currSum += candidates[i];
				DFS(candidates,target -candidates[i], i, result, path);
				//currArray.remove(currArray.size() - 1);
				path.pop();
				//currSum -= candidates[i];
				/*
				while (i + 1 < candidates.length
						&& candidates[i] == candidates[i + 1]){
					i++;
				}
			*/
			//	start++;
			}

		}
	}
}
