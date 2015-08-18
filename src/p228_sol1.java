import java.util.ArrayList;
import java.util.List;
/*
Summary Ranges 

Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

/**
 * This is my original AC solution without help
 * It seems there is no short-cut towards this problem in Java
 * We just scan the array, and search consecutive next integer start with nums[i]
 * we will move array pointer to the next non consecutive integer in array, and start we next search
 * there
 * sol2 uses the similar idea with another style, the main algorithm is similar
 * 
 * It seems python has more tricky ways to solve this problem, more python solution will be updated tomorrow!
 * 
 * Update:
 * I checked python solution today, there is no general good algorithm in this problem
 * sol3 is the python version of sol1
 * sol4 builds a matirx or map that record the range, and update the tail of range if the number is consecutive
 * sol5 uses the python's groupby function, which can not be used in Java
 * 
 * So among those solutions, sol1 and sol2 are more recommended
 * @author hpPlayer
 * @date Aug 16, 2015 1:06:28 AM
 */
public class p228_sol1 {
	public static void main(String[] args){
		int[] nums = {-1};
		for(String s : new p228_sol1().summaryRanges(nums)){
			System.out.println(s);
		}
	}
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        for(int i = 0; i < nums.length; i++){
        	int m = 0;
            String s = nums[i] + "";
            
            while(i+m+1 < nums.length && (nums[i+m] +1 == nums[i+m+1])){
                m++;
            }
            if(m != 0){
            	s += "->" + nums[i+m];
            }
            result.add(s);
            i += m;
        	//System.out.println(i);
        }
        
        return result;
    }
}
