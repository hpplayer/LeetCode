import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
/**
 * we can try HashSet!
 * Tests showed HashSet does allow arrays with same contents, even they may be two objects!;
 * The disadvantage is that HashSet may contains all sets so waste space!
 * 
 * @author hpPlayer
 * @date Mar 3, 2015 8:13:53 PM
 */
public class p090_sol1 {
	public static void main(String[] args) {
		int[] S = { 1, 1, 2, 2 };
		List<List<Integer>> result = subsetsWithDup(S);
		for (int j = 0; j < result.size(); j++) {
			System.out.print(result.get(j) + " ");
			System.out.println();
		}
		
	}

    public static List<List<Integer>> subsetsWithDup(int[] num) {
        Arrays.sort(num);
		HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());
		for (int i = 0; i < num.length; i++) {
			List<List<Integer>> Newtemp = new ArrayList<List<Integer>>();
			for (List<Integer> PrevSubset : result) {
				ArrayList<Integer> Subset = new ArrayList<Integer>();
				Subset.addAll(PrevSubset);
				Subset.add(num[i]);
				if(!hs.contains(Subset)){
					Newtemp.add(Subset);
				}
			}
			hs.addAll(Newtemp);
			result.addAll(Newtemp);
		}
		return result;
    }
}
