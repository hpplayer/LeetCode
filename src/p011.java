/**
 * Greedy Approach, and an incredible and simple algorithm
 * The limitation of the area is the lower height, we want to avoid the effect of length, so we can only focus on the height
 * How? we start from the longest area (0  to height[].length -1), so, when we move towards mid, we guarantee we at least have visited 
 * the largest area that can be formed with that length. 
 * As talked before the area is controlled by the lower height, so we need move the pointer that points to the lower height to search
 * for next higher height. This is greedy algorithm that we are always looking for height that can maximize the area
 * Bravo! 
 * @author hpPlayer
 * @date Mar 26, 2015 10:28:30 PM
 */
public class p011 {

    public int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        int left = 0, right = height.length -1;
        while(left < right){//while there are still two vertical lines left
            maxArea = Math.max(maxArea, Math.min(height[right], height[left]) * (right - left));
            if(height[left] < height[right]){//move the lower one to find if next height is higher
                left ++;
            }else{
                right --;
            }
            
        }
        return maxArea;
    }
}
