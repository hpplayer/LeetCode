import java.util.ArrayList;
import java.util.List;
/**
 * This is my original AC solution without help
 * It seems there is no short-cut towards this problem in Java
 * We just scan the array, and search consecutive next integer start with nums[i]
 * we will move array pointer to the next non consecutive integer in array, and start we next search
 * there
 * sol2 uses the similar idea with another style, the main algorithm is similar
 * 
 * It seems python has more tricky ways to solve this problem, more python solution will be updated tomorrow!
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
