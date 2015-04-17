import java.util.*;
/**
 * This approach works in this way:
 * 1) We use a stack to record all non-descending bar index, so we know they can form a rectangle
 * 2) If next incoming bar is smaller than the top bar in stack, then we need to pop bars in stack
 * to form rectangles. 
 * 3) The key idea is that, since our bars in stack are ascending, so we know for each top bar, its left bar in stack
 * will be smaller than it, also, the next incoming bar is smaller than top bar, so the length of max rectangle in the height of top bar
 * will between the bar just in front of top and the incoming bar. (i-s.peek()-1), s.peek is after we pop current top bar
 * 4) if the stack is empty after top, then we know we reach the start of list, then the length will just be i 
 * 5) Since our stack is non-descending, so we need to pair all previous bars in stack with bar i, this is indicated by "i--", which let
 * i stay the same
 * @author hpPlayer
 * @date Apr 17, 2015 1:21:59 AM
 */

public class p084_sol2 {
    public int largestRectangleArea(List<Integer> height) {
        int len = height.size();
        Stack<Integer> s = new Stack<Integer>();//store non-descending bar index
        int result = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len? 0 : height.get(i));
             if(s.isEmpty() || h >= height.get(s.peek())){
                s.push(i);
            }else{
                int top = s.pop();
                result = Math.max(result, height.get(top) * (s.isEmpty()? i : i - s.peek()-1));
                i--;//keep this index i, until we compare all elements in stack
            }
        }
        return result;
    }
}
