import java.util.*;
/*
Largest Rectangle in Histogram
(figures are in file p084)
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
find the area of largest rectangle in the histogram.
             6         
         5   __   
         __ |  |
        |  ||  | 2    3
 2   1  |  ||  |     __
 __     |  ||  | __ |  |
|  | __ |  ||  ||  ||  |
|  ||  ||  ||  ||  ||  |
Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
             6         
         5   __   
         __ |  |
        |\\||\\| 2    3
 2   1  |\\||\\|     __
 __     |\\||\\| __ |  |
|  | __ |\\||\\||  ||  |
|  ||  ||\\||\\||  ||  |

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
*/

/**
 * This problem needs a very tricky solution, see sol2
 * @author hpPlayer
 * @date Apr 17, 2015 2:29:32 AM
 */
public class p084_sol1 {
	public static void main(String[] args){
		List<Integer> ary = new ArrayList<Integer>();
		ary.add(2);
		ary.add(0);
		ary.add(2);
		System.out.println(largestRectangleArea(ary));
	}
	  public static int largestRectangleArea(List<Integer> height) {
	        int left = 0, right = height.size()-1;
	        int area = 0;
	        while(right >= left){
	            int width = left == right? 1 : right - left+1;
	            area = Math.max(area, width * Math.min(height.get(left), height.get(right)));
	            if(height.get(left) < height.get(right)){
	                left ++;
	            }else if (height.get(left) > height.get(right)){
	                right --;
	            }else{
	                left ++;   
	            }
	        }
	        return area;
	    }
}
