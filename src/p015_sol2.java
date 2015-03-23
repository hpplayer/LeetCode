import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
/**
 * Similar idea, but instead of skipping duplicates, it use a hashset to remove duplicate
 * Considering this approach will still calculate duplicate number and remove duplicate in the last moment
 * The sol1 should be the better solution.
 * But it at least provides another idea to remove duplicates
 * @author hpPlayer
 * @date Mar 23, 2015 1:14:24 AM
 */

public class p015_sol2 {
	public List<List<Integer>> threeSum(int[] num) {
    	Arrays.sort(num);
    	HashSet<List<Integer>> hs = new HashSet();
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	for(int i = 0; i < num.length; i++){//we can skip loop when i > num.length -2
    		if(i >=1 && num[i] == num[i-1]) continue;//skip i, if curr value is same with prev value
    		int currSum = num[i];
    		int start = i+1; 
    		int end = num.length -1;
    		while(start < end){
    			if(num[start] + num[end] == -currSum){//equal
    				List<Integer> temp = new ArrayList<Integer>();
    				temp.add(num[i]);
    				temp.add(num[start]);
    				temp.add(num[end]);
    				
    				hs.add(temp);
    				//current start and end has been used, we will never use them again due to no-duplicate property
    				start ++;
    				end --;
    				//now we need skip duplicates    				
    			}else if (num[start] + num[end] < -currSum){//too small
    				start ++;
    			}else{//too big
    				end--;
    			}
    		}
    	}
    	result.addAll(hs);
       return result;
    }
}
