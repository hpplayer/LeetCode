import java.util.ArrayList;
import java.util.List;
/**
 * Use DFS!, each loop add new item into the ary, if the ary's size = k, then add it to result, and return to the start point of DFS 
 * and try another path...Super good idea!
 * 
 * Remark:
 * 1) since we use index i in the loop to represent the real number that stored in the arrayList, so the range should be 1 to n;
 * 2) since we only have one array in the DFS, so everytime when we store ary to the result output, we need create a new ary to store,
 * otherwise our later operations will change the array in the arrayList
 * 3) Each time, when done DFS for one node, we need remove it from the array, so our DFS can continue 
 * 
 * 
 * @author hpPlayer
 * @date Mar 4, 2015 10:45:47 PM
 */
public class p077 {
	public static void main(String[] args) {
		List<List<Integer>> temp = combine(1, 1);
		for (List<Integer> ary : temp) {
			System.out.println(ary.toString());
		}
	}

	public static List<List<Integer>> combine(int n, int k) {
		if(n <= 0 || n < k){
			return null;
		}
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		ArrayList<Integer> ary = new ArrayList<Integer>();
		DFS(n, k, 1, ary, result);//we need add start = 1, since our combination's smallest number is 1
		return result;
	}
	
	
	public static void DFS(int n, int k, int start,  ArrayList<Integer> ary, List<List<Integer>> result){
		if(ary.size() == k){
			// why need new arrayList??? cuz we are only using one ary object, 
			//if we dont add new to store it, then our later loop will change the one we stored in the result!!
			result.add(new ArrayList<Integer>(ary));
			return;
		}
		
		//remark, we use i to stand for the real number in combination, so its range should be [1, n]
		for(int i = start; i <= n; i++){//need i <= n, since our combination's largest number is n
			ary.add(i);
			DFS(n, k, i+1, ary, result);
			ary.remove(ary.size()-1);//DFS done for this node, we change to next node
		}
	}
}
