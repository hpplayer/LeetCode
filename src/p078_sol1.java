import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/**
 * my solutions and got accepted
 * 
 * basic idea...
 * Outer loop: screen all elements and make new ArrayList that uses each elements as head
 * Inner loop: screen all elements that larger than the head and add to result, then uses the newest element as filter to recusively search
 * again
 * 
 * Remark:
 * dont forget always add empty case !
 * dont forget use addAll !
 * problem already gives distinct integer!
 * time: O(n*n)
 * 
 * @author hpPlayer
 * @date Mar 3, 2015 6:43:36 PM
 */
public class p078_sol1 {
	public static void main(String[] args){
		int[] S = {1, 1, 2};
		List<List<Integer>> result = subsets(S);
		for(int j = 0; j < result.size(); j++){
			System.out.print(result.get(j) + " ");
			System.out.println();
		}
	}
	public static List<List<Integer>> subsets(int[] S) {
		ArrayList<Integer> ary = new ArrayList<Integer>();
		HashSet<Integer> hs = new HashSet<Integer>();
		for(int i: S){
			ary.add(i);
		}
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());
		for(Integer i : ary){
			if(!hs.contains(i)){//only operate if we don't have it 
				ArrayList<Integer> singleAry = new ArrayList<Integer>();
				singleAry.add(i);
				result.add(singleAry);
				hs.add(i);
				

				
				ArrayList<Integer> currAry = new ArrayList<Integer>();
				currAry.add(i);
				
				//System.out.println("current i " + i);
				addAry(result, currAry, ary, i);
			

			/*
				for(int j = 0; j < result.size(); j++){
					System.out.print(result.get(j) + " ");
					System.out.println();
				}
			*/
			}
		}
		return result;
	}
	
	public static void addAry(List<List<Integer>> result, ArrayList<Integer> currAry, List<Integer> totalAry, Integer filter){
		if(currAry.equals(totalAry)){
			//System.out.println("im here");
			//System.out.println(currAry);
			return;
		}
		
		//System.out.println("loop over"+  " filter: " + filter);


		for(Integer i : totalAry){
			//System.out.println(i);
			//System.out.println(filter);
			if(i > filter){
				ArrayList<Integer> currAry2 = (ArrayList<Integer>) currAry.clone();
				currAry2.add(i);
				result.add(currAry2);
				//System.out.println("start");
				ArrayList<Integer> currAry3 = (ArrayList<Integer>) currAry2.clone();
				addAry(result, currAry3, totalAry, i);
				//System.out.println("end");
			}

		}
		
	}
}
