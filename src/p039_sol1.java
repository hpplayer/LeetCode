import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * finally I made it right without any help!!!
 * lol!!!!!!! victory, the victory of our people and the victory of DFS!!!
 * DFS IMBA!!!!
 * 
 * Basic Idea: use DFS search each node, when visiting this node sum += this node's value
 * Done search, we have included all combinations involving this value that can get to the sum, so we just need to move 
 * to next node that in the same level of current visiting node, before the movement, we need reset parameters to the values 
 * before adding this node including, sum -= Node.value, currentArray.remove(Node). Also, since we have search all nodes after 
 * current Node to get the sum, so in next node, we can't let next node visit current Node otherwise we will create duplicate 
 * so here I introduce the start index, after visiting each node and moving to the next node, we just let start index ++; so the 
 * next node will not visit previous nodes again!
 * 
 * Again cheers my AC solutions!
 * yeah, so happy!!!
 * 
 * Notice: 
 * 1)Don't forget sort array to let nodes in order otherwise we can't exclude duplicate combinations!
 * 2)Also we can improve this method by removing "int sum". we can just sub from target instead, if the result == 0, add to result sets
 * everytime we pass "target - dataset[i]" to its DFS exploration, if target < 0 return.. 
 * 
 * @author hpPlayer
 * @date Mar 5, 2015 10:53:42 PM
 */
public class p039_sol1 {
	public static void main(String[] args) {
		int[] candidates = { 1,2 };
		List<List<Integer>> result = combinationSum(candidates, 2);
		for (List<Integer> ary : result) {
			System.out.println(ary);
		}

	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> currArray = new ArrayList<Integer>();
		Arrays.sort(candidates);
		DFS(candidates, 0, target, 0, result, currArray);
		return result;
	}

	public static void DFS(int[] candidates, int currSum, int target, int start,
			List<List<Integer>> result, List<Integer> currArray) {
		if (currSum == target) {
			result.add(new ArrayList<Integer>(currArray));
			return;
		} else if (currSum > target) {
			return;
		} else {// currSum < target
			for (int i = start; i < candidates.length; i++) {
				currArray.add(candidates[i]);
				currSum += candidates[i];
				DFS(candidates, currSum, target, start, result, currArray);
				currArray.remove(currArray.size() - 1);
				currSum -= candidates[i];
				/*
				while (i + 1 < candidates.length
						&& candidates[i] == candidates[i + 1]){
					i++;
				}
			*/
				start++;
			}

		}
	}
}
