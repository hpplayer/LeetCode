import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * We can sort the array first!!!! This action will save us a lot of time!!!!
 * No duplicate problem!
 * 
 * We recursively add element to the result set, and then pop each set out and then add new element to each set!
 * 
 * @author hpPlayer
 * @date Mar 3, 2015 6:58:45 PM
 */
public class p078_sol2 {
    public List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S);
       List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        for(int i = 0; i < S.length; i++){
            ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();//don't change result array, when we are iterating it
            for(List<Integer> PreSubset : result){
                ArrayList<Integer> newSubset = new ArrayList<Integer>();
                newSubset.addAll(PreSubset);
                newSubset.add(S[i]);
                temp.add(newSubset);
            }
            result.addAll(temp);
        }
        return result;
    }
}
