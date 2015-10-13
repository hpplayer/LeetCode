import java.util.Arrays;
/**
 * This is DP solution.
 * The key idea is to create the left and right array that records the max left and right height on current index
 * We firstly create two arrays left and right
 * Then begin fill two arrays by using DP approach, i.e. array[i] = Math.max(height[i], array[i+/-1])
 * So after the loop is done, we have collected all information on each index regarding the left and right limits
 * Next step is to calculating the sum.
 * The trapped water is calculated as the Min(left[i-1], right[i+1]) - height[i], if the difference is > 0 then add to results.
 * That makes sense, since if current bar is higher than all bars on the right and left, it will not trap water.
 * 
 * Remark:
 * I combined filling left and right array into the same loop. So be careful with the index j. Don't mix j with i!
 * @author hpPlayer
 * @date Aug 5, 2015 6:37:36 PM
 */

public class p042_sol2 {
	public static void main(String[] args){
		int[] height = {4,2,3};
		System.out.println(new p042_sol2().trap(height));
	}
    public int trap(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        for(int i = 0; i < height.length; i++){
        	//System.out.println(i);
        	//System.out.println(height.length - 1 - i);
            left[i] = i == 0? height[i] : Math.max(height[i], left[i-1]);
            int j = height.length - 1 - i;
            right[j] = j == height.length - 1? height[j] : Math.max(height[j], right[j+1]); 
        }
        
        //System.out.println(Arrays.toString(left));
        //System.out.println(Arrays.toString(right));
        int result = 0;
        for(int i = 1; i < height.length - 1; i++){//skip leftmost and rightmost bar as they could not trap water on top
            if(height[i] < Math.min(left[i-1], right[i+1])){//we calculate water on top, so even the height is same will not trap water
                result += Math.min(left[i-1], right[i+1]) - height[i];
            }
            
        }
        return result;
    }
}
