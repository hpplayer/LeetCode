import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
/**
 * this method should work..but dont know why it does not pass leetcode...reported time exceeds limit...
 * 
 * @author hpPlayer
 * @date Mar 5, 2015 6:24:56 PM
 */
public class p047_sol2 {
	public static void main(String[] args) {
		int[] input = {0, 0, 1, 1};
		ArrayList<ArrayList<Integer>> temp = permuteUnique(input);
		int count = 0;
		
		/*
		 * test hashset for array
		 */
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		a.add(5);
		b.add(5);
		HashSet<ArrayList<Integer>> checkDup = new HashSet<ArrayList<Integer>>();
		checkDup.add(a);
		System.out.println(checkDup.contains(b));
		
		for (ArrayList<Integer> ary : temp) {
			count ++;
			System.out.println(ary);
		}
		System.out.println(count);
	}
	
	public static ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		ArrayList<ArrayList<Integer>> Result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> currArray = new ArrayList<Integer>();
		HashSet<ArrayList<Integer>> checkDup = new HashSet<ArrayList<Integer>>();
		boolean[] visited = new boolean[num.length];
		Arrays.sort(num);
		DFS(currArray, Result, num, visited, checkDup);
		return Result;
	}

	public static void DFS(ArrayList<Integer> currArray,
			ArrayList<ArrayList<Integer>> Result, int[] dataInput,
			boolean[] visited, HashSet<ArrayList<Integer>> checkDup) {
		if (currArray.size() == dataInput.length) {
			if (!checkDup.contains(currArray)) {
				Result.add(new ArrayList<Integer>(currArray));
				checkDup.add(new ArrayList<Integer>(currArray));
			}
			return;
		}

		for (int i = 0; i < dataInput.length; i++) {
			if (visited[i] == false) {
				currArray.add(dataInput[i]);
				visited[i] = true;
				DFS(currArray, Result, dataInput, visited, checkDup);
				currArray.remove(currArray.size() - 1);
				visited[i] = false;
			}
		}
	}
}
