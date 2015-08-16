import java.util.ArrayList;
import java.util.List;
/**
 * This algorithm uses two pointers
 * If consecutive, then move second pointer forward 
 * if not consecutive, then start build string based on two pointers
 * 
 * Remark:
 * when the loop end, that only means our second pointer moved to the tail of array, and our 
 * result has not included the last pair of range. So we need add it into our result in the end
 * 
 * @author hpPlayer
 * @date Aug 16, 2015 1:15:32 AM
 */
public class p228_sol2 {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        if (nums == null || nums.length == 0) return result;
        int start = nums[0], end = nums[0];
        for(int i = 1; i < nums.length; i++){
            //if current element is a consecutive one
            if (nums[i] == end + 1){
                end = nums[i];
                continue;
            }
            
            //if current element is not a consecutive one, it means we find a new start point
            if(start == end){//single element
                result.add(start + "");
            }else{
                result.add(start + "->" + end);
            }
            start = end = nums[i];
        }
        
        //for the last pair
        if(start == end){//single element
            result.add(start + "");
        }else{
            result.add(start + "->" + end);
        }
        return result;
    }
    
    
}
