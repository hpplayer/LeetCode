import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * basic idea: we can add duplicate element to all elements that ends with last duplicate element without creating duplicates
 * To achieve this, we need record index of previous duplicate element, attach current duplicate after the index of previous 
 * duplicate element(inclusive). Here we use "start" and "prevEnd" to achieve this goal.
 * "prevEnd" will record the prev index of every element
 * "start" will control the start of next iteration. If duplicate found, let start = prevEnd, otherwise let start =0;
 * 
 * Remark:
 * 1) sort array!
 * 2) List is interface, and we need create ArrayList to implement it1
 * 3) since we are updating result during inner iteration, we need use prevEnd to control the number of inner iteration
 * 4) dont forget empty subset !
 * 
 * 
 * @author hpPlayer
 * @date Mar 3, 2015 9:10:31 PM
 */

public class p090_sol2 {
	public static void main(String[] args) {
		int[] S = {0};
		List<List<Integer>> result = subsetsWithDup(S);
		for (int j = 0; j < result.size(); j++) {
			System.out.print(result.get(j) + " ");
			System.out.println();
		}
		
	}
    public static List<List<Integer>> subsetsWithDup(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        
        int start = 0;//start helps us skip all elements before last duplicate element if needed
        for(int i = 0; i < num.length; i ++){
            int prevEnd = result.size();//prevEnd help us store the index before last duplicate
            for(int j = start; j < prevEnd; j++){//we cant use result.size() here since it is changing while we add newSubset!
                ArrayList<Integer> newSubset = new ArrayList<Integer>();
                newSubset.addAll(result.get(j));
                newSubset.add(num[i]);//we add element as normal
                result.add(newSubset);
            }
            //check if next element is duplicate and whether we need to change the start
            if(i + 1 < num.length && num[i] == num[i+1]){
                start = prevEnd;
            }else{
                start = 0;
            }
        }
        return result;
    }
}
