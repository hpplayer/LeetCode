import java.util.ArrayList;
import java.util.HashSet;

/**
 * Use DFS, it is similar to problem of combination(p77)
 * just one step from final answer...Damn!!!!!! The correct solution is using
 * hashset to see if we have visited this node before, if yes jump to next loop
 * if no, add this one to hashset as visited and do similar DFS as we have done
 * to the combination problem..
 * 
 * 
 * So most code here is my code except the idea that check the hashset alone is enough(my old idea is check hashset and 
 * current layer and last layer to see if we are visited other node in same layer or we are visiting next layer to current visiting layer)
 * @author hpPlayer
 * @date Mar 5, 2015 3:24:02 PM
 */
public class p046_sol1 {
	public static void main(String[] args) {
		int[] input = { 1, 2, 3 };
		ArrayList<ArrayList<Integer>> temp = permute(input);
		for (ArrayList<Integer> ary : temp) {
			System.out.println(ary);
		}

	}

	public static ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> Result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> currArray = new ArrayList<Integer>();
		HashSet<Integer> indexSet = new HashSet<Integer>();
		DFS(currArray, Result, num, indexSet);
		return Result;
	}

	public static void DFS(ArrayList<Integer> currArray,
			ArrayList<ArrayList<Integer>> Result, int[] dataInput,
			HashSet<Integer> currIndex) {
		if (currArray.size() == dataInput.length) {
			Result.add(new ArrayList<Integer>(currArray));
			return;
		}

		for (int i = 0; i < dataInput.length; i++) {
			if (!currIndex.contains(dataInput[i])) {
				currArray.add(dataInput[i]);
				currIndex.add(dataInput[i]);
				DFS(currArray, Result, dataInput, currIndex);
				currArray.remove(currArray.size() - 1);
				currIndex.remove(dataInput[i]);
			}

		}
	}
}
