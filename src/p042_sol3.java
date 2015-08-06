/**
 * Two pointers approach. This approach does not use extra spaces except for some variables, but is a little tricky to understand
 * The basic idea is as follows:
 * 1) use two pointers one pointer from left and the other pointer from right
 * 2) we will always move pointer on the lower bar side
 * 2) we use leftMax and rightMax to record the max bar on left and right. if we move left pointer, and found current value in left
 * pointer is larger than leftMax, then it means we could not trap water, update leftMax then continue loop. If we move right pointer,
 * and found current value in left pointer is smaller or equal leftMax, then it means we could trap water, we add (leftmax - height[left])
 * to result. Here may comes one question: why we dont consider right pointer or rightMax in calculating the result now? The answer
 * is no matter we move left or right pointer, it always means we are moving the shorter bar. We calculate the result only when we 
 * have current value <= Max value on this side, so the order should be current pointer< Max value on this side < other pointer.
 * In other words, if we found current value is larger than the max value, we should update max value and move the other pointer
 * if we found after moving other pointer, the new other value is larger than this max value, then we will have to move this pointer
 * again. So max value will always smaller than the value that other pointer points to. 
 * We will stop switch moving pointer only when current value is smaller than max value on this side.
 * So current pointer< Max value on this side < other pointer.
 * 
 * 
 * Example:
 * 
 * 1 3 4:
 * leftMax = 1, rightMax = 4, height[left] = 1, height[right] = 4
 * result += 1 - 1 = 0, then move left pointer
 * leftMax = 3, rightMax = 4, height[left] = 3, height[right] = 4
 * do nothing to result, move left pointer
 * leftMax = 4, rightMax = 4, height[left] = 4, height[right] = 4
 * loop done, we trap 0 water
 * 
 * 4 1 3:
 * leftMax = 4, rightMax = 3, height[left] = 4, height[right] = 3
 * result += 3 - 3 = 0, then move right pointer
 * leftMax = 4, rightMax = 3, height[left] = 4, height[right] = 1
 * result += 3 - 1 = 2, then move right pointer
 * leftMax = 4, rightMax = 4, height[left] = 4, height[right] = 4
 * loop done, we trap 2 water
 * 
 * 
 * 1 4 3:
 * leftMax = 1, rightMax = 3, height[left] = 1, height[right] = 3
 * result += 1 - 1 = 0, then move left pointer
 * leftMax = 4, rightMax = 3, height[left] = 4, height[right] = 3
 * do nothing to result, move left pointer
 * leftMax = 4, rightMax = 4, height[left] = 4, height[right] = 4
 * loop done, we trap 0 water
 * 
 * 
 * 4 3 1:
 * leftMax = 4, rightMax = 1, height[left] = 4, height[right] = 1
 * result += 1 - 1 = 0, then move right pointer
 * leftMax = 4, rightMax = 3, height[left] = 4, height[right] = 3
 * do nothing to result, move right pointer
 * leftMax = 4, rightMax = 4, height[left] = 4, height[right] = 4
 * loop done, we trap 0 water
 * 
 * @author hpPlayer
 * @date Aug 5, 2015 6:44:55 PM
 */
public class p042_sol3 {
	public static void main(String[] args){
		int[] height = {1,4,3};
		System.out.println(new p042_sol3().trap(height));
	}
    public int trap(int[] height) {
        if(height == null || height.length < 1) return 0;
        int left = 0, right = height.length - 1, result = 0, leftMax = height[left], RightMax = height[right];
        while(left <= right){//or simply left < right
        	//we will always move the lower edge, so the max value on moving side will be guaranteed lower than the fix side
            if(height[left] < height[right]){
                if(height[left] > leftMax){//no way we can trap water, skip this bar
                    leftMax = height[left];
                }else{//we can trap water on this bar, calculate the volume above it
                    result += leftMax - height[left];
                }
                left ++;
            }else{
                if(height[right] > RightMax){
                    RightMax = height[right];
                }else{
                    result += RightMax - height[right];
                }
                right --;
            }
        }
        
        return result;
    }
}
